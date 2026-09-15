package africa.semicolon.habitTracker.controller;

import africa.semicolon.habitTracker.dtos.HabitRequestDto;
import africa.semicolon.habitTracker.dtos.HabitResponseDto;
import africa.semicolon.habitTracker.model.Habit;
import africa.semicolon.habitTracker.service.HabitService;
import africa.semicolon.habitTracker.service.HabitServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;



@RestController
@AllArgsConstructor
public class HabitController {

    private final HabitService habitService;
    private final ModelMapper modelMapper;


    @GetMapping("/users/{userId}/habits")
    public ResponseEntity<List<HabitResponseDto>> findAllHabitsByUserId(@PathVariable Long userId) {
        List <Habit> habits = habitService.findAllHabits(userId);
        Type type = new TypeToken<List<HabitResponseDto>>(){}.getType();
        return ResponseEntity.ok(modelMapper.map(habits, type));
    }

    @GetMapping("/habits/{id}")
    public ResponseEntity<HabitResponseDto> findById(@PathVariable Long id){
        Habit habit = habitService.findHabitById(id);
        return ResponseEntity.ok(modelMapper.map(habit, HabitResponseDto.class));
    }

    @PostMapping("/users/{userId}/habits")
    public ResponseEntity<HabitResponseDto> createHabit(@RequestBody HabitRequestDto habitRequestDto, @PathVariable Long userId){
        Habit habit = habitService.createHabit(habitRequestDto, userId);
        return ResponseEntity.ok(modelMapper.map(habit, HabitResponseDto.class));
    }
}
