package com.ztgm.mall.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void responseData(HttpServletRequest request, HttpServletResponse response, String data) {
		try {

			String res = data;
			String uri = request.getRequestURI();

			response.setHeader("content-type", "text/html; charset=UTF-8");
			response.setHeader("Content-Length", "" + res.getBytes("UTF-8").length);
			response.setCharacterEncoding("UTF-8");

			response.getWriter().print(res);
			response.getWriter().flush();
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logger.error("参数解析失败", e);
		// return ServiceResponseHandle.failed("could_not_read_json");
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public void handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		logger.error("不支持当前请求方法", e);
		// return ServiceResponseHandle.failed("request_method_not_supported");
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e) {

		logger.error("服务运行异常", e);
		e.printStackTrace();
		// return ServiceResponseHandle.failed("server_error");
	}

	// @ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleUnexpectedServerError(RuntimeException ex) {

		logger.error(ex.getMessage());
		return ex.getMessage();
	}

	
}