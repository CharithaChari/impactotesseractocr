package net.sirma.impactoOCR.tesseract.ocr;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class AuthenticateRequestParams {
	@NotEmpty(message = "name should not be blank or empty")
	private String name;

	public AuthenticateRequestParams(@NotEmpty(message = "name should not be blank or empty") String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
