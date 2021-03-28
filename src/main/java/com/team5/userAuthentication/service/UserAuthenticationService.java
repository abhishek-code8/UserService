package com.team5.userAuthentication.service;
import com.team5.userAuthentication.exeptions.*;
import com.team5.userAuthentication.model.User;

public interface UserAuthenticationService {
    
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
    boolean saveUser(User user) throws UserAlreadyExistsException;
}
