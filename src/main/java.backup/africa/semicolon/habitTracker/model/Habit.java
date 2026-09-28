package africa.semicolon.habitTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class Habit {
    @Id
    private Long Id;
    private String describe;
    private String Type;
}
