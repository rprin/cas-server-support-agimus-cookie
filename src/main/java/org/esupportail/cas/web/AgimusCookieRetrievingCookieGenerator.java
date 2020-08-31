package org.esupportail.cas.web;

import org.apereo.cas.web.cookie.CookieGenerationContext;
import org.apereo.cas.web.support.gen.CookieRetrievingCookieGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates the agimus cookie.
 *
 * @author Julien Marchal
 * @since 5.2.2
 */
public class AgimusCookieRetrievingCookieGenerator extends CookieRetrievingCookieGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(AgimusCookieRetrievingCookieGenerator.class);
	   
    public AgimusCookieRetrievingCookieGenerator(final CookieGenerationContext cookieGenerationContext) {
        super(cookieGenerationContext);
        LOGGER.debug("AgimusCookieRetrievingCookieGenerator::AgimusCookieRetrievingCookieGenerator : create bean AgimusCookieRetrievingCookieGenerator");
    }
}
