package es.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements EsEntity {
    public static final String INDEX = "student";
    private int age;
    private String name;
    private String studentNo;

    public Student(int age, String name, String studentNo) {
        this.age = age;
        this.name = name;
        this.studentNo = studentNo;
    }

    public Student() {
    }

    @Override
    public String getId() {
        return studentNo;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getStudentNo() {
        return this.studentNo;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Student)) return false;
        final Student other = (Student) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAge() != other.getAge()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$studentNo = this.getStudentNo();
        final Object other$studentNo = other.getStudentNo();
        if (this$studentNo == null ? other$studentNo != null : !this$studentNo.equals(other$studentNo)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Student;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAge();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $studentNo = this.getStudentNo();
        result = result * PRIME + ($studentNo == null ? 43 : $studentNo.hashCode());
        return result;
    }

    public String toString() {
        return "Student(age=" + this.getAge() + ", name=" + this.getName() + ", studentNo=" + this.getStudentNo() + ")";
    }
}
