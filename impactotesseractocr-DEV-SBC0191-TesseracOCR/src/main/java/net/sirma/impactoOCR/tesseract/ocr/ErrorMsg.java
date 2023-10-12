package net.sirma.impactoOCR.tesseract.ocr;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(NON_NULL)
public class ErrorMsg {
	private int code;
	private String message;
	private String detail;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ErrorMsg(int code, String message, String detail) {
		super();
		this.code = code;
		this.message = message;
		this.detail = detail;

	}

}
