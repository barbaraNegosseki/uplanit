package br.gov.sp.fatec.uplanitspringbootapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.uplanitspringbootapp.entity.User;
import br.gov.sp.fatec.uplanitspringbootapp.repository.UserRepository;

@SpringBootTest
@Transactional
@Rollback
class UplanitSpringBootAppApplicationTests {

    @Autowired
    private UserRepository userRepo;

	@Test
	void contextLoads() {
    }
    
    @Test
    void insertTest() {
        User user = new User();        
        user.setName("Felipe");
        user.setSurname("Biscaro");
        user.setEmail("fsbiscaro@gmail.com");
        user.setBirthday("23-03-1999");
        user.setPassword("pass123");
        user.setOcupation("Engenheiro");
        user.setUsername("usernametest");
        userRepo.save(user);
        assertNotNull(user.getUsername());
    }
}
