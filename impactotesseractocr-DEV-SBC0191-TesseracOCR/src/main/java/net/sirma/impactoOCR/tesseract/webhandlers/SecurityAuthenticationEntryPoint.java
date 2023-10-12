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

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

import static java.util.Collections.singletonMap;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * SecurityAuthenticationEntryPoint is called by ExceptionTranslationFilter to handle all AuthenticationException.
 * These exceptions are thrown when authentication failed : wrong login/password, authentication unavailable, invalid token
 * authentication expired, etc.
 *
 * For problems related to access (roles), see RestAccessDeniedHandler.
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

//    	String meth = request.getMethod();
//    	String serpath = request.getServletPath();
//    	if(authException instanceof InsufficientAuthenticationException) {
    		String msgDetails = "Unrecognized request URL (" +request.getMethod() +":" + request.getServletPath() + ")";
    		ErrorResponse errorResponse = new ErrorResponse(new ErrorMsg(HttpStatusCode.SC_UNAUTHORIZED, "Invalid request url", msgDetails));
    		ObjectMapper objMapper = new ObjectMapper();
    		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
    		wrapper.setStatus(HttpStatusCode.SC_UNAUTHORIZED);
    		wrapper.setContentType(APPLICATION_JSON_VALUE);
    		wrapper.getWriter().println(objMapper.writeValueAsString(errorResponse));
    		wrapper.getWriter().flush();
    		
//    	}else {
////    		RestErrorList errorList = new RestErrorList(SC_UNAUTHORIZED, new ErrorMessage());
////            ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", SC_UNAUTHORIZED), errorList);
//            ObjectMapper objMapper = new ObjectMapper();
//
//            HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
//            wrapper.setStatus(SC_UNAUTHORIZED);
//            wrapper.setContentType(APPLICATION_JSON_VALUE);
//            wrapper.getWriter().println(objMapper.writeValueAsString(""));
//            wrapper.getWriter().flush();
//    	}
        
    }
}
//#PKY0001 ends