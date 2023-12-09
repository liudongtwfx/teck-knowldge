package person.liudong.dubbo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -8078553235932817515L;
    private T data;
    private boolean success;
    private String msg;
}
