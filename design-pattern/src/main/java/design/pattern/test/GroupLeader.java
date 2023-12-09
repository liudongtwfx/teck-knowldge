package design.pattern.test;

import lombok.Data;

import java.util.List;

/**
 * @author wangjun
 * @desc 组长；会领导多个员工
 */
@Data
public class GroupLeader extends Employee {

    /**
     * 一个组长可以管理多个员工
     */
    private List<Employee> employeeList;

    @Override
    public boolean processCallRequest(CallRequest callRequest) {
        System.out.println("直接解决客诉");
        return true;
    }
}
