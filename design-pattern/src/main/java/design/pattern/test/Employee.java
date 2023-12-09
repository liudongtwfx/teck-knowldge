package design.pattern.test;

import lombok.Data;

/**
 * @author wangjun
 * @desc 员工
 */
@Data
public class Employee implements CallRequestHandler {

    /**
     * 员工id
     */
    private Long id;
    /**
     * 员工姓名
     */
    private String name;

    /**
     * 下一个处理者
     */
    private Employee next;

    /**
     * 设置下一个处理者
     *
     * @param next
     */
    public void setNextHandler(Employee next) {
        this.next = next;
    }


    /**
     * 处理客诉
     *
     * @param callRequest
     * @return
     */
    public boolean processCallRequest(CallRequest callRequest) {
        System.out.println("直接解决客诉");
        return true;
    }

    @Override
    public void handler(CallRequest callRequest, CallRequestHandlerChain handlerChain) {
        // 可以处理请求
        if (processCallRequest(callRequest)) {
            return;
        }
        // 交由下一个处理
        handlerChain.requestChain(callRequest);
    }
}
