package com.czh.pojo;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private Bag bag;
}
