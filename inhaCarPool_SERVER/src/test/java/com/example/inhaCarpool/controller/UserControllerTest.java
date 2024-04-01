package com.example.inhaCarpool.controller;


import com.example.inhaCarpool.user.UserController;
import com.example.inhaCarpool.user.UserService;
import com.example.inhaCarpool.user.data.UserSignUpDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.doNothing;

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

        doNothing().when(userService).saveUser(new UserSignUpDTO(
                "1234567891234567891234567891",
                "nickname",
                "email")
        );

        ResponseEntity<Map<String, String>> responseEntity = new UserController(userService).saveUser(new UserSignUpDTO(
                "1234567891234567891234567891",
                "nickname",
                "email")
        );

    }



}
