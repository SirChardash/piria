package sirchardash.piria.museumtour.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.models.User;
import sirchardash.piria.museumtour.services.UserService;

import java.util.List;

@RestController
class UserController {

    private final UserService service;

    @Autowired
    UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/admin/users")
    List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/admin/user/{userId}/resetPassword")
    void resetPassword(@PathVariable String userId) {
        service.resetPassword(userId);
    }

    @PostMapping("/admin/user/{userId}/disable")
    void disableUser(@PathVariable String userId) {
        service.disable(userId);
    }

}
