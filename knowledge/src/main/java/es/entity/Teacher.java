package es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements EsEntity {
    public static final String INDEX = "teacher";
    public static final String TYPE = "teacher";

    private int age;
    private String teacherId;
    private String teacherName;

    @Override
    public String getId() {
        return teacherId;
    }
}
