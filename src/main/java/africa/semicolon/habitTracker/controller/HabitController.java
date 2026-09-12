package africa.semicolon.habitTracker.controller;


import africa.semicolon.habitTracker.model.HabitLog;
import africa.semicolon.habitTracker.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping ("/{habitId}/logs")
    public List<HabitLog> getHabitLogs(@PathVariable Long habitId){

        return habitService.getHabitLogs(habitId);
    }

}
