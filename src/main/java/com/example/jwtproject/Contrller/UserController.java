package com.example.jwtproject.Contrller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtproject.Entity.User;
import com.example.jwtproject.Repository.UserRepository;
import com.example.jwtproject.Service.Userservice;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Userservice userService;

    @PostMapping("/register")
    public String register(@RequestBody User user)
    {
      if(userService.registerUser(user)==true)
      {
        return "Successfully Registered !";
      }
     return " Error occured";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        String result=userService.validateUser(user);
      if(result.equals("User Not Found") || result.equals("Invalid Password"))
      {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
      }
      

      return ResponseEntity.ok().body("JWT toke :" +result);

    }
    
}
