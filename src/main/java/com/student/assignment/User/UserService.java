package com.student.assignment.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        User existingUser = userOptional.get();
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setDescription(user.getDescription());
            existingUser.setGender(user.getGender());
            existingUser.setDate_of_birth(user.getDate_of_birth());
        }
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
    }
    
}
