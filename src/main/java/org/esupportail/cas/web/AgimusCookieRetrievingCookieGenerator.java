package org.esupportail.cas.web;

import org.apereo.cas.web.support.CookieRetrievingCookieGenerator;
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
	   
    public AgimusCookieRetrievingCookieGenerator(final String name, final String path,
                                                  final int maxAge, final String domain,
                                                  final String cookieValuePrefix) {
        super(name, path, maxAge, false, domain, false);
        LOGGER.debug("AgimusCookieRetrievingCookieGenerator::AgimusCookieRetrievingCookieGenerator : create bean AgimusCookieRetrievingCookieGenerator");
    }
}
