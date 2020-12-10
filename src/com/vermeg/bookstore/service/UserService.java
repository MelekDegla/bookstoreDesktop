package com.vermeg.bookstore.service;

import java.util.List;

import com.vermeg.bookstore.model.User;

public interface UserService extends ICRUDService<User> {
	
	public  List<User> findByName(String name);
	
	
	
}
