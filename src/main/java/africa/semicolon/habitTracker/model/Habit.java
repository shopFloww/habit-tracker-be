package africa.semicolon.habitTracker.model;


public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;

    private LocalDateTime created_at = LocalDateTime.now();

    private String description;

    }