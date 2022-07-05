package com.selflearning.bookwithvalidations.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
	private String username;
	private String password;
}