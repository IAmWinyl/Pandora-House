package com.serraabak.pandorahouse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greeting from Pandora House!";
    }
    @GetMapping("/about")
    public String about() {
        return "Made by Serra Abak.";
    }
    
}
