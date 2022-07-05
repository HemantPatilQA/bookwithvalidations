package com.selflearning.bookwithvalidations.controllers;

import com.selflearning.bookwithvalidations.dtos.UserDTO;
import com.selflearning.bookwithvalidations.entities.User;
import com.selflearning.bookwithvalidations.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO){
        logger.info("Adding User : " + userDTO.getName());
        User userRequest = modelMapper.map(userDTO, User.class);
        User user = userService.addUser(userRequest);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        logger.info("Added User : " + userDTO.getName() + " Successfully.");
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<UserDTO> getUser(@RequestParam(value="name") String name){
        logger.info("Searching for user with name : " + name);
        User user = userService.getUser(name);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        logger.info("User with name : " + name + " Searched successfully.");
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<User> userList = userService.getAllUsers();

        List<UserDTO> userDTOList = userList.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId){
        return new ResponseEntity<>(modelMapper.map(userService.getUserById(userId), UserDTO.class), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable(value = "id") Long userId, @Valid @RequestBody UserDTO userDTO){

        User updatedUser = userService.updateUser(userId, userDTO);

        return new ResponseEntity<>(modelMapper.map(updatedUser, UserDTO.class), HttpStatus.OK);
    }
}
