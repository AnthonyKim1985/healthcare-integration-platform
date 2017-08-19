package org.bigdatacenter.healthcareintegrationplatform.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

public class RESTException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(RESTException.class);

    public RESTException(String message, HttpServletResponse httpServletResponse) {
        super(message);
        logger.error(String.format("%s - REST Exception occurs: %s", Thread.currentThread().getName(), message));
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}