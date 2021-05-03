package br.gov.sp.fatec.uplanitspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, String> {
    
}
