package com.team5.userAuthentication.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.userAuthentication.exeptions.*;
import com.team5.userAuthentication.repository.*;
import com.team5.userAuthentication.model.*;
import org.springframework.data.repository.CrudRepository;



@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
	
private UserAutheticationRepository userRepository;
	
	@Autowired
	public UserAuthenticationServiceImpl(UserAutheticationRepository userRepository) {
		this.userRepository=userRepository;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> userResult=userRepository.findById(user.getUserId());
		if(userResult.isPresent())
		{
			throw new UserAlreadyExistsException("User with Id: "+user.getUserId()+" already exists!");
		}
		
		userRepository.save(user);
		return true;
	}
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user=userRepository.findByUserIdAndUserPassword(userId, password);
		if(user==null){
			throw new UserNotFoundException("UserId and Password mismatch!");
		}
		return user;
	}

}
