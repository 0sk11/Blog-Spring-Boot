package com.example.blog.controllers;

import java.util.List;

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

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
	@Autowired
	CategoryService categoryService;
	@PostMapping("/")
	public ResponseEntity <CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto> (createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity <CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer id){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,id);
		return new ResponseEntity<CategoryDto> (updateCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <ApiResponse> updateCategory(@PathVariable Integer id){
		this.categoryService.DeleteCategory(id);
		return new ResponseEntity<ApiResponse> (new ApiResponse("Category is deleted successfully !!",true),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <CategoryDto> getCategory(@PathVariable Integer id){
		CategoryDto categoryDto = this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity <List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categoryDtos = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(categoryDtos,HttpStatus.OK);
	}
}
