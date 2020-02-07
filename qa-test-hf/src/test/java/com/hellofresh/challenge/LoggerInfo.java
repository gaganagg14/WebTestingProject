package com.hellofresh.challenge;

import org.apache.log4j.Logger;

/**
 * Created by gaggarwal 
 *  Logging Class using log4j
 */

public class LoggerInfo {

	static volatile LoggerInfo logger;
	Logger log;
	
	private LoggerInfo() {
		log = Logger.getLogger("Log4JLogs");
	}
	
	public static LoggerInfo getInstance() {
		if(logger == null) {
			synchronized(LoggerInfo.class) {
				if(logger == null) {
					logger = new LoggerInfo();
				}
			}
		}
		return logger;
	}
	

	public void Info(String TESTID, String sMessage){
    	log.info(TESTID +"->"+ sMessage);
    }
    public void Info(String sMessage){	
    	log.info(sMessage);
    }

    public void Debug(String TESTID, String sMessage){
    	log.debug(TESTID +"->"+ sMessage);
    }
    
    public void Debug(String sMessage){
    	log.debug(sMessage);
    }
    
    public void Error(String sMessage){
    	log.debug(sMessage);
    }
}
