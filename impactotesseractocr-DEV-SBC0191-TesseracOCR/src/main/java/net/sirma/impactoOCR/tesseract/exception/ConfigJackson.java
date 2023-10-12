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
      |0.0.0.1     | Pappu      | Oct 28, 2022 | #PKY0001    | Initial writing
      ----------------------------------------------------------------------------------------------
*/
//#PKY0001 begins
package net.sirma.impactoOCR.tesseract.exception;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;

@Configuration
public class ConfigJackson implements InitializingBean {

	@Autowired
	private RequestMappingHandlerAdapter converter;

	@Override
	public void afterPropertiesSet() throws Exception {
		configureJacksonToFailOnUnknownProperties();
	}

	private void configureJacksonToFailOnUnknownProperties() {
		MappingJackson2HttpMessageConverter httpMessageConverter = converter.getMessageConverters().stream()
				.filter(mc -> mc.getClass().equals(MappingJackson2HttpMessageConverter.class))
				.map(mc -> (MappingJackson2HttpMessageConverter) mc).findFirst().get();

		httpMessageConverter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

}
//#PKY0001 ends