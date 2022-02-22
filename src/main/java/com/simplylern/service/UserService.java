package com.simplylern.service;

import com.simplylern.model.User;

public interface UserService {
	User add(User user);
	boolean validate(String username,String password);
}
