package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.exception.HabitNotFoundException;
import africa.semicolon.habitTracker.model.HabitLog;
import africa.semicolon.habitTracker.repository.HabitLogRepository;
import africa.semicolon.habitTracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;

    @Override
    public List<HabitLog> getHabitLogs(Long habitId) {

        if (!habitRepository.existsById(habitId)) {
            throw new HabitNotFoundException(habitId);
        }

        return habitLogRepository.findByHabitIdOrderByDateDesc(habitId);
    }
}
