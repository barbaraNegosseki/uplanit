package br.gov.sp.fatec.uplanitspringbootapp.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

   //public List<Tasks> findById(Long taskId);
    
}
