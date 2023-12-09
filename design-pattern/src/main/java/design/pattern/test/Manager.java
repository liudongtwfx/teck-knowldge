package design.pattern.test;

import lombok.Data;

import java.util.List;

/**
 * @author wangjun
 * @desc 经理; 会管理多个组长；
 */
@Data
public class Manager extends Employee {

    /**
     * 经理管理多个组长
     */
    private List<GroupLeader> groupLeaderList;

    @Override
    public boolean processCallRequest(CallRequest callRequest) {
        System.out.println("直接解决客诉");
        return true;
    }


}
