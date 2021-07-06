package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {
    @GetMapping("/sign_in")
    public String loginPage(){
        return "signIn";
    }

    @GetMapping("/secure/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
