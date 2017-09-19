package com.rocklct.framework;

import com.rocklct.framework.bean.Data;
import com.rocklct.framework.bean.Handler;
import com.rocklct.framework.bean.Param;
import com.rocklct.framework.bean.View;
import com.rocklct.framework.helper.BeanHelper;
import com.rocklct.framework.helper.ConfigHelper;
import com.rocklct.framework.helper.ControllerHelper;
import com.rocklct.framework.util.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rocklct on 2017/9/18.
 */

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // init all the helper classes
        HelperLoader.init();
        // get ServletContext to register Servlet
        ServletContext servletContext = config.getServletContext();

        // handle jsp-registered servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");

        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        // process the servlet that handles static resources
        ServletRegistration defaultRegistration = servletContext.getServletRegistration("default");
        defaultRegistration.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get requestMethod and requestPath
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();

        // get Action Handler
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);

        if (handler != null) {
            // get controller class and corresponding instance
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            //create param Object
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                // put the parameter from request into the paramMap by getParameter()
                paramMap.put(paramName, paramValue);
            }

            // put the parameter from request into the paramMap by getInputStream()
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if (StringUtil.isNotEmpty(body)) {
                String[] params = StringUtils.split(body, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] array = StringUtils.split(param, "=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }

            // encapsulate the map
            Param param = new Param(paramMap);

            //invoke the Action method
            Method actionMethod = handler.getActionMethod();
            // use the controller instance to invoke the specific method
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);

            if (result instanceof View) {
                // return a Jsp
                View view = (View) result;
                String path = view.getPath();

                if (StringUtil.isNotEmpty(path)) {
                    // if path starts with '/',then complete and redirect this path
                    if (path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path)
                                .forward(req, resp);
                    }
                }
            } else if (result instanceof Data) {
                // return JSON data
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();

                }


            }

        }

    }
}
