package net.sirma.impactoOCR.tesseract.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

//import net.sourceforge.tess4j.Tesseract;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1")
public class OcrController {

	private FinalResponseProcessor finalRes = new FinalResponseProcessor();

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "/ocr", produces = MediaType.APPLICATION_JSON_VALUE)
	@Consumes("multipart/form-data") // #00000002 Changes
	public ResponseWrapper generatesOCR(@Valid @RequestBody AuthenticateRequestParams authenticateRequestParams,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ResponseWrapper responseWrapper = null;
		ErrorResponse ErrorResponse = null;
		SuccessResponse SuccessResponse = null;
		ErrorMsg ErrorMsg = null;
		Gson gson = new GsonBuilder().serializeNulls().create();
		String decoReq = gson.toJson(authenticateRequestParams);
		decoReq = decoReq.replaceAll("\\+", "%2B");
		try {
			decoReq = URLDecoder.decode((decoReq), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		authenticateRequestParams = gson.fromJson(decoReq, AuthenticateRequestParams.class);
		String name = authenticateRequestParams.getName();
		JsonObject resObject = new JsonObject();
		resObject.addProperty("name", name);
		resObject.addProperty("executed", true);
		LinkedHashMap isonMap = new LinkedHashMap();
		ObjectMapper mapperObj = new ObjectMapper();
		isonMap = mapperObj.readValue(gson.toJson(resObject).toString(), new TypeReference<LinkedHashMap>() {});
		responseWrapper = new ResponseWrapper(isonMap, HttpStatus.OK, null);
		SuccessResponse = new SuccessResponse(isonMap);
		finalRes.processFinalResponse(response, SuccessResponse, ErrorResponse, isonMap, HttpStatusCode.SC_OK);
		return responseWrapper;

	}

}
