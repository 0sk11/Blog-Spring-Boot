package com.example.blog.services;


import java.util.List;


import com.example.blog.payloads.UserDto;


public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getUsers();
	void deleteUser(Integer userId);
	
}
