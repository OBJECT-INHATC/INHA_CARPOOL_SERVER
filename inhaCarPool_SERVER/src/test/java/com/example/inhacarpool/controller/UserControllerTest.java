package com.example.inhacarpool.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.inhacarpool.user.UserController;
import com.example.inhacarpool.user.UserService;
import com.example.inhacarpool.user.data.dto.UserSignUpDto;

@WebMvcTest(UserController.class)
//@AutoConfigureMockMvc // MockMvc를 빌더 없이 주입받을때 필요한 어노테이션
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// UserController에서 사용하는 UserService를 MockBean으로 주입
	@MockBean
	UserService userService;

	@Test
	void getUserTest() {

		doNothing().when(userService).saveUser(new UserSignUpDto(
			"1234567891234567891234567891",
			"nickname",
			"email")
		);

	}

}
