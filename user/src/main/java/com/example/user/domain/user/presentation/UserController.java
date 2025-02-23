package com.example.user.domain.user.presentation;

import com.example.user.domain.user.presentation.dto.req.UserCreateInput;
import com.example.user.domain.user.presentation.dto.req.UserDeleteInput;
import com.example.user.domain.user.presentation.dto.req.UserPasswordUpdateInput;
import com.example.user.domain.user.presentation.dto.req.UserUpdateInput;
import com.example.user.domain.user.presentation.dto.res.UserMutationResponse;
import com.example.user.domain.user.presentation.dto.res.UserQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    @QueryMapping
    public List<UserQueryResponse> getAllUser(){

    }

    @QueryMapping
    public UserQueryResponse getUserById(
            @Argument Long userId
    ){

    }

    @QueryMapping
    public UserQueryResponse getUserByAccessToken(
            @Argument String accessToken
    ){

    }

    @MutationMapping
    public UserMutationResponse createUser(
            @Argument UserCreateInput userCreateInput
    ){

    }

    @MutationMapping
    public UserMutationResponse updateUser(
            @Argument UserUpdateInput userUpdateInput
    ){

    }

    @MutationMapping
    public UserMutationResponse updateUserPassword(
            @Argument UserPasswordUpdateInput userPasswordUpdateInput
    ){

    }

    @MutationMapping
    public UserMutationResponse deleteUser(
            @Argument UserDeleteInput userDeleteInput
    ){

    }


}
