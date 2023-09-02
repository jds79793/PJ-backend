package com.spring.user.controller;

import com.spring.user.DTO.AddUserRequestDTO;
import com.spring.user.entity.User;
import com.spring.user.service.UserDetailService;
import com.spring.user.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userService;
    private final UserDetailService userDetailService;


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody AddUserRequestDTO requestDTO){
        userService.save(requestDTO); // 현재 userService에 email, pw뿐이라 수정 요망
        return ResponseEntity.ok("회원가입 성공");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AddUserRequestDTO addUserRequestDTO){
        String requestEmail = addUserRequestDTO.getEmail();
        String requestPW = addUserRequestDTO.getPassword();

        String userPW = userDetailService.loadUserByUsername(requestEmail).getPassword();

        return ResponseEntity.ok("로그인 성공");

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    // 유저 정보 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
