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


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import net.sirma.impactoOCR.tesseract.ocr.ErrorMsg;
import net.sirma.impactoOCR.tesseract.ocr.ErrorResponse;
import net.sirma.impactoOCR.tesseract.ocr.FinalResponseProcessor;
import net.sirma.impactoOCR.tesseract.ocr.HttpStatusCode;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandlerController {
	@SuppressWarnings("unused")
	private FinalResponseProcessor finalRes = new FinalResponseProcessor();

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleInvalidArguments(MethodArgumentNotValidException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			String details = error.getField() + " should not be blank or invalid input";
			errorMap.put("error", new ErrorMsg(HttpStatusCode.SC_BAD_REQUEST, error.getDefaultMessage(), details));
		});

		return errorMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnrecognizedPropertyException.class)
	public void handleMessageNotReadable(HttpServletRequest request, HttpServletResponse response,
			UnrecognizedPropertyException ex) throws JsonProcessingException, IOException {
		String errMsg = "Unknown Property : " + ex.getPropertyName();
		ErrorResponse errorResponse = new ErrorResponse(new ErrorMsg(HttpStatusCode.SC_BAD_REQUEST, errMsg,
				"Property '" + ex.getPropertyName() + "' is unknown field"));
		ObjectMapper objMapper = new ObjectMapper();
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
		wrapper.setStatus(HttpStatusCode.SC_BAD_REQUEST);
		wrapper.setContentType(APPLICATION_JSON_VALUE);
		wrapper.getWriter().println(objMapper.writeValueAsString(errorResponse));
		wrapper.getWriter().flush();

	}

}
//#PKY0001 ends