package model;




public class User {
    private int age;
    private String name;
    private String sex;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public User(int age, String name, String sex){
        this.age = age;
        this.name = name;
        this.sex = sex;
    }
}
