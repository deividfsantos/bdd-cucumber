package com.deividsantos.bdd.output;

public class ClientOutput {

    private String name;
    private String phone;
    private String nationality;

    public ClientOutput() {
    }

    public ClientOutput(String name, String phone, String nationality) {
        this.name = name;
        this.phone = phone;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "ClientOutput{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
