package me.jjgray.strive;


import me.jjgray.strive.entities.Activity;
import me.jjgray.strive.entities.User;
import me.jjgray.strive.services.ActivityService;
import me.jjgray.strive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.parseInt;

@CrossOrigin
@RestController
public class StriveController {

    @Autowired
    UserService userService;

    @Autowired
    ActivityService activityService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getById(parseInt(id));
        HttpStatus status = user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(user);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getByEmail(email);
        HttpStatus status = user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(user);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/activity")
    public ResponseEntity createActivity(@RequestBody Activity activity) {
        try {
            Activity newActivity = activityService.createActivity(activity);
            return ResponseEntity.status(HttpStatus.OK).body(newActivity);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user/activities")
    public ResponseEntity<List<Activity>> getActivitiesByUser(@RequestBody User user) {
        List<Activity> activitiesList = activityService.findActivitiesByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(activitiesList);
    }

    @GetMapping("/user/activities/{id}")
    public ResponseEntity<List<Activity>> getActivitiesByUser(@PathVariable String id) {
        User currentUser = userService.getById(parseInt(id));

        List<Activity> activitiesList = activityService.findActivitiesByUser(currentUser)
                .stream()
                .sorted((a, b) -> b.getId() - a.getId())
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(activitiesList);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id) {
        try {
            userService.updateUserById(user, parseInt(id));
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        try {
            userService.deleteUserById(parseInt(id));
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("user/activity/delete/{id}")
    public ResponseEntity deleteActivity(@PathVariable String id) {
        try {
            activityService.deleteActivityById(parseInt(id));
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
