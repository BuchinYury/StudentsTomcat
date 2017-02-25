package models.pojo;

/**
 * Created by yuri on 25.02.17.
 */
public class Student {
    private int id;
    private String name;
    private String email;
    private int group_id;

    public Student(int id, String name, String email, int group_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.group_id = group_id;
    }

    public Student(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
