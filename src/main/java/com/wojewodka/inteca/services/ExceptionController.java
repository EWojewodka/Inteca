package com.wojewodka.inteca.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler({ Throwable.class })
	@ResponseBody
	public ResponseEntity<ExceptionModel> handleException(Throwable t, HttpServletResponse response)
			throws IOException {
		ExceptionModel result = new ExceptionModel(t);
		response.sendError(result.getHttpCode());
		return new ResponseEntity<ExceptionModel>(result, HttpStatus.resolve(result.getHttpCode()));
	}

}
