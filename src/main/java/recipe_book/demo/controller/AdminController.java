package recipe_book.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.dto.UserListResponse;
import recipe_book.demo.model.User;
import recipe_book.demo.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserListResponse> getAllUsers() {
        List<User> users = adminService.getAllUser();
        UserListResponse response = UserListResponse.fromUsers(users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        adminService.deleteUser(userId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }




}
