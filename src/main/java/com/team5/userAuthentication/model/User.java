package com.team5.userAuthentication.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @Column(name="userId")
    private String userId;
    @Column(name="userPassword")
    private String userPassword;
    @Column(name="userEmail")
    private String userEmail;
//    @Column(name="profilePicture")
//    private String profilePictureUrl; 
    public User() {
    	
    }
    
	public User(String userId, String userPassword, String userEmail) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		//this.profilePictureUrl = profilePictureUrl;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPassword=" + userPassword + ", userEmail=" + userEmail
				 + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getEmail() {
		return userEmail;
	}
	public void setEmail(String userEmail) {
		this.userEmail = userEmail;
	}
//	public String getProfilePictureUrl() {
//		return profilePictureUrl;
//	}
//	public void setprofilePictureUrl(String profilePictureUrl) {
//		this.profilePictureUrl = profilePictureUrl;
//	}
    
}
