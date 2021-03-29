package br.gov.sp.fatec.uplanitspringbootapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.uplanitspringbootapp.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

    public List<User> findByNameContainsIgnoreCase(String name);

    public User findByName(String name);

    public User findByNameAndPassword(String name, String password);

    public List<User> findBySubName(String subscription);

    /*

    @Query("select u from User u where u.name = ?1 and u.password = ?2")
    public User buscaUsuarioPorNamePassword(String name, String password);

    @Query("select u from User u inner join u.subscriptions a where a.name = ?1")
    public List<User> buscaPorNameSubscriptions(String subscriptions);

    @Query("select u from User u where u.name = ?1 ")
    public User buscaUsuarioPorName(String name);*/
    
}
