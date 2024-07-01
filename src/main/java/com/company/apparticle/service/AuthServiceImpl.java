package com.company.apparticle.service;

import com.company.apparticle.entity.User;
import com.company.apparticle.exception.MyBadRequestException;
import com.company.apparticle.exception.MyConflictException;
import com.company.apparticle.exception.MyNotFoundException;
import com.company.apparticle.mapper.UserMapper;
import com.company.apparticle.payload.*;
import com.company.apparticle.repository.RoleRepository;
import com.company.apparticle.repository.UserRepository;
import com.company.apparticle.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final SenderService senderService;
    private final CodeService codeService;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;


    @Override
    public ApiResponse<UserDto> register(RegisterDto registerDto) {
        if (!registerDto.password().equals(registerDto.prePassword()))
            throw new MyBadRequestException("Passwords are not equals");

        User user;
        Optional<User> userOptional = userRepository.findByEmail(registerDto.email());
        if (userOptional.isPresent()) {
            user = userOptional.get();

            if (user.isEnabled())
                throw new MyConflictException("Email already registered!");

            user.setName(registerDto.name());
        } else {
            user = userMapper.toEntity(registerDto);
        }

        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setRoles(new ArrayList<>(List.of(roleRepository.findByName("USER").orElseThrow())));
        userRepository.save(user);
        UserDto userDto = userMapper.toDto(user);

        String code = codeService.createCode(userDto.getEmail());
        senderService.send(userDto.getEmail(), "Your code", code);

        return ApiResponse.success(userDto, "User saved successfully");
    }

    @Override
    public ApiResponse<TokenDto> confirmAccount(String email, String code) {
        CodeDto codeDto = codeService.getCode(email);
        if (codeDto == null)
            throw new MyBadRequestException("Your code expired");

        if (!codeDto.getCode().equals(code))
            throw new MyBadRequestException("Your code does not match");

        User user = userRepository.findByEmail(email).orElseThrow(() -> new MyNotFoundException("User not found. Register first"));
        user.setEnabled(true);
        userRepository.save(user);
        codeService.remove(email);

        String at = jwtProvider.generateAccessToken(user.getId());
        String rt = jwtProvider.generateRefreshToken(user.getId());

        return ApiResponse.success(TokenDto.builder().accessToken(at).refreshToken(rt).build());
    }

    @Override
    public ApiResponse<UserDto> resendCode(String email) {
        CodeDto codeDto = codeService.getCode(email);
        if (codeDto != null)
            throw new MyBadRequestException("You cannot get a new code until the code expires");

        User user = userRepository.findByEmail(email).orElseThrow(() -> new MyNotFoundException("User not found!"));
        if (user.isEnabled())
            throw new MyBadRequestException("Code does not exists");

        String code = codeService.createCode(email);
        senderService.send(email, "Your code", code);

        return ApiResponse.success(userMapper.toDto(user), "Code resend successfully");
    }

    @Override
    public ApiResponse<TokenDto> login(LoginDto loginDto) {
        return null;
    }

    @Override
    public ApiResponse<UserDto> forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        return null;
    }
}
