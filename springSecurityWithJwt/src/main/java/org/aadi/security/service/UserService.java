package org.aadi.security.service;

import org.aadi.security.model.User;

public interface UserService {
	
	Integer saveUser(User user);
	
	User findByUsername(String username);

}
