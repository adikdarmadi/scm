package com.scm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scm.constant.BaseConstant;
import com.scm.security.TokenAuthenticationService;
import com.scm.security.UserAuthentication;
import com.scm.service.LoginUserService;
import com.scm.util.DateUtil;
import com.scm.util.rest.RestUtil;
import com.scm.vo.AuthVO;
import com.scm.vo.UserVO;

/**
 * Controller class for Authenticate Business
 * 
 * @author Adik
 */
@RestController
@RequestMapping("/auth")
public class AuthenticateController {

	@Autowired
	private LoginUserService userService;
	protected Map<String, String> mapHeaderMessage = new HashMap<String, String>();
	
	@Autowired
	public TokenAuthenticationService tokenAuthenticationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);
	
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserVO> signIn(@RequestBody AuthVO vo, HttpServletRequest request, HttpServletResponse httpResponse) {

		if (vo.getId() == null || vo.getPassword() == null) {
			return RestUtil.getJsonHttptatus(HttpStatus.BAD_REQUEST);
		}

		LOGGER.info("starting logging {}", vo.getId() + " at " + DateUtil.getIndonesianStringDate(new Date()));
		
		UserVO userVo = userService.signIn(vo);
		
		mapHeaderMessage.clear();
		
		if (userVo == null) {
			mapHeaderMessage.put(BaseConstant.STATUS, HttpStatus.UNAUTHORIZED.name());
			mapHeaderMessage.put(BaseConstant.STATUS_CODE, HttpStatus.UNAUTHORIZED.toString());
			mapHeaderMessage.put(BaseConstant.MESSAGE, BaseConstant.HttpHeaderInfo.LABEL_ERROR);
			return RestUtil.getJsonResponse(userVo, HttpStatus.UNAUTHORIZED, mapHeaderMessage);
		}else{
			GrantedAuthority authority = new SimpleGrantedAuthority("USER");
			String token = tokenAuthenticationService.addAuthentication(httpResponse, new UserAuthentication(
					new User(userVo.getId(), userVo.getPassword(), Arrays.asList(authority))));

			mapHeaderMessage.put("X-AUTH-TOKEN", token);
			
			mapHeaderMessage.put(BaseConstant.STATUS, HttpStatus.OK.name());
			mapHeaderMessage.put(BaseConstant.STATUS_CODE, HttpStatus.OK.toString());
			mapHeaderMessage.put(BaseConstant.MESSAGE, BaseConstant.HttpHeaderInfo.LABEL_SUCCESS);
			return RestUtil.getJsonResponse(userVo, HttpStatus.OK, mapHeaderMessage);
		}
	}
	
	@RequestMapping(value = "/sign-out", method = RequestMethod.POST)
	@ResponseBody
	public void signOut(@RequestBody AuthVO vo) {

		LOGGER.info("starting logout{}", vo.getId() + " at " + DateUtil.getIndonesianStringDate(new Date()));

		// misal call service logout dan seterusnya
		// Karena Stateless tidak perlu set " session user " menjadi tidak
		// aktif, return HttpStatus.OK ke client
		RestUtil.getJsonHttptatus(HttpStatus.OK);
	}

}
