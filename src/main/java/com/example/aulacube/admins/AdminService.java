package com.example.aulacube.admins;
import com.example.aulacube.admins.dtos.CreateAdminRequest;
import com.example.aulacube.users.UserService;
import com.example.aulacube.users.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    public AdminService(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    public AdminEntity createAdmin(CreateAdminRequest request) {
         AdminEntity newAdmin = modelMapper.map(request, AdminEntity.class);
         //TODO: Set the password here
        return adminRepository.save(newAdmin);
    }
    public AdminEntity getAdmin(String email) {

        return adminRepository.findByEmail(email).orElseThrow(() -> new AdminNotFoundException(email));
    }
    public AdminEntity getAdmin(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException(adminId));
    }

public AdminEntity updateAdmin(String email, String password) {
    var admin = adminRepository.findByEmail(email).orElseThrow(()-> new AdminNotFoundException(email));
    //Save the admin after updating the password
    return adminRepository.save(admin);
}
      public AdminEntity loginAdmin(String email, String password) {
        var admin = adminRepository.findByEmail(email).orElseThrow(() -> new AdminNotFoundException(email));
        return admin;
      }

        static class AdminNotFoundException extends RuntimeException {
            public AdminNotFoundException(String email) {
                super("Admin not found with email: " + email);
            }
            public AdminNotFoundException(Long adminId) {
                super("Admin not found with id: " + adminId);
            }
        }



}
