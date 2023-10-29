package com.example.blog.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.UserDto;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")	
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);
		System.out.println("Create-User ran");
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return ResponseEntity.ok(Map.of("message","User deleted successfully"));
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.ok(this.userService.getUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAllUsers(@PathVariable(name = "id") Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	

}
