package thrift;

import lombok.extern.slf4j.Slf4j;
import thrift.gen.shared.SharedStruct;
import thrift.gen.tutorial.Calculator;
import thrift.gen.tutorial.InvalidOperation;
import thrift.gen.tutorial.Work;

import java.util.HashMap;

@Slf4j
public class CalculatorHandler implements Calculator.Iface {
    private final HashMap<Integer, SharedStruct> logMap;

    public CalculatorHandler() {
        logMap = new HashMap<>();
    }

    public void ping() {
        log.info("ping()");
    }

    public int add(int n1, int n2) {
        log.info("add(" + n1 + "," + n2 + ")");
        return n1 + n2;
    }

    public int calculate(int logId, Work work) throws InvalidOperation {
        log.info("calculate(" + logId + ", {" + work.op + "," + work.num1 + "," + work.num2 + "})");
        int val;
        switch (work.op) {
            case ADD:
                val = work.num1 + work.num2;
                break;
            case SUBTRACT:
                val = work.num1 - work.num2;
                break;
            case MULTIPLY:
                val = work.num1 * work.num2;
                break;
            case DIVIDE:
                if (work.num2 == 0) {
                    InvalidOperation io = new InvalidOperation();
                    io.whatOp = work.op.getValue();
                    io.why = "Cannot divide by 0";
                    throw io;
                }
                val = work.num1 / work.num2;
                break;
            default:
                InvalidOperation io = new InvalidOperation();
                io.whatOp = work.op.getValue();
                io.why = "Unknown operation";
                throw io;
        }

        SharedStruct entry = new SharedStruct();
        entry.key = logId;
        entry.value = Integer.toString(val);
        logMap.put(logId, entry);

        return val;
    }

    public SharedStruct getStruct(int key) {
        log.info("getStruct(" + key + ")");
        return logMap.get(key);
    }

    public void zip() {
        log.info("zip()");
    }

}
