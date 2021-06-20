package br.gov.sp.fatec.uplanitspringbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

   //public List<Tasks> findById(Long taskId);

   @Query("select t from Tasks t where t.taskId = ?1")
   public Tasks getTaskById (Long taskId);

   @Query("select t from Tasks t where t.taskUserId = ?1")
   public List<Tasks> getTasksByUserId (String taskUserId);
    
}
