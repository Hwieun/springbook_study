package com.springbook.TobySpring.one.one;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class User {
    private String id;
    private String name;
    private String password;
}
