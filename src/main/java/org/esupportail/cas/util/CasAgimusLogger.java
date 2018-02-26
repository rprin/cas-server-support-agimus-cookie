package org.esupportail.cas.util;

import org.esupportail.cas.config.CasAgimusConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This is {@link CasAgimusLogger}.
 *
 * @author Julien Marchal
 * @since 5.0.0
 */
public class CasAgimusLogger {
	private static final Logger LOGGER = LoggerFactory.getLogger(CasAgimusLogger.class);
   
	@Autowired
    @Qualifier("agimusConfigurationProperties")
    private CasAgimusConfigurationProperties agimusConfigurationProperties;
    
	public CasAgimusLogger() {		
		LOGGER.debug("CasAgimusLogger::CasAgimusLogger : create bean CasAgimusLogger");    	
	}
	
	public void log(String username, String value) {
		LOGGER.info(value + "" + agimusConfigurationProperties.getTraceFileSeparator() + "" + username);
	}
}
