package es.entity;

public class Teacher implements EsEntity {
    public static final String INDEX = "teacher";
    public static final String TYPE = "teacher";

    private int age;
    private String teacherId;
    private String teacherName;

    public Teacher(int age, String teacherId, String teacherName) {
        this.age = age;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public Teacher() {
    }

    @Override
    public String getId() {
        return teacherId;
    }

    public int getAge() {
        return this.age;
    }

    public String getTeacherId() {
        return this.teacherId;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Teacher)) return false;
        final Teacher other = (Teacher) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAge() != other.getAge()) return false;
        final Object this$teacherId = this.getTeacherId();
        final Object other$teacherId = other.getTeacherId();
        if (this$teacherId == null ? other$teacherId != null : !this$teacherId.equals(other$teacherId)) return false;
        final Object this$teacherName = this.getTeacherName();
        final Object other$teacherName = other.getTeacherName();
        if (this$teacherName == null ? other$teacherName != null : !this$teacherName.equals(other$teacherName))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Teacher;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAge();
        final Object $teacherId = this.getTeacherId();
        result = result * PRIME + ($teacherId == null ? 43 : $teacherId.hashCode());
        final Object $teacherName = this.getTeacherName();
        result = result * PRIME + ($teacherName == null ? 43 : $teacherName.hashCode());
        return result;
    }

    public String toString() {
        return "Teacher(age=" + this.getAge() + ", teacherId=" + this.getTeacherId() + ", teacherName=" + this.getTeacherName() + ")";
    }
}
