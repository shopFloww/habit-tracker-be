package africa.semicolon.habitTracker.controller;

import africa.semicolon.habitTracker.dto.request.CreateUserRequest;
import africa.semicolon.habitTracker.dto.response.UserResponse;
import africa.semicolon.habitTracker.service.HabitService;
import africa.semicolon.habitTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class HabitController {


    private HabitService habitService;
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request){

        UserResponse response = new UserResponse();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/id")
    public ResponseEntity<UserResponse> getUserById(@RequestParam int id){

        UserResponse response = new UserResponse();
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}





