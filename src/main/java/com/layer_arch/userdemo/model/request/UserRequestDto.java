package com.layer_arch.userdemo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto implements Serializable {

    private UUID id;

    @NotNull(message = "Field 'name' is required!")
    private String name;

    @Email(message = "Field 'email' must be valid.")
    private String email;

    @Size(min = 6,max = 8,message = "Field 'password' must have between 6 and 8 characters.")
    private String password;

}
