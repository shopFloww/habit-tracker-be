package africa.semicolon.habitTracker.dtos;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
public class HabitRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

}

