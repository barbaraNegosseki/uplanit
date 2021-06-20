package br.gov.sp.fatec.uplanitspringbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passEncoder;

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
        user.setPassword(passEncoder.encode(password));
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
    @PreAuthorize("isAuthenticated()")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    //pesquisando pelo username
    @Override
    //@PreAuthorize("isAuthenticated()")
    public User getUsername(String username){
        Optional<User> userOp = userRepo.findById(username);
        if(userOp.isPresent()){
            return userOp.get();
        }
        throw new RegistroNaoEncontradoException("Usuário não existe!");
    }

       //pesquisando usuário COMPLETO pelo username
       @Override
       //@PreAuthorize("isAuthenticated()")
       public User getUserByUsername(String username){
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
    @PreAuthorize("isAuthenticated()")
    public User getUserName(String name){
         User user = userRepo.findByName(name);
        if(user != null){
            return user;
        }
        throw new RegistroNaoEncontradoException("Usuário não existe!");
    }    

    //pesquisando pelo nome da inscrição
    @Override
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("isAuthenticated()")
    public Tasks createTask(String taskUserId, String taskName, String taskCheck, String taskDateCreated, String taskDateDue) {
       
        Tasks tasks = new Tasks();
        tasks.setTaskUserId(taskUserId);
        tasks.setTaskName(taskName);
        tasks.setTaskCheck(taskCheck);
        tasks.setTaskDateCreated(taskDateCreated);
        tasks.setTaskDateDue(taskDateDue);
        taskRepo.save(tasks);

        return tasks;
    } 

    //pesquisando todas as tasks
    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Tasks> getAllTasks(){
        return taskRepo.findAll();
    }

    //pesquisando todas as tasks do usuário pelo username
    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Tasks> getTasksByUserId(String taskName){
        return taskRepo.findAll();
    }

    //pesquisando pelo id da task
    @Override
    @PreAuthorize("isAuthenticated()")
    public Tasks getTaskById(Long taskId){
        Optional<Tasks> taskOp = taskRepo.findById(taskId);
        if(taskOp.isPresent()){
            return taskOp.get();
        }
        throw new RegistroNaoEncontradoException("Tarefa não existe!");
    }

    //atualizando as informações das tasks
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public Tasks updateTaskInfo(Long taskId, String taskName, String taskCheck, String taskDateCreated, String taskDateDue) {

        Tasks tasks = taskRepo.getTaskById(taskId);
        if (tasks != null){
            tasks.setTaskName(taskName);
            tasks.setTaskCheck(taskCheck);
            tasks.setTaskDateCreated(taskDateCreated);
            tasks.setTaskDateDue(taskDateDue);
            taskRepo.save(tasks);
    
            return tasks;
        }

        throw new RegistroNaoEncontradoException("Tarefa não encontrada!");        
    }  

    // @PreAuthorize("isAuthenticated()")
    // public Pedido atualizarValorPedido(double price, Long id){
    //     Pedido pedido = pedidoRepo.buscarPedidoPorId(id);
    //     if (pedido != null) {
    //         pedido.setPrice(price);
    //         pedidoRepo.save(pedido);
    //         return pedido;            
    //     }
    //     throw new RegistroNaoEncontradoException("Pedido não encontrado!");
    // }

    //deletando pelo id da Task
    @Override
    @PreAuthorize("isAuthenticated()")
    public Tasks deleteTask(Long taskId){
        Optional<Tasks> taskOp = taskRepo.findById(taskId);
        if(taskOp.isPresent()){
            taskRepo.deleteById(taskId);
            return taskOp.get();
        }
        throw new RegistroNaoEncontradoException("Tarefa não existe!");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);

        if(user == null){
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }

        return org.springframework.security.core.userdetails.User.builder().username(username)
                    .password(user.getPassword()).authorities(user.getSubscriptions().stream()
                    .map(Subscription::getSubscriptions).collect(Collectors.toList()).toArray(new String[user
                    .getSubscriptions().size()])).build();
    }
}
