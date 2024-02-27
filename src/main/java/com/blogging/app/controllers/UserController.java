package com.blogging.app.controllers;

import com.blogging.app.payloads.ApiResponse;
import com.blogging.app.payloads.UserDto;
import com.blogging.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto> >getAllUser() {
        List<UserDto> allUsers = this.userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser( @PathVariable Integer userId) {
        UserDto userDto = this.userService.getUserById(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
        UserDto updatedUser = this.userService.updateUser(userDto,userId);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser( @PathVariable Integer userId) {
       this.userService.deleteUser(userId);
       return new ResponseEntity(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
    }



}
