package africa.semicolon.habitTracker.exception;

public class HabitAlreadyLoggedException extends RuntimeException {
    public HabitAlreadyLoggedException(String message) {
        super(message);
    }
}