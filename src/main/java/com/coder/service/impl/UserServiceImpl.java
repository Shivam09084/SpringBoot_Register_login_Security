package com.coder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.coder.entity.User;
import com.coder.repository.UserRepository;
import com.coder.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
		User save = userRepo.save(user);
		
		return save;
	}

	@Override
	public void removeSessionMessage() {
		
		//This code is used for remove message after refresh the page [like suppose we register a user after that show a msg register successfully that case we have to refresh this code after that register successfully msg not show)
		
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

}
