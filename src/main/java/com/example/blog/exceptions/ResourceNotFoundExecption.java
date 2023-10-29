package com.example.blog.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundExecption extends RuntimeException {
	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundExecption(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found %s :%s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
}
