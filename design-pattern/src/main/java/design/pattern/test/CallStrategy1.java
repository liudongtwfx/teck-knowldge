package design.pattern.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjun
 * @desc 策略1
 */
public class CallStrategy1 implements CallStrategy {

    /**
     * 策略1的责任链
     */
    @Override
    public void handle(CallRequest callRequest) {

        List<CallRequestHandler> callRequestHandlers = new ArrayList<>();
        callRequestHandlers.add(new Employee());
        callRequestHandlers.add(new GroupLeader());
        callRequestHandlers.add(new Manager());

        CallRequestHandlerChain callRequestHandlerChain = new CallRequestHandlerChainImpl(callRequestHandlers);
        callRequestHandlerChain.requestChain(callRequest);
    }
}
