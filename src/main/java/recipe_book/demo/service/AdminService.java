package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import recipe_book.demo.model.User;
import recipe_book.demo.repository.UserRepository;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

}
