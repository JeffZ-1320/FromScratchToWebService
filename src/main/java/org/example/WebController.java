package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {
    @GetMapping("/test")
//
    public String testing(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        return"test";
    }


}
