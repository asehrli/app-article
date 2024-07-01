package com.company.apparticle.service;

import com.company.apparticle.payload.*;

public interface UserService {

    ApiResponse<UserDto> register(RegisterDto registerDto);

    ApiResponse<TokenDto> confirmAccount(String email, String code);

    ApiResponse<UserDto> resendCode(String email);

    ApiResponse<TokenDto> login(LoginDto loginDto);

    ApiResponse<UserDto> forgetPassword(ForgetPasswordDto forgetPasswordDto);

}
