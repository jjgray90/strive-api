package me.jjgray.strive.services;

import me.jjgray.strive.entities.User;
import me.jjgray.strive.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User createUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().length() < 1) {
            throw new RuntimeException("Please enter first name");
        }

        if (user.getLastName() == null || user.getLastName().length() < 1) {
            throw new RuntimeException("Please enter last name");
        }

        if (user.getEmail() == null || user.getEmail().length() < 1) {
            throw new RuntimeException("Please enter an email");
        }
        return userRepository.save(user);
    }


    public void deleteUserById(int id) {
        User userToDelete = userRepository.findById(id).orElse(null);
        if (userToDelete == null) {
            throw new RuntimeException("Invalid ID");
        }
        userRepository.delete(userToDelete);
    }


}
