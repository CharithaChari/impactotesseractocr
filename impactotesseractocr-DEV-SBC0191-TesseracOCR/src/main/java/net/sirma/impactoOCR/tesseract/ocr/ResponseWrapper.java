package net.sirma.impactoOCR.tesseract.ocr;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import javax.lang.model.type.ErrorType;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(NON_NULL)
public class ResponseWrapper {
	private Object data;
	private Object metadata;
	private List<ErrorType> errors;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public List<ErrorType> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorType> errors) {
		this.errors = errors;
	}

	public ResponseWrapper(Object data, Object metadata, List<ErrorType> errors) {
		super();
		this.data = data;
		this.metadata = metadata;
		this.errors = errors;
	}

}
