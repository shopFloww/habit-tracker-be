package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.model.HabitLog;

public interface HabitLogService {

    HabitLog logCompletion(Long habitId);
}