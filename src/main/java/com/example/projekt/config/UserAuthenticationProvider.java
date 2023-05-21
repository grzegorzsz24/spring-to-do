//package com.example.projekt.config;
//
//import com.example.projekt.model.User;
//import com.example.projekt.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class UserAuthenticationProvider implements AuthenticationProvider{
//    Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//
//    public UserAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        Optional<User> user = userRepository.findUserByEmail(email);
//        if (user == null) {
//            throw new BadCredentialsException("User not found");
//        }
//        if (passwordEncoder.matches(password, user.get().getPassword())) {
//            logger.info("Successfully Authenticated the user");
//            return new UsernamePasswordAuthenticationToken(email, password, getUserRoles(user.get().getRoles().toString()));
//        } else {
//            throw new BadCredentialsException("Wrong password!");
//        }
//    }
//
//    private List<GrantedAuthority> getUserRoles(String userRoles) {
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//        String[] roles = userRoles.split(",");
//        for (String role : roles) {
//            logger.info("Role: " + role);
//            grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
//        }
//        return grantedAuthorityList;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
