package net.sirma.impactoOCR.tesseract.ocr;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class FinalResponseProcessor {
	public void processFinalResponse(HttpServletResponse response, SuccessResponse successResponse,
			ErrorResponse ErrorResponse, Object data, int httpStatusCode) throws IOException, ServletException {
		if (successResponse != null && ErrorResponse == null && data != null) {
			ObjectMapper objMapper = new ObjectMapper();
			HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
			wrapper.setStatus(httpStatusCode);
			wrapper.setContentType(APPLICATION_JSON_VALUE);
			wrapper.getWriter().println(objMapper.writeValueAsString(data));
			wrapper.getWriter().flush();

		} else {
			ObjectMapper objMapper = new ObjectMapper();
			HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
			wrapper.setStatus(httpStatusCode);
			wrapper.setContentType(APPLICATION_JSON_VALUE);
			wrapper.getWriter().println(objMapper.writeValueAsString(ErrorResponse));
			wrapper.getWriter().flush();

		}

	}

}
