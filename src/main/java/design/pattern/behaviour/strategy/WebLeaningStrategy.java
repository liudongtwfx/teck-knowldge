package design.pattern.behaviour.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebLeaningStrategy implements LearningStrategy {

    private final String website;

    public WebLeaningStrategy(String website) {
        this.website = website;
    }

    @Override
    public void learn() {
        log.info("learning with website:{}", website);
    }
}
