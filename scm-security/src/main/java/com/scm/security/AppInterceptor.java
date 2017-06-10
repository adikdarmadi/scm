package com.scm.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scm.constant.SecurityConstant;
import com.scm.util.CommonUtil;

/**
 * Interceptor class for All annotation method controller @AppPermission
 * 
 * @author Adik
 */
public class AppInterceptor implements HandlerInterceptor {

	private final Logger LOG = LoggerFactory.getLogger(AppInterceptor.class);

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	public AppInterceptor() {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			HandlerMethod hm = (HandlerMethod) handler;
			Method method = hm.getMethod();

			if (method.isAnnotationPresent(AppPermission.class)) {
				String hakAksesValue = method.getAnnotation(AppPermission.class).hakAkses();
				List<String> list = new ArrayList<String>();
				if (CommonUtil.isNotNullOrEmpty(hakAksesValue)) {
					String[] array = hakAksesValue.split("\\,", -1);
					list = Arrays.asList(array);
				}
				if (CommonUtil.isNotNullOrEmpty(list)) {
					Boolean isLogin = false;
					for (String hakAkses : list) {
						
						if (hakAkses.equalsIgnoreCase("IS_ADD")) {
							isLogin = true;
						} else if (hakAkses.equalsIgnoreCase("IS_CONFIRM")) {
							isLogin = true;
						}
					}
					if (isLogin) {
						return true;
					} else {
						setUnautorized(response);
						return false;
					}
				}
			}
		} catch (Exception e) {
			setNotFound(response);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
	}
	
	public void setUnautorized(HttpServletResponse response) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SecurityConstant.STATUS, HttpStatus.UNAUTHORIZED.name());
		map.put(SecurityConstant.STATUS_CODE, HttpStatus.UNAUTHORIZED.toString());
		map.put(SecurityConstant.MESSAGE, HttpStatus.UNAUTHORIZED.toString());

		// convert map to JSON string
		try {
			json = mapper.writeValueAsString(map);
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();						

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setNotFound(HttpServletResponse response) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SecurityConstant.STATUS, HttpStatus.NOT_FOUND.name());
		map.put(SecurityConstant.STATUS_CODE, HttpStatus.NOT_FOUND.toString());
		map.put(SecurityConstant.MESSAGE, HttpStatus.NOT_FOUND.toString());

		// convert map to JSON string
		try {
			json = mapper.writeValueAsString(map);
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.setContentType("application/json");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}