package design.pattern.test;

public interface CallRequestHandler {
    void handler(CallRequest callRequest, CallRequestHandlerChain handlerChain);
}
