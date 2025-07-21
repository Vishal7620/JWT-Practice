package com.example.jwtproject.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwtproject.Entity.User;
import com.example.jwtproject.GlobalConfig.JWTutil;
import com.example.jwtproject.Repository.UserRepository;

@Service
public class Userservice {
    
@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;


@Autowired
private JWTutil jwTutil;

public boolean registerUser(User user)
{
   User newUser=new User();

   try {
    newUser.setUserName(user.getUserName());
    String hashCode=passwordEncoder.encode(user.getPassword());
    newUser.setPassword(hashCode);
    userRepository.save(newUser);    
   } catch (Exception e) {
    // TODO: handle exception
    e.printStackTrace();
    return false;
   }
   return true;
}

public String validateUser(User user)
{
    Optional<User> currentUser=userRepository.findByUserName(user.getUserName());
    
   if(currentUser.isPresent())
   {
    User dbUser=currentUser.get();
    
    if(passwordEncoder.matches(user.getPassword(),dbUser.getPassword()))
    {
        return jwTutil.generateToken(user.getUserName());
    }
    else{
       
        return "Invalid Password";
    }

   }
   else{
     return "User Not Found";
   }

}


}
