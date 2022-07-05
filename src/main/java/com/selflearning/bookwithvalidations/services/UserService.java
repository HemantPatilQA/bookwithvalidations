package com.selflearning.bookwithvalidations.services;

import com.selflearning.bookwithvalidations.dtos.UserDTO;
import com.selflearning.bookwithvalidations.entities.Book;
import com.selflearning.bookwithvalidations.entities.User;
import com.selflearning.bookwithvalidations.error.ResourceNotFoundException;
import com.selflearning.bookwithvalidations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    public User updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());

        User updatedBook = userRepository.save(user);

        return updatedBook;
    }
}
