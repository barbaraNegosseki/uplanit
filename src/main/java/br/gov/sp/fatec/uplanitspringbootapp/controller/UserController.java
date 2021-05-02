package br.gov.sp.fatec.uplanitspringbootapp.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Subscription;
import br.gov.sp.fatec.uplanitspringbootapp.entity.User;
import br.gov.sp.fatec.uplanitspringbootapp.service.SegurancaService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private SegurancaService segurancaService;

    public List<User> getAllUsers(){
        return segurancaService.getAllUsers();
    }   

    @JsonView(View.UserComplete.class)
    @GetMapping(value = "/{username}")
    public User getUsername(@PathVariable("username") String username){
        return segurancaService.getUsername(username);
    } 
    
    @JsonView(View.User.class)
    @GetMapping(value = "/name")
    public User getUserName(@RequestParam(value="name") String name){
        return segurancaService.getUserName(name);
    }

    @JsonView(View.User.class)
    @PostMapping
    public User signUpNewUser(@RequestBody User user){
        return segurancaService.createUser(user.getName(), user.getSurname(), user.getEmail(), user.getBirthday(), user.getPassword(), user.getOcupation(), user.getUsername(), "TRIAL");
    }

    @JsonView(View.Subscription.class)
    @GetMapping(value = "/subscription/{subscription}")
    public Subscription getBySubNames(@PathVariable("subscription") String name){
        return segurancaService.getBySubName(name);
    }
}
