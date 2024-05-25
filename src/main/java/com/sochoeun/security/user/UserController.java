package com.sochoeun.security.user;

import com.sochoeun.exception.response.BaseResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private BaseResponse baseResponse;
    private UserResponse response;
    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId){
        User user = service.getUser(userId);

        response = new UserResponse();
        response.setFirstname(user.getFirstname());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setStatus(user.getStatus());
        response.setId(user.getId());
        response.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        baseResponse = new BaseResponse();
        baseResponse.getSuccess(response);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getAllUser(){
        List<User> allUser = service.getAllUser();

        List<UserResponse> collect = allUser.stream()
                .map(user -> {
                    response = new UserResponse();
                    response.setFirstname(user.getFirstname());
                    response.setLastname(user.getLastname());
                    response.setEmail(user.getEmail());
                    response.setStatus(user.getStatus());
                    response.setId(user.getId());
                    response.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
                    return response;
                })
                .toList();

        baseResponse = new BaseResponse();
        baseResponse.getSuccess(collect);
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> disableUser(@PathVariable Integer userId){
        String disable = "DISABLE";
        User user = service.disableUser(userId, disable);

        response = new UserResponse();
        response.setFirstname(user.getFirstname());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setStatus(user.getStatus());
        response.setId(user.getId());
        response.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        baseResponse = new BaseResponse();
        baseResponse.disableSuccess(response);
        return ResponseEntity.ok(baseResponse);
    }
}
