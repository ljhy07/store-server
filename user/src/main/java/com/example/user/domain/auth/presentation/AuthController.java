package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.LoginInput;
import com.example.user.domain.auth.presentation.dto.req.ReissueTokenInput;
import com.example.user.domain.auth.presentation.dto.req.SignUpInput;
import com.example.user.domain.auth.presentation.dto.res.SignUpResponse;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.CommandAuthService;
import com.example.user.domain.user.service.CommandUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final CommandAuthService commandAuthService;
    private final CommandUserService commandUserService;

    @MutationMapping
    public SignUpResponse signup(
            @Argument SignUpInput signUpInput
    ){
        return commandUserService.save(signUpInput);
    }

    @MutationMapping
    public Token login(
            @Argument LoginInput loginInput
    ){
        return commandAuthService.login(loginInput);
    }

    @MutationMapping
    public Token reissueToken(
            @Argument ReissueTokenInput reissueTokenInput
    ){
        return commandAuthService.reissue(reissueTokenInput);
    }

//    // 인증 인가용 method
//    @QueryMapping
//    @ResponseStatus(HttpStatus.OK)
//    public boolean checkAuthStatus(){
//        return
//    }
}
