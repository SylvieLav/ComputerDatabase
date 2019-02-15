package com.computerDatabase.excilys.spring;

import org.springframework.web.servlet.support.*;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {MainConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses()  {
        return new Class[] {WebConfiguration.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

}