package org.esupportail.cas.config;

import org.apereo.cas.authentication.adaptive.AdaptiveAuthenticationPolicy;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.ticket.UniqueTicketIdGenerator;
import org.apereo.cas.util.HostNameBasedUniqueTicketIdGenerator;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.apereo.cas.web.flow.resolver.CasDelegatingWebflowEventResolver;
import org.apereo.cas.web.flow.resolver.CasWebflowEventResolver;
import org.apereo.cas.web.support.CookieRetrievingCookieGenerator;
import org.esupportail.cas.flow.AgimusCookieAction;
import org.esupportail.cas.flow.AgimusCookieWebflowConfigurer;
import org.esupportail.cas.util.CasAgimusLogger;
import org.esupportail.cas.web.AgimusCookieRetrievingCookieGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.execution.Action;

/**
 * This is {@link CasAgimusCookieWebflowConfiguration}.
 *
 * @author Julien Marchal
 * @since 5.0.0
 */
@Configuration("casAgimusCookieWebflowConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CasAgimusCookieWebflowConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(CasAgimusCookieWebflowConfiguration.class);
   	
	@Autowired
    @Qualifier("adaptiveAuthenticationPolicy")
    private AdaptiveAuthenticationPolicy adaptiveAuthenticationPolicy;

    @Autowired
    @Qualifier("serviceTicketRequestWebflowEventResolver")
    private CasWebflowEventResolver serviceTicketRequestWebflowEventResolver;

    @Autowired
    @Qualifier("initialAuthenticationAttemptWebflowEventResolver")
    private CasDelegatingWebflowEventResolver initialAuthenticationAttemptWebflowEventResolver;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CasConfigurationProperties casProperties;
    
    @Autowired
    @Qualifier("loginFlowRegistry")
    private FlowDefinitionRegistry loginFlowDefinitionRegistry;

    @Autowired
    private FlowBuilderServices flowBuilderServices;
     
    @Autowired
    @Qualifier("agimusTokenTicketIdGenerator")
    private HostNameBasedUniqueTicketIdGenerator agimusTokenTicketIdGenerator;

    
    @Bean
    @RefreshScope
    public CasAgimusConfigurationProperties agimusConfigurationProperties() {
        return new CasAgimusConfigurationProperties();
    }
    
    @Bean
    @RefreshScope
    public HostNameBasedUniqueTicketIdGenerator agimusTokenTicketIdGenerator() {
        /*return new DefaultUniqueTicketIdGenerator();*/
    	return new HostNameBasedUniqueTicketIdGenerator(
    				agimusConfigurationProperties().getCookieValueMaxLength(),
    				casProperties.getHost().getName()
    			);
    }

    @Bean
    public CasAgimusLogger agimusLogger() {
    	LOGGER.debug("CasAgimusCookieWebflowConfiguration::CasAgimusLogger : Create bean agimusLogger");    	
    
        return new CasAgimusLogger();
    }
    
    @Bean
    public Action agimusCookieAction() {
    	LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieAction : Create bean agimusCookieAction");    	
    
        return new AgimusCookieAction(initialAuthenticationAttemptWebflowEventResolver, 
                serviceTicketRequestWebflowEventResolver,
                adaptiveAuthenticationPolicy);
    }
    
    @Bean
    @RefreshScope
    public CookieRetrievingCookieGenerator agimusCookieGenerator() {
    	LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieGenerator : Create bean agimusCookieGenerator");
    	LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieGenerator : Read Properties");
    	
    	CasAgimusConfigurationProperties agimusConfigurationProperties = this.agimusConfigurationProperties();
    	

    	LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieGenerator : AgimusCookieName = ["+ agimusConfigurationProperties.getCookieName() +"]");                        
        LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieGenerator : AgimusCookieMaxAge = ["+ agimusConfigurationProperties.getCookieMaxAge() +"]");
        LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieGenerator : AgimusCookiePath = ["+ agimusConfigurationProperties.getCookiePath() +"]");
        LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieGenerator : AgimusCookieDomain = ["+ agimusConfigurationProperties.getCookieDomain() +"]");
        LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieGenerator : AgimusCookieValuePrefix = ["+ agimusConfigurationProperties.getCookieValuePrefix() +"]");
        LOGGER.debug("CasAgimusCookieWebflowConfiguration::agimusCookieGenerator : AgimusTraceFileSeparator = ["+ agimusConfigurationProperties.getTraceFileSeparator() +"]");
        
    	return new AgimusCookieRetrievingCookieGenerator(agimusConfigurationProperties.getCookieName(), 
    			agimusConfigurationProperties.getCookiePath(), 
    			agimusConfigurationProperties.getCookieMaxAge(),
    			agimusConfigurationProperties.getCookieDomain(),
    			agimusConfigurationProperties.getCookieValuePrefix());
    }
    
    @Bean
    @DependsOn("defaultWebflowConfigurer")
    public CasWebflowConfigurer agimusCookieWebflowConfigurer() {
    	LOGGER.info("CasAgimusCookieWebflowConfiguration::agimusCookieWebflowConfigurer : configure Agimus WebFlow");    
    	final CasWebflowConfigurer w = new AgimusCookieWebflowConfigurer(flowBuilderServices,          
                loginFlowDefinitionRegistry,
                applicationContext, casProperties);
        w.initialize();
        return w;
    }   
    
}
