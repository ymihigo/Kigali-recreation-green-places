package com.mihigo.main.payloads;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Component
public class ApiResponse<T> {
	int recordCount;
	T response;

	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiResponse(int recordCount, T response) {
		this.recordCount = recordCount;
		this.response = response;
	}
	
	

}
