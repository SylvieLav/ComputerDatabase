package com.computerDatabase.excilys.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

public class Authenticator implements ClientRequestFilter {

    private final String user;
    private final String password;

    public Authenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        final String basicAuthentication = getBasicAuthentication();
        
        headers.add("Authorization", basicAuthentication);
    }
    
    private String getBasicAuthentication() {
        String token = this.user + ":" + this.password;
        
        try {
            return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Cannot encode with UTF-8", e);
        }
    }
}
