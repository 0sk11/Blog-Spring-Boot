package com.example.blog.exceptions;

public class ConstraintViolationException extends RuntimeException{
	String resourceName;
	String fieldName;
	long fieldValue;
	public ConstraintViolationException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s Exception found at %s :%s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
