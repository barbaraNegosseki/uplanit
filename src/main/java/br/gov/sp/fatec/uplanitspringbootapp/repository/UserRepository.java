package br.gov.sp.fatec.uplanitspringbootapp.repository;

//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.uplanitspringbootapp.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
