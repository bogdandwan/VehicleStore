package com.example.VehicleStore.security.auth;


import com.example.VehicleStore.entity.user.Role;
import com.example.VehicleStore.entity.user.User;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.repository.user.RoleRepository;
import com.example.VehicleStore.repository.user.UserRepository;
import com.example.VehicleStore.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;



    public AuthenticationResponse register(RegisterRequest request) {


        String roleName = request.getRole() != null ? request.getRole() : "ROLE_USER";
        Role userRole = roleRepository.findByName(roleName)
                .orElseThrow(() -> new NotFoundException("Role not found"));

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .build();
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
