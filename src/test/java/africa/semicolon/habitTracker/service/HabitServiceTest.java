package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.model.Habit;
import africa.semicolon.habitTracker.repository.HabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {

    @Mock
    private HabitRepository HabitRepository;

    @InjectMocks
    private HabitServiceImpl HabitService;

    private Habit habit;

    @BeforeEach
    void setUp() {
        habit = new Habit();
        habit.setId(1L);
        habit.setDescription("Drink water");
        habit.setType("Health");
    }

    @Test
    void createHabit_savesAndReturnsHabit_whenNoDuplicateExists() {
        when(HabitRepository.existsByDescriptionAndType("Drink water", "Health"))
                .thenReturn(false);
        when(HabitRepository.save(habit)).thenReturn(habit);

        Habit result = HabitService.createHabit(habit);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDescription()).isEqualTo("Drink water");
        assertThat(result.getType()).isEqualTo("Health");
        verify(HabitRepository).existsByDescriptionAndType("Drink water", "Health");
        verify(HabitRepository).save(habit);
    }

    @Test
    void createHabit_throwsIllegalArgumentException_whenDuplicateExists() {
        when(HabitRepository.existsByDescriptionAndType("Drink water", "Health"))
                .thenReturn(true);


        assertThatThrownBy(() -> HabitService.createHabit(habit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("already exists");

        verify(HabitRepository, never()).save(any(Habit.class));
    }

    @Test
    void getAllHabits_returnsAllHabits_whenHabitsExist() {
        Habit second = new Habit();
        second.setId(2L);
        second.setDescription("Read");
        second.setType("Learning");
        when(HabitRepository.findAll()).thenReturn(Arrays.asList(habit, second));

        List<Habit> result = HabitService.getAllHabits();

        assertThat(result).hasSize(2).containsExactly(habit, second);
        verify(HabitRepository).findAll();
    }

    @Test
    void getAllHabits_returnsEmptyList_whenNoHabitsExist() {
        when(HabitRepository.findAll()).thenReturn(Collections.emptyList());

        List<Habit> result = HabitService.getAllHabits();

        assertThat(result).isEmpty();
        verify(HabitRepository).findAll();
    }


    @Test
    void getHabitById_returnsHabit_whenIdExists() {

        when(HabitRepository.findById(1L)).thenReturn(Optional.of(habit));


        Habit result = HabitService.getHabitById(1L);


        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDescription()).isEqualTo("Drink water");
        assertThat(result.getType()).isEqualTo("Health");
        verify(HabitRepository).findById(1L);
    }

    @Test
    void getHabitById_throwsNoSuchElementException_whenIdDoesNotExist() {

        when(HabitRepository.findById(99L)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> HabitService.getHabitById(99L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("99");

        verify(HabitRepository).findById(99L);
    }


    @Test
    void updateHabit_overwritesAndReturnsHabit_whenIdExists() {
        Habit updates = new Habit();
        updates.setDescription("Drink more water");
        updates.setType("Wellness");

        when(HabitRepository.findById(1L)).thenReturn(Optional.of(habit));
        when(HabitRepository.save(habit)).thenReturn(habit);

        Habit result = HabitService.updateHabit(1L, updates);

        assertThat(result.getDescription()).isEqualTo("Drink more water");
        assertThat(result.getType()).isEqualTo("Wellness");
        verify(HabitRepository).findById(1L);
        verify(HabitRepository).save(habit);
    }

    @Test
    void updateHabit_throwsNoSuchElementException_whenIdDoesNotExist() {
        Habit updates = new Habit();
        updates.setDescription("Doesn't matter");
        updates.setType("Doesn't matter");

        when(HabitRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> HabitService.updateHabit(99L, updates))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("99");

        verify(HabitRepository, never()).save(any(Habit.class));
    }


    @Test
    void deleteHabit_deletesHabit_whenIdExists() {
        when(HabitRepository.existsById(1L)).thenReturn(true);

        HabitService.deleteHabit(1L);

        verify(HabitRepository).existsById(1L);
        verify(HabitRepository).deleteById(1L);
    }

    @Test
    void deleteHabit_throwsNoSuchElementException_whenIdDoesNotExist() {
        when(HabitRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> HabitService.deleteHabit(99L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("99");

        verify(HabitRepository, never()).deleteById(anyLong());
    }
}