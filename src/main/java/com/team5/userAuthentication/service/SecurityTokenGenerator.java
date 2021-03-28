package com.team5.userAuthentication.service;

import java.util.Map;

import com.team5.userAuthentication.model.*;

public interface SecurityTokenGenerator {

	Map<String,String> generateToken(User user);
}
