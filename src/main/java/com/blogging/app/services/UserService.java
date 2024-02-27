package com.blogging.app.services;

import com.blogging.app.enitities.User;
import com.blogging.app.payloads.UserDto;

import java.util.List;

public interface UserService {


   UserDto createUser(UserDto userDto);

   UserDto updateUser(UserDto userDto,Integer userId);
   UserDto getUserById(Integer userId);
   List<UserDto> getAllUsers();

   void deleteUser(Integer userId);

}
