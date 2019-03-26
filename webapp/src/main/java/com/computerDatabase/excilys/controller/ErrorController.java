package com.computerDatabase.excilys.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
	
    @GetMapping(value = "errors")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView();
        int httpErrorCode = getErrorCode(httpRequest);
 
        switch (httpErrorCode) {
            case 403: {
                errorPage.setViewName("403");
                break;
            }
            case 405: {
                errorPage.setViewName("405");
                break;
            }
            case 500: {
                errorPage.setViewName("500");
                break;
            }
            default:
                errorPage.setViewName("404");
                break;
        }        
        return errorPage;
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
    	LOGGER.info("********************errorCode = " + httpRequest.getAttribute("javax.servlet.error.status_code"));
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
}
