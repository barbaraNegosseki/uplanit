package br.gov.sp.fatec.uplanitspringbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Subscription;
import br.gov.sp.fatec.uplanitspringbootapp.entity.Tasks;
import br.gov.sp.fatec.uplanitspringbootapp.entity.User;
import br.gov.sp.fatec.uplanitspringbootapp.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.uplanitspringbootapp.repository.SubscriptionRepository;
import br.gov.sp.fatec.uplanitspringbootapp.repository.TaskRepository;
import br.gov.sp.fatec.uplanitspringbootapp.repository.UserRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private SubscriptionRepository subRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TaskRepository taskRepo;

    @Transactional
    public User createUser(String name, String surname, String email, String birthday, String password, String ocupation, String username, String subscription) {
        Subscription sub = subRepo.findBySubscription(subscription);

        if(sub == null){
            sub = new Subscription();
            sub.setSubscriptions(subscription);
            subRepo.save(sub);
        }

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setPassword(password);
        user.setOcupation(ocupation);
        user.setUsername(username);
        user.setSubscriptions(new HashSet<Subscription>());
        user.getSubscriptions().add(sub);
        userRepo.save(user);

        return user;
    }           

    @Transactional
    public User updateUserInfo(String name, String surname, String email, String birthday, String password, String username, String ocupation) {

        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setPassword(password);
        user.setOcupation(ocupation);
        userRepo.save(user);

        return user;
    }  

    //pesquisando todos os usuários
    @Override
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    //pesquisando pelo username
    @Override
    public User getUsername(String username){
        Optional<User> userOp = userRepo.findById(username);
        if(userOp.isPresent()){
            return userOp.get();
        }
        throw new RegistroNaoEncontradoException("Usuário não existe!");
    }

    //deletando pelo username
    @Override
    public User deleteUser(String username){
        Optional<User> userOp = userRepo.findById(username);
        if(userOp.isPresent()){
            userRepo.deleteById(username);
            return userOp.get();
        }
        throw new RegistroNaoEncontradoException("Usuário não existe!");
    }

    //pesquisando pelo nome
    @Override
    public User getUserName(String name){
         User user = userRepo.findByName(name);
        if(user != null){
            return user;
        }
        throw new RegistroNaoEncontradoException("Usuário não existe!");
    }    

    //pesquisando pelo nome da inscrição
    @Override
    public Subscription getBySubName(String name){
        Subscription subscription = subRepo.findBySubscription(name);
        
        if(subscription != null){
            return subscription;
        }
        throw new RegistroNaoEncontradoException("Inscrição não existe!");
    }

    //

    //inserindo task
    @Transactional
    public Tasks createTask(String taskId, String taskName, String taskCheck, String taskDateCreated, String taskDateDue) {
       
        Tasks tasks = new Tasks();
        tasks.setTaskId(taskId);
        tasks.setTaskName(taskName);
        tasks.setTaskCheck(taskCheck);
        tasks.setTaskDateCreated(taskDateCreated);
        tasks.setTaskDateDue(taskDateDue);
        taskRepo.save(tasks);

        return tasks;
    } 

    //pesquisando todas as tasks
    @Override
    public List<Tasks> getAllTasks(){
        return taskRepo.findAll();
    }

    //pesquisando pelo id da task
    @Override
    public Tasks getTaskById(String taskId){
        Optional<Tasks> taskOp = taskRepo.findById(taskId);
        if(taskOp.isPresent()){
            return taskOp.get();
        }
        throw new RegistroNaoEncontradoException("Tarefa não existe!");
    }

    //atualizando as informações das tasks
    @Transactional
    public Tasks updateTaskInfo(String taskId, String taskName, String taskCheck, String taskDateCreated, String taskDateDue) {

        Tasks tasks = new Tasks();
        tasks.setTaskId(taskId);
        tasks.setTaskName(taskName);
        tasks.setTaskCheck(taskCheck);
        tasks.setTaskDateCreated(taskDateCreated);
        tasks.setTaskDateDue(taskDateDue);
        taskRepo.save(tasks);

        return tasks;
    }  
}
