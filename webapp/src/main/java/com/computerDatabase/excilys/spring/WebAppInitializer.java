package com.computerDatabase.excilys.spring;

import org.springframework.web.servlet.support.*;

import com.computerDatabase.excilys.config.DbConnection;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {DbConnection.class, SecurityConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses()  {
        return new Class[] {WebConfiguration.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
