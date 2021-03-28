package com.team5.userAuthentication.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team5.userAuthentication.exeptions.*;
import com.team5.userAuthentication.model.User;
import com.team5.userAuthentication.service.SecurityTokenGenerator;
import com.team5.userAuthentication.service.UserAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {

	@Autowired
	UserAuthenticationService authService;

	public UserAuthenticationController(UserAuthenticationService authService) {
		this.authService = authService;
	}
	
	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		try {
			authService.saveUser(user);
			return new ResponseEntity<String>("User Registered Successfully",HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("{\"message\" : \""+e.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginDetails){
		
		try {
			
			String userId=loginDetails.getUserId();
			String password=loginDetails.getUserPassword();
			
			if(userId==null || password==null)
			{
				throw new Exception("UserId or Password cannot be empty");
			}
			User loggedInUser=authService.findByUserIdAndPassword(userId,password);
			Map<String, String> map=securityTokenGenerator.generateToken(loggedInUser);
			
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("{\"message\" : \""+e.getMessage()+"\"}",HttpStatus.UNAUTHORIZED);
		}
	}

}