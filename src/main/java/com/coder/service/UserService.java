package com.coder.service;

import com.coder.entity.User;

public interface UserService {

	public User saveUser(User user);
	public void removeSessionMessage();
}
