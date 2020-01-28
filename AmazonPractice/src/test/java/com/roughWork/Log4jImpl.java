package com.roughWork;

import org.apache.logging.log4j.LogManager;

public class Log4jImpl {

	private static org.apache.logging.log4j.Logger logger =  LogManager.getLogger(Log4jImpl.class.getName());
	 
    public static void main(String[] args) {
        logger.debug("Log4jExample: A Sample Debug Message");
        logger.info("Log4jExample: A Sample Info  Message");
        logger.warn("Log4jExample: A Sample Warn  Message");
        logger.error("Log4jExample: A Sample Error Message");
        logger.fatal("Log4jExample: A Sample Fatal Message");       
    }
}
