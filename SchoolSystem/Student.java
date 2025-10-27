package SchoolSystem;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private int id;
    public Student(){};
    public Student(int id, String name, int age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public  String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "学号：" + this.id + ",姓名：" + this.name + ",年龄：" +  this.age;
    }
}
