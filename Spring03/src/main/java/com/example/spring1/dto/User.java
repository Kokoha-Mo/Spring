package com.example.spring1.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Getter
// @Setter
// @ToString
// @Builder
@Data // 包含Getter/Setter/ToString......
public class User {
    private int id;
    private String name;
    private boolean gender;
}
