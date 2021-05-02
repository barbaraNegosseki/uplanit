package br.gov.sp.fatec.uplanitspringbootapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
    
    @GetMapping
    public String welcome(){
        return "Welcome Back!";
    }

}
