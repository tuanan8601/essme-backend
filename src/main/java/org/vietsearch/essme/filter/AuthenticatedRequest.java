package org.vietsearch.essme.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AuthenticatedRequest extends HttpServletRequestWrapper {

    private String userId;
    public AuthenticatedRequest(HttpServletRequest req, String userId) {
        super(req);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}