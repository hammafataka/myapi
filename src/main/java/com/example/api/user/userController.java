package com.example.api.user;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class userController {
    private final userService userService;

    @Autowired
    public userController(com.example.api.user.userService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<user> getStudent(){
        return userService.getUser();
    }

    @PostMapping
    public void RegisterUser(@RequestBody user user){
        userService.addUser(user);

    }
    @PutMapping(path = "{UserId}")
    public void  UpdateUser(
            @PathVariable("UserId")Long userId,
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String email
                                                ){
        userService.UpdateUser(userId,name,email);
    }
    @DeleteMapping(path ="{userID}")
    public void DeleteUser(@PathVariable("userID")Long userID){
        userService.DeleteUser(userID);
    }
}
