package com.hzyazilimci.security.service.concretes;

import com.hzyazilimci.security.authDtos.AuthenticationDto;
import com.hzyazilimci.security.authDtos.AuthenticationResponse;
import com.hzyazilimci.security.authDtos.RegisterDto;
import com.hzyazilimci.security.config.JwtManager;
import com.hzyazilimci.security.repository.UserRepository;
import com.hzyazilimci.security.service.abstracts.UserService;
import com.hzyazilimci.security.user.EnmRole;
import com.hzyazilimci.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author hzyazilimci
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager jwtManager;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterDto registerDto) {

        User user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .userName(registerDto.getUserName())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(EnmRole.USER)
                .build();

        this.userRepository.save(user);

        String token = this.jwtManager.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationDto authenticationDto) {

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getUserName(),
                        authenticationDto.getPassword()
                )
        );

        User user = this.userRepository.findByUserName(authenticationDto.getUserName()).orElseThrow();

        return AuthenticationResponse
                .builder()
                .token(jwtManager
                        .generateToken(user))
                .build();
    }
}
