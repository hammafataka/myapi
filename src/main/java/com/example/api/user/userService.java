package com.example.api.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class userService {

    private final userRepository userRepository;

    @Autowired
    public userService(com.example.api.user.userRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<user> getUser(){
        return userRepository.findAll();
    }

    public void addUser(user user){
        Optional<user> userbyEmail = userRepository.finduserByEmail(user.getEmail());
        Optional<user> userByUsername = userRepository.findusersByUserName(user.getUsername());
        if(userByUsername.isPresent()){
            throw new IllegalStateException("User Name is already Taken");
        }
        if(userbyEmail.isPresent()){
            throw new IllegalStateException("Email is already taken");
        }

        userRepository.save(user);
        System.out.println(user);
    }

    public void DeleteUser(Long userID) {
        boolean exists= userRepository.existsById(userID);
        if(!exists){
            throw  new IllegalStateException("User Does not exits with id: "+userID);
        }
        userRepository.deleteById(userID);
    }

    @Transactional
    public void UpdateUser(Long userId, String name, String email) {
        user tempuser=userRepository.findById(userId).orElseThrow(
                ()->new IllegalStateException(
                 "user with id: "+userId+" does not exist"
                ));
        if(name!=null&&name.length()>0&&!Objects.equals(tempuser.getUsername(),name)){
            Optional<user> userOptional=userRepository.findusersByUserName(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("User name is already taken");
            }
            tempuser.setUsername(name);
        }
        if(email!=null&&email.length()>0&&!Objects.equals(tempuser.getEmail(),email)){
            Optional<user> userOptional=userRepository.finduserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("Email is already taken");
            }
            tempuser.setEmail(email);
        }
    }
}
