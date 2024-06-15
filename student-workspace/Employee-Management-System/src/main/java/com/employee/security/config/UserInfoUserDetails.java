package com.employee.security.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.employee.entity.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserInfoUserDetails is a custom UserDetails implementation that provides user
 * details for authentication and authorization in Spring Security.
 */
public class UserInfoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String name; // Username
	private String password; // User's password
	private List<GrantedAuthority> authorities; // User's roles/authorities

	/**
	 * Constructs a UserInfoUserDetails instance from a User entity.
	 *
	 * @param userInfo The User entity containing user details
	 */
	public UserInfoUserDetails(User userInfo) {
		name = userInfo.getName();
		password = userInfo.getPassword();
//		authorities = Arrays.stream(userInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
