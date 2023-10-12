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
package net.sirma.impactoOCR.tesseract.webhandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sirma.impactoOCR.tesseract.ocr.ErrorMsg;
import net.sirma.impactoOCR.tesseract.ocr.ErrorResponse;
import net.sirma.impactoOCR.tesseract.ocr.HttpStatusCode;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

import static java.util.Collections.singletonMap;
import static org.apache.commons.httpclient.HttpStatus.SC_FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The RestAccessDeniedHandler is called by the ExceptionTranslationFilter to
 * handle all AccessDeniedExceptions. These exceptions are thrown when the
 * authentication is valid but access is not authorized.
 *
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String msgDetails = "Access denied";
		ErrorResponse errorResponse = new ErrorResponse(new ErrorMsg(HttpStatusCode.SC_FORBIDDEN, msgDetails, "Access denied - Invalid or Unknown Request"));
		ObjectMapper objMapper = new ObjectMapper();
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
		wrapper.setStatus(HttpStatusCode.SC_FORBIDDEN);
		wrapper.setContentType(APPLICATION_JSON_VALUE);
		wrapper.getWriter().println(objMapper.writeValueAsString(errorResponse));
		wrapper.getWriter().flush();
	}
}
//#PKY0001 ends