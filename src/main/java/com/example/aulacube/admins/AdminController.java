package com.example.aulacube.admins;

import com.example.aulacube.admins.dtos.AdminResponse;
import com.example.aulacube.admins.dtos.CreateAdminRequest;
import com.example.aulacube.admins.dtos.LoginAdminRequest;
import com.example.aulacube.commons.dtos.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private  final AdminService adminService;
    private final ModelMapper modelMapper;

    public AdminController(AdminService adminService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("")
    ResponseEntity<AdminResponse> signupAdmin(@RequestBody CreateAdminRequest request) {
        AdminEntity savedAdmin = adminService.createAdmin(request);
        URI savedAdminUri = URI.create("/admins/" + savedAdmin.getId());

        return ResponseEntity.created(savedAdminUri)
                .body(modelMapper.map(savedAdmin, AdminResponse.class));
    }
    @PostMapping("/login")
    ResponseEntity<AdminResponse> loginAdmin(@RequestBody LoginAdminRequest request) {
        AdminEntity savedAdmin = adminService.loginAdmin(request.getEmail(), request.getPassword()) ;

        return ResponseEntity.ok()
                .body(modelMapper.map(savedAdmin, AdminResponse.class));
    }

    @ExceptionHandler({
            AdminService.AdminNotFoundException.class
    })
    ResponseEntity<ErrorResponse> handleAdminExceptions(Exception ex) {
        String message = null;
        HttpStatus status = null;

        if (ex instanceof AdminService.AdminNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }

        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status)
                .body(response);
    }

}
