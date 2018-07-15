package com.deividsantos.bdd.dto;

public class Client {

    private Integer code;
    private String name;
    private String phone;
    private String nationality;

    public Client() {
    }

    public Client(Integer code, String name, String phone, String nationality) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.nationality = nationality;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
        return "Client{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
