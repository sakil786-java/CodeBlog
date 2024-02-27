package com.blogging.app.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 4,message = " Name must be min of 4 characters")
    private String name;
    @Email(message = "Email Address is not valid")
    private String email;
    @NotEmpty

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must contain at least 8 characters, one digit, one lowercase letter, one uppercase letter, and one special character.")
    private String password;
    @NotEmpty
    private String about;
}
