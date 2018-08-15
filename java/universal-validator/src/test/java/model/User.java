package model;




public class User {
    private int age;
    private String name;
    private String sex;
    private String password;
    private String passwordConfirm;
    private String email;
    private String website;

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public User(int age, String name, String sex,String password,String passwordConfirm,String email, String website){
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.password=password;
        this.passwordConfirm = passwordConfirm;
        this.email=email;
        this.website = website;
    }
}
