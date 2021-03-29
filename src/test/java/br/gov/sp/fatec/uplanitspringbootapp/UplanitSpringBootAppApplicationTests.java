package br.gov.sp.fatec.uplanitspringbootapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;
import br.gov.sp.fatec.uplanitspringbootapp.entity.Subscription;
import br.gov.sp.fatec.uplanitspringbootapp.entity.User;
import br.gov.sp.fatec.uplanitspringbootapp.repository.SubscriptionRepository;
import br.gov.sp.fatec.uplanitspringbootapp.repository.UserRepository;

@SpringBootTest
@Transactional
@Rollback
class UplanitSpringBootAppApplicationTests {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SubscriptionRepository subRepo;

	@Test
	void contextLoads() {
    }
    
    @Test
    void userInsertTest() {
        User user = new User();   

        user.setName("Felipe");
        user.setSurname("Biscaro");
        user.setEmail("fsbiscaro@gmail.com");
        user.setBirthday("23-03-1999");
        user.setPassword("pass123");
        user.setOcupation("Engenheiro");
        user.setUsername("fbiscaro");

        user.setSubscriptions(new HashSet<Subscription>());
        Subscription sub = new Subscription();
        sub.setSubscriptions("TRIAL");
        user.getSubscriptions().add(sub);

        userRepo.save(user);
        assertNotNull(user.getUsername());
    }

    @Test
    void userSubscriptionTest() {
        User user = new User();
        user.setName("Mariana");
        user.setSurname("Santos");
        user.setEmail("msantos@gmail.com");
        user.setBirthday("21-06-1998");
        user.setPassword("pass123");
        user.setOcupation("Bibliotecária");
        user.setUsername("msantos");
        userRepo.save(user);

        Subscription sub = new Subscription();
        sub.setSubscriptions("TRIAL");
        sub.setUser(new HashSet<User>());
        sub.getUser().add(user);

        subRepo.save(sub);
        assertNotNull(sub.getUser().iterator().next().getUsername());
    }

    //

    @Test
    void testaBuscaUsuarioNomeContains(){
        List<User> users = userRepo.findByNameContainsIgnoreCase("B");
        assertFalse(users.isEmpty());
    }

    @Test
    void testaBuscaUsuarioNome(){
        User user = userRepo.findByName("Barbara");
        assertNotNull(user);
    }

    @Test
    void testaBuscaUsuarioNomeSenha(){
        User user = userRepo.findByNameAndPassword("Barbara", "pass123");
        assertNotNull(user);
    }

    @Test
    void testaBuscaUsuarioNomeAutorizacao(){
        List<User> user = userRepo.findBySubName("SUBSCRIBED");
        assertFalse(user.isEmpty());
    }
}
