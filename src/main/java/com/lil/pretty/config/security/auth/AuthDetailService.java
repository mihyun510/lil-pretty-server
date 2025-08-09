package com.lil.pretty.config.security.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lil.pretty.domain.user.model.User;
import com.lil.pretty.domain.user.repository.UserRepository;

@Service
public class AuthDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usId) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsId(usId);

		//UserBuilder builder = null;
		if (user.isPresent()) {
			return new AuthDetail(user.get()); // CustomUserDetails 반환
//			User currentUser = user.get();
//			builder = org.springframework.security.core.userdetails.User.withUsername(userid);
//			builder.password(currentUser.getPassword());
//			builder.roles(currentUser.getRole());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		//return builder.build();
	}

	/**
	 * 현재 인증된 사용자의 정보를 반환합니다.
	 *
	 * @return 현재 인증된 사용자
	 * @throws RuntimeException 인증된 사용자가 없을 경우 예외 발생
	 */
	public User getAuthenticatedUser() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication != null && authentication.isAuthenticated()
	        && !(authentication instanceof AnonymousAuthenticationToken)) {

	        Object principal = authentication.getPrincipal();

	        if (principal instanceof User) {
	            return (User) principal;
	        } else {
	            throw new RuntimeException("Authenticated principal is not of type User");
	        }
	    }

	    throw new RuntimeException("No authenticated user found");
	}

}