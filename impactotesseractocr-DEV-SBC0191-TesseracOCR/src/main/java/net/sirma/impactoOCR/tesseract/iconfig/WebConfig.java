/*
      Good Faith Statement & Confidentiality : The below code is part of IMPACTO Suite of products . 
      Sirma Business Consulting India reserves all rights to this code . No part of this code should 
      be copied, stored or transmitted in any form for whatsoever reason without prior written consent 
      of Sirma Business Consulting (India).Employees or developers who have access to this code shall 
      take all reasonable precautions to protect the source code and documentation, and preserve its
      confidential, proprietary and trade secret status in perpetuity.Any breach of the obligations 
      to protect confidentiality of IMPACTO may cause immediate and irreparable harm to Sirma Business 
      Consulting, which cannot be adequately compensated by monetary damages. Accordingly, any breach 
      or threatened breach of confidentiality shall entitle Sirma Business Consulting to seek preliminary
      and permanent injunctive relief in addition to such remedies as may otherwise be available.
 
      //But by the grace of God We are what we are, and his grace to us was not without effect. No, 
      //We worked harder than all of them--yet not We, but the grace of God that was with us.
      ----------------------------------------------------------------------------------------------
      |Version No  | Changed by | Date         | Change Tag  | Changes Done
      ----------------------------------------------------------------------------------------------
      |0.1 Beta    | Nye 		| Sep 4, 2018  | #00000001   | Initial writing
      ----------------------------------------------------------------------------------------------
      
*/
// #00000001 Begins

package net.sirma.impactoOCR.tesseract.iconfig;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.monitorjbl.json.JsonViewSupportFactoryBean;

@EnableWebMvc
@Configuration
@ComponentScan("net.sirma.impactoOCR.tesseract")
public class WebConfig {
	
	@Bean public CommonsMultipartResolver multipartResolver() { 
		CommonsMultipartResolver multipart = new CommonsMultipartResolver();
		multipart.setMaxUploadSize(50 * 1024 * 1024); return multipart;}
	
	@Bean @Order(0) public MultipartFilter multipartFilter() { 
		MultipartFilter multipartFilter = new MultipartFilter(); 
		multipartFilter.setMultipartResolverBeanName("multipartResolver"); 
		return multipartFilter; 
		}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {

	     MultipartConfigFactory factory = new MultipartConfigFactory();

	     //factory.setMaxFileSize("9000MB");

	     //factory.setMaxRequestSize("9000MB");

	     return factory.createMultipartConfig();

	}
	
	 @Bean
	  public JsonViewSupportFactoryBean views() {
	    return new JsonViewSupportFactoryBean();
	  }
	 
	 @Bean
	    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
	        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	        validatorFactoryBean.setValidationMessageSource(messageSource);
	        return validatorFactoryBean;
	    }
  
	 @Bean
	    public Jackson2ObjectMapperBuilderCustomizer addCustomBigDecimalDeserialization() {
	        return new Jackson2ObjectMapperBuilderCustomizer() {

	            @Override
	            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
	               jacksonObjectMapperBuilder.featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	               // Add your customization
	               // jacksonObjectMapperBuilder.featuresToEnable(...)      
	            }
	        };
	    }
	 
	 @Bean 
	 public Jackson2ObjectMapperBuilder objectMapperBuilder(){
	     Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	     builder.failOnUnknownProperties(true);
	     return builder;
	 }
}
//#00000001 Ends

