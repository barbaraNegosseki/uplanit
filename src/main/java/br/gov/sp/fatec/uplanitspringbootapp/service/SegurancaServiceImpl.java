package br.gov.sp.fatec.uplanitspringbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Subscription;
import br.gov.sp.fatec.uplanitspringbootapp.entity.User;
import br.gov.sp.fatec.uplanitspringbootapp.repository.SubscriptionRepository;
import br.gov.sp.fatec.uplanitspringbootapp.repository.UserRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private SubscriptionRepository subRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public User createUser(String name, String surname, String email, String birthday, String password, String ocupation, String username, String subscription) {
        Subscription sub = subRepo.findBySubscription(subscription);

        if(sub == null){
            sub = new Subscription();
            sub.setSubscriptions(subscription);
            subRepo.save(sub);
        }

        User user = new User();
        user.setName("Mariana");
        user.setSurname("Santos");
        user.setEmail("msantos@gmail.com");
        user.setBirthday("21-06-1998");
        user.setPassword("pass123");
        user.setOcupation("Bibliotecária");
        user.setUsername("msantos");
        user.setSubscriptions(new HashSet<Subscription>());
        user.getSubscriptions().add(sub);
        userRepo.save(user);

        return user;
    }           

    @Override
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public User getUsername(String username){
        Optional<User> userOp = userRepo.findById(username);
        if(userOp.isPresent()){
            return userOp.get();
        }
        throw new RuntimeException("Usuário com esse username não encontrado!");
    }

    @Override
    public User getUserName(String name){
         User user = userRepo.findByName(name);
        if(user != null){
            return user;
        }
        throw new RuntimeException("Usuário não existe!");
    }    

    @Override
    public Subscription getBySubName(String name){
        Subscription subscription = subRepo.findBySubscription(name);
        
        if(subscription != null){
            return subscription;
        }
        throw new RuntimeException("Inscrição não existe!");
    }
}
