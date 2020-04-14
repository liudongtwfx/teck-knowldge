package design.pattern.behaviour.strategy;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class ReadingStrategy implements LearningStrategy {
    private final List<String> books;

    public ReadingStrategy(String book) {
        this.books = Collections.singletonList(book);
    }

    @Override
    public void learn() {
        log.info("leaning with book:{}", String.join(",", books));
    }
}
