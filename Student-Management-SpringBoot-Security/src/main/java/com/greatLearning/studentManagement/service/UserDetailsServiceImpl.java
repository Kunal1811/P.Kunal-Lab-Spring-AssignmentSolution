package com.greatLearning.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatLearning.studentManagement.entity.User;
import com.greatLearning.studentManagement.repository.UserRepository;
import com.greatLearning.studentManagement.security.MyUserDetails;



public class UserDetailsServiceImpl implements UserDetailsService {

	   @Autowired
	    private UserRepository userRepository;
	   	private PasswordEncoder passwordEncoder;
	   	
	   	public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
	   		this.passwordEncoder = passwordEncoder;
	   	}
	     
	    @Override
	    public UserDetails loadUserByUsername(String username)
	            throws UsernameNotFoundException {
	        User user = userRepository.getUserByUsername(username);
	         
	        if (user == null) {
	            throw new UsernameNotFoundException("Could not find user");
	        }
	        user.setPassword(passwordEncoder.encode(user.getPassword())); 
	        return new MyUserDetails(user);
	    }

}
