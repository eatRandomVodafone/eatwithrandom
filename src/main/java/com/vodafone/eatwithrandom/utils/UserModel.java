package com.vodafone.eatwithrandom.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vodafone.eatwithrandom.model.User;

public class UserModel {
	
	public static User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}

