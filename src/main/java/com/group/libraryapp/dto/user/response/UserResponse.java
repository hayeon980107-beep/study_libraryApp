package com.group.libraryapp.dto.user.response;

public class UserResponse {

    private long id;
    private String name;
    private  Integer age;

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public UserResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
