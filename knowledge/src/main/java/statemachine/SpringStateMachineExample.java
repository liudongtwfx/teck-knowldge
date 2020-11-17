package statemachine;

import com.google.common.collect.Sets;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

public class SpringStateMachineExample {
    public static void main(String[] args) throws Exception {
        StateMachine<String, String> state = getState();
        state.start();
        System.out.println(state.getState());
        state.sendEvent("event1");
        System.out.println(state.getState().getId());
        state.sendEvent("event2");
        System.out.println(state.getState().getId());
    }

    private static StateMachine<String, String> getState() throws Exception {
        StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();
        builder.configureTransitions()
                .withExternal()
                .target("round1").source("start").event("event1").and()
                .withExternal()
                .target("round2").source("round1").event("event2");
        builder.configureStates().withStates().initial("start").states(Sets.newHashSet("round1", "round2"));
        return builder.build();
    }
}
