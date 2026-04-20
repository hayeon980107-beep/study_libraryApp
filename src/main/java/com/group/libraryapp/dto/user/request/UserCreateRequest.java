package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age; // null 가능한 integer

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
