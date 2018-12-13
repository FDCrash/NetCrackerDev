package com.netcracker.denisik.controllers;

import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "Gets all users", nickname = "UserController.getAllUsers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}


