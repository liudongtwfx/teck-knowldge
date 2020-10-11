package design.pattern.behaviour.strategy;

public interface LearningStrategy {
    void learn();

    default boolean canLearn() {
        return true;
    }
}
