package com.hzyazilimci.security.authDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hzyazilimci
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
}
