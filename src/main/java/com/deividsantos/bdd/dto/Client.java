package com.deividsantos.bdd.dto;

public class Client {

    private Integer code;
    private String name;
    private String phone;

    public Client() {
    }

    public Client(Integer code, String name, String phone) {
        this.code = code;
        this.name = name;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "Client{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
