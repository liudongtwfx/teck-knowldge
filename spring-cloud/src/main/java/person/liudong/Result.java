package person.liudong;

import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private boolean success;

    private T data;

    private String msg;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
