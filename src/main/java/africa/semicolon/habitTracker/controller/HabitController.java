package africa.semicolon.habitTracker.controller;

import africa.semicolon.habitTracker.dto.request.CreateUserRequest;
import africa.semicolon.habitTracker.dto.response.userResponse;
import africa.semicolon.habitTracker.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class HabitController {


    private HabitService habitService;

    @PostMapping
    public ResponseEntity<userResponse> createUser(@RequestBody CreateUserRequest request){

        userResponse response = new userResponse();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}





