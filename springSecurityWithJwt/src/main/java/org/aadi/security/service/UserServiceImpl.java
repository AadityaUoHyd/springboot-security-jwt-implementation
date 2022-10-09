package org.aadi.security.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aadi.security.model.User;
import org.aadi.security.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	//save
	@Override
	@Transactional
	public Integer saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user).getId();
	}

	//get by username
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		Optional<User> user=userRepository.findByUsername(username);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException 
	{
		User userFromExistingDb=findByUsername(username); 
		if(userFromExistingDb==null) 
			throw new UsernameNotFoundException(
					new StringBuffer()
					.append("User name ")
					.append(username)
					.append(" not found!")
					.toString());

		List<GrantedAuthority> authorities=
				userFromExistingDb.getRoles()
				.stream()
				.map(role->new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(
				username, 
				userFromExistingDb.getPassword(), 
				authorities);
	}

}