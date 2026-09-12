package africa.semicolon.habitTracker.controller;

import africa.semicolon.habitTracker.model.Habit;
import africa.semicolon.habitTracker.model.HabitLog;
import africa.semicolon.habitTracker.repository.HabitLogRepository;
import africa.semicolon.habitTracker.repository.HabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitLogRepository habitLogRepository;

    @BeforeEach
    void setUp() {
        habitLogRepository.deleteAll();
        habitRepository.deleteAll();
    }

    @Test
    void getHabitLogs_returnsLogsSortedNewestFirst() throws Exception {

        Habit habit = habitRepository.save(new Habit());

        habitLogRepository.save(
                HabitLog.builder()
                        .date(LocalDate.of(2026, 9, 10))
                        .habit(habit)
                        .build()
        );

        habitLogRepository.save(
                HabitLog.builder()
                        .date(LocalDate.of(2026, 9, 12))
                        .habit(habit)
                        .build()
        );

        var result = mockMvc.perform(
                        get("/habits/{habitId}/logs", habit.getId())
                ).andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertThat(response).contains("2026-09-12");
        assertThat(response).contains("2026-09-10");

        int newestDate = response.indexOf("2026-09-12");
        int oldestDate = response.indexOf("2026-09-10");

        assertThat(newestDate).isLessThan(oldestDate);
    }

    @Test
    void getHabitLogs_whenHabitHasNoLogs_returnsEmptyArray() throws Exception {

        Habit habit = habitRepository.save(new Habit());

        var result = mockMvc.perform(
                        get("/habits/{habitId}/logs", habit.getId())
                ).andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertThat(response).isEqualTo("[]");
    }

    @Test
    void getHabitLogs_whenHabitDoesNotExist_returns404() throws Exception {

        mockMvc.perform(
                get("/habits/{habitId}/logs", 999999L)
        ).andExpect(status().isNotFound());
    }
}

