package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AppProperties appProperties;
    private final String userNotFoundEncodedPassword;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthSessionRepository authSessionRepository;

    public AuthController(PasswordEncoder passwordEncoder, TokenService tokenService, AppProperties appProperties, AuthSessionRepository authSessionRepository) {
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.appProperties = appProperties;
        this.userNotFoundEncodedPassword = this.passwordEncoder
                .encode("userNotFoundPassword");
        this.authSessionRepository = authSessionRepository;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse register(@RequestBody UserEntity newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);
        BaseResponse response = new BaseResponse();
        response.setData(newUser);
        return response;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity login(@RequestBody SignInEntity signInRequest){
        UserEntity user = userRepository.getByEmail(signInRequest.getEmail());
        long test = authSessionRepository.deleteByEmail(signInRequest.getEmail());
        if(user != null){
            boolean pwMatched = passwordEncoder.matches(signInRequest.getPassword(),user.getPassword());
            if(pwMatched){
                String sessionId = tokenService.createToken();
                Timestamp exp = Timestamp.valueOf(LocalDateTime.now().plus(this.appProperties.getCookieMaxAge()));
                AuthSessionEntity sessionEntity = new AuthSessionEntity(sessionId, exp, user.getEmail());

                authSessionRepository.save(sessionEntity);

                ResponseCookie cookie = ResponseCookie
//                        .from(AuthCookieFilter.COOKIE_NAME, sessionId)
                        .from("authenticate", sessionId)
                        .maxAge(this.appProperties.getCookieMaxAge()).sameSite("Strict")
                        .path("/").httpOnly(true).secure(this.appProperties.isSecureCookie()).build();
                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("{\"success\":\"true\"}");
            }
        }else {
            this.passwordEncoder.matches(signInRequest.getPassword(), this.userNotFoundEncodedPassword);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/deleteCookie")
    public ResponseEntity deleteCookie(){
        ResponseCookie cookie = ResponseCookie
//                        .from(AuthCookieFilter.COOKIE_NAME, sessionId)
                .from("authenticate", null)
                .maxAge(0).sameSite("Strict")
                .path("/").httpOnly(true).secure(this.appProperties.isSecureCookie()).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("{\"success\":\"true\"}");
    }
}
