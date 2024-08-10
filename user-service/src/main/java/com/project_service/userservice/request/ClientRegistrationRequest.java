package com.project_service.userservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record ClientRegistrationRequest(
        @NotBlank(message = "Can not be empty")
        @Pattern(regexp = "^\\p{L}+$", message = "First name must contain only letters")
        String username,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email should be valid" )
        String email,

        @NotBlank(message = "Can not be empty")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\p{Punct})[a-zA-Z\\d\\p{Punct}]*$",
                message = "Password is required to contain only English alphabet characters "
                                                         + "at least one uppercase and one lowercase, also one digit and "
                                                         + "one special character")
        @Size(min = 5, max = 20, message = "Password mast by between 5 and 20 characters")
        String password) {
}
