package com.example.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.UserService;
import com.example.blog.exceptions.ResourceNotFoundExecption;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExecption("User", " id ", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExecption("User", " id " , userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userList = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userList;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExecption("User", " id " , userId));
		this.userRepo.delete(user);
		
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;		
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		return userDto;
	}

}
