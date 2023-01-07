package com.hzyazilimci.security.service.abstracts;

import com.hzyazilimci.security.authDtos.AuthenticationDto;
import com.hzyazilimci.security.authDtos.AuthenticationResponse;
import com.hzyazilimci.security.authDtos.RegisterDto;

/**
 * @author hzyazilimci
 */

public interface UserService {

    AuthenticationResponse register(RegisterDto registerDto);
    AuthenticationResponse authenticate(AuthenticationDto authenticationDto);
}
