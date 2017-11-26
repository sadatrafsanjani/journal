package com.rafsan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController{

    private static final String PATH = "/error";
    private static final String TEMPLATE = "error";

    private final ErrorAttributes errorAttributes;

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(PATH)
    public String error(ModelMap modelMap, HttpServletRequest request) {

        Map<String, Object> error = getErrorAttributes(request, true);

        modelMap.put("pageName","Error");
        modelMap.put("timpestamp", error.get("timestamp"));
        modelMap.put("status", error.get("status"));
        modelMap.put("error", error.get("error"));
        modelMap.put("message", error.get("message"));
        modelMap.put("path", error.get("path"));

        return TEMPLATE;
    }

    @RequestMapping("/404")
    public String page404(ModelMap modelMap, HttpServletRequest request) {

        modelMap.put("pageName","404");
        modelMap.put("error", getErrorAttributes(request, true));

        return "404";
    }

    private Map<String,Object> getErrorAttributes(HttpServletRequest request, boolean stackTrace) {

        RequestAttributes ra = new ServletRequestAttributes(request);

        return this.errorAttributes.getErrorAttributes(ra, stackTrace);
    }
}
