package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModelDto {
    private Long id;
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String postalCode;
    private String token;
    private String username;
    private String oldPassword;
    private String password;
    private String repeatPassword;
}
