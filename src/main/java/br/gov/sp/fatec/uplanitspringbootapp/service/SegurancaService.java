package br.gov.sp.fatec.uplanitspringbootapp.service;

import java.util.List;

import br.gov.sp.fatec.uplanitspringbootapp.entity.User;

public interface SegurancaService {

    public User createUser(String name, String surname, String email, String birthday, String password, String ocupation, String username, String subscription);
    
    public List<User> getAllUsers();

}
