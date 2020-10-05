package design.pattern.behaviour.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebLeaningStrategy implements LearningStrategy {

    private static final Logger log = LoggerFactory.getLogger(WebLeaningStrategy.class);

    private final String website;

    public WebLeaningStrategy(String website) {
        this.website = website;
    }

    @Override
    public void learn() {
        log.info("learning with website:{}", website);
    }
}
