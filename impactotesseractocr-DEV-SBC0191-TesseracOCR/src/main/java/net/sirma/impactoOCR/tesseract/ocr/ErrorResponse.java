package net.sirma.impactoOCR.tesseract.ocr;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(NON_NULL)
public class ErrorResponse {
	private ErrorMsg error;

	public ErrorMsg getError() {
		return error;
	}

	public void setError(ErrorMsg error) {
		this.error = error;
	}

	public ErrorResponse(ErrorMsg error) {
		super();

		this.error = error;

	}

}
