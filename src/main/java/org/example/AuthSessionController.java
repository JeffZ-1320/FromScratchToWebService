package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/secure/authSession")
public class AuthSessionController {
    @Autowired
    private AuthSessionRepository authSessionRepository;

    @GetMapping("/getAllSessions")
    public List<AuthSessionEntity> getAuthSessionById(){
        return authSessionRepository.findAll();
    }

    @GetMapping("/getSession/{id}")
    public AuthSessionEntity getAuthSessionById(@PathVariable("id") String sessionId){
        return authSessionRepository.findById(sessionId).orElse(null);
    }

    @PostMapping("/newSession")
    public AuthSessionEntity addSession(@Valid @RequestBody AuthSessionEntity authSession){
        return authSessionRepository.save(authSession);
    }

    @PutMapping("/updateSession/{id}")
    public AuthSessionEntity updateSession(@PathVariable("id") String sessionId, @NotNull @Valid @RequestBody AuthSessionEntity newAuthSession){
        AuthSessionEntity authSession = authSessionRepository.getById(sessionId);
        authSession.setValidUntil(newAuthSession.getValidUntil());
        authSession.setEmail(newAuthSession.getEmail());
        return authSessionRepository.save(authSession);
    }

    @DeleteMapping("/deleteSession/{id}")
    public int deleteSession(@PathVariable("id") String sessionId){
        authSessionRepository.deleteById(sessionId);
        return 1;
    }

}
