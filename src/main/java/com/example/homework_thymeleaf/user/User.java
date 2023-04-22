package com.example.homework_thymeleaf.user;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uuid;
    private String name;
    private String description;
    private String gender;
    private String address;
    private String email;
    private String image;
}
