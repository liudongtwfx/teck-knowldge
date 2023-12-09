package design.pattern.test;

/**
 * @author wangjun
 * @desc 接听电话处理策略
 */
public interface CallStrategy {

    public void handle(CallRequest callRequest);
}
