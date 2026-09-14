package africa.semicolon.habitTracker.model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Habit {

    @Id
    @ManyToOne
    private Long id;

    private String name = "User-" + id;

    private LocalDateTime created_at = LocalDateTime.now();
    
    private String description;

    public void habit( String description ){
        this.description = description;
    }
}