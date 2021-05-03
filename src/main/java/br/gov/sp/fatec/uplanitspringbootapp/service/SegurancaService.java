package br.gov.sp.fatec.uplanitspringbootapp.service;

import java.util.List;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Subscription;
import br.gov.sp.fatec.uplanitspringbootapp.entity.Tasks;
import br.gov.sp.fatec.uplanitspringbootapp.entity.User;


public interface SegurancaService {

    public User createUser(String name, String surname, String email, String birthday, String password, String ocupation, String username, String subscription);
    
    public User updateUserInfo(String name, String surname, String email, String birthday, String password, String username, String ocupation);
    
    public List<User> getAllUsers();

    public User getUsername(String username);

    public User getUserName(String name);

    public Subscription getBySubName(String name);

    public User deleteUser(String username);

    //

    public List<Tasks> getAllTasks();

    public Tasks createTask(String taskId, String taskName, String taskCheck, String taskDateCreated, String taskDateDue);

}
