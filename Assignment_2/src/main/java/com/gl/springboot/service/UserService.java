package com.gl.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.springboot.model.User;
import com.gl.springboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	public void save(User user) {
		// TODO Auto-generated method stub
		
	}
}
