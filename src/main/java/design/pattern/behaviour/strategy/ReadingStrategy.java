package design.pattern.behaviour.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class ReadingStrategy implements LearningStrategy {
    private final List<String> books;

    private static final Logger log = LoggerFactory.getLogger(ReadingStrategy.class);

    public ReadingStrategy(String book) {
        this.books = Collections.singletonList(book);
    }

    @Override
    public void learn() {
        log.info("learning with book:{}", String.join(",", books));
    }
}
