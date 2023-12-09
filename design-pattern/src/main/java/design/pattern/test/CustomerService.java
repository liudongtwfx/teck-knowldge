package design.pattern.test;

/**
 * @author wangjun
 * @desc 编程实现，为一个客服部门设计一个电话接线员系统，不用mysql db存储实现。尽可能的用设计模式实现。
 * <p>
 * 题目描述：
 * 一个客服部门100个人，10人一组，有组长，经理；他们之间层层汇报的关系。
 * <p>
 * <p>
 * 策略1：电话来的时候，先分到每个组，组员都忙的时候可以向上传递。员工-》组长-》经理
 * <p>
 * 策略2：组长之间可以轮换。  员工-》组长-》组长......-》经理
 * <p>
 * 策略3：员工-》组长-》组长-》 员工
 */
public class CustomerService {

    private CallStrategy callStrategy;

    /**
     * 电话接线员系统的入口
     * 整体上采用了策略模式和责任链模式来实现
     *
     * @param callRequest
     */
    public void solveCallerRequest(CallRequest callRequest) {

        callStrategy.handle(callRequest);

    }

}
