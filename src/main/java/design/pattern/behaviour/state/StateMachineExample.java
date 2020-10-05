package design.pattern.behaviour.state;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;

public class StateMachineExample {
    public static void main(String[] args) {
        new StateMachineExample().test();
    }

    private void test() {
        StateMachineConfig<State, Trigger> phoneCallConfig = new StateMachineConfig<>();

        phoneCallConfig.configure(State.OffHook)
                .permit(Trigger.CallDialed, State.Ringing, () -> System.out.println("haha"));

        phoneCallConfig.configure(State.Ringing)
                .permit(Trigger.HungUp, State.OffHook)
                .permit(Trigger.CallConnected, State.Connected);

// this example uses Java 8 method references
// a Java 7 example is provided in /examples
        phoneCallConfig.configure(State.Connected)
                .onEntry(this::startCallTimer)
                .onExit(this::stopCallTimer)
                .permit(Trigger.LeftMessage, State.OffHook)
                .permit(Trigger.HungUp, State.OffHook)
                .permit(Trigger.PlacedOnHold, State.OnHold);

// ...

        StateMachine<State, Trigger> phoneCall =
                new StateMachine<>(State.OffHook, phoneCallConfig);

        phoneCall.fire(Trigger.CallDialed);
        if (phoneCall.getState() != State.Ringing) {
            throw new RuntimeException("phoneCall error");
        }

        phoneCall.fire(Trigger.CallConnected);
        phoneCall.fire(Trigger.LeftMessage);
    }

    private void stopCallTimer() {
        System.out.println("stop call timer");
    }

    private void startCallTimer() {
        System.out.println("start call timer");
    }


    enum State {
        OffHook, Connected, OnHold, Ringing
    }

    enum Trigger {
        HungUp, CallDialed, CallConnected, LeftMessage, PlacedOnHold
    }
}
