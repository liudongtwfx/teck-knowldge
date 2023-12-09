package design.pattern.test;

import java.util.List;

public class CallRequestHandlerChainImpl implements CallRequestHandlerChain {
    private List<CallRequestHandler> callRequestHandlerList;
    private int currentHanderIndex;

    public CallRequestHandlerChainImpl(List<CallRequestHandler> callRequestHandlerList) {
        this.callRequestHandlerList = callRequestHandlerList;
        currentHanderIndex = 0;
    }

    @Override
    public void requestChain(CallRequest request) {
        if (currentHanderIndex < callRequestHandlerList.size()) {
            callRequestHandlerList.get(currentHanderIndex).handler(request, this);
            currentHanderIndex++;
        }
    }
}
