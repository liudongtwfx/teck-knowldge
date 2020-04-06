package es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements EsEntity {
    private int age;
    private String name;
    private String studentNo;

    @Override
    public String getId() {
        return studentNo;
    }
}
