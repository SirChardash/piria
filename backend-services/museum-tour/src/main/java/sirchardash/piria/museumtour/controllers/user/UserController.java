package sirchardash.piria.museumtour.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.jpa.TrackingLog;
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

    @GetMapping("/admin/user/logs")
    List<TrackingLog> logs() {
        return service.logs();
    }

    @GetMapping(value = "/admin/user/logs/pdf", produces = "application/pdf")
    byte[] logsAsPdf() {
        return service.logsAsPdf();
    }

    @GetMapping("/admin/user/stats")
    StatsResponse userStats() {
        return new StatsResponse(
                service.activeUsersPerHours(),
                service.activeUsers()
        );
    }

}
