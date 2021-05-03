package br.gov.sp.fatec.uplanitspringbootapp.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Tasks;

import br.gov.sp.fatec.uplanitspringbootapp.service.SegurancaService;

@RestController
@RequestMapping(value = "/task")
@CrossOrigin
public class TasksController {
    
    @Autowired
    private SegurancaService segurancaService;

    //get
    public List<Tasks> getAllTasks(){
        return segurancaService.getAllTasks();
    }

    //post
    @PostMapping
    public ResponseEntity<Tasks> signUpNewUser(@RequestBody Tasks tasks,
            UriComponentsBuilder uriComponentsBuilder){   
        tasks = segurancaService.createTask(tasks.getTaskId(), tasks.getTaskName(), tasks.getTaskCheck(), tasks.getTaskDateCreated(), tasks.getTaskDateDue());
        HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(
                uriComponentsBuilder.path(
                    "/tasks/" + tasks.getTaskId()).build().toUri());

        return new ResponseEntity<Tasks>(tasks, responseHeaders, HttpStatus.CREATED);
    }
}
