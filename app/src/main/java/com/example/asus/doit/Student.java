package com.example.asus.doit;

public class Student {
    private String name;
    private String roll;
    private String phone;
    private String hometown;
    public Student(String name, String roll, String phone, String hometown) {
        this.name=name;
        this.roll=roll;
        this.phone=phone;
        this.hometown=hometown;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    public String getRoll()
    {
        return roll;
    }
    public void setRoll(String roll)
    {
        this.roll=roll;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone=phone;
    }

    public String getHometown()
    {
        return hometown;
    }
    public void setHometown(String hometown)
    {
        this.hometown=hometown;
    }
}
