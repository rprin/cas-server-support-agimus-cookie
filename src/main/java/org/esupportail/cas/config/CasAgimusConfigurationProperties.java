package org.esupportail.cas.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CAS Agimus configuration properties model.
 *
 * @author Julien Marchal
 */
@ConfigurationProperties(prefix = "cas.agimus")
public class CasAgimusConfigurationProperties {
    private String cookieName = "AGIMUS";
    // 3 days
    private int cookieMaxAge = 259200;
    private String cookiePath = "/";
    private int cookieValueMaxLength = 30;
    public int getCookieValueMaxLength() {
		return cookieValueMaxLength;
	}

	public void setCookieValueMaxLength(int cookieValueMaxLength) {
		this.cookieValueMaxLength = cookieValueMaxLength;
	}

	private String cookieDomain = "univ.fr";
    private String cookieValuePrefix = "TRACEAGIMUS";
    private String traceFileSeparator = ":";
    
    
    public String getTraceFileSeparator() {
		return traceFileSeparator;
	}

	public void setTraceFileSeparator(String traceFileSeparator) {
		this.traceFileSeparator = traceFileSeparator;
	}

	public int getCookieMaxAge() {
		return cookieMaxAge;
	}

	public void setCookieMaxAge(int cookieMaxAge) {
		this.cookieMaxAge = cookieMaxAge;
	}

	public String getCookiePath() {
		return cookiePath;
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public String getCookieDomain() {
		return cookieDomain;
	}

	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	public String getCookieValuePrefix() {
		return cookieValuePrefix;
	}

	public void setCookieValuePrefix(String cookieValuePrefix) {
		this.cookieValuePrefix = cookieValuePrefix;
	}    

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }
}
