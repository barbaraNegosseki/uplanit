package br.gov.sp.fatec.uplanitspringbootapp.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.uplanitspringbootapp.entity.Tasks;

import br.gov.sp.fatec.uplanitspringbootapp.service.SegurancaService;

@CrossOrigin
@RestController
@RequestMapping(value = "/task")
public class TasksController {
    
    @Autowired
    private SegurancaService segurancaService;

    //get
    @JsonView(View.TasksComplete.class)
    @GetMapping()
    public List<Tasks> getAllTasks(){
        return segurancaService.getAllTasks();
    }

    //pegando as tarefa de cada usuário pelo username
    @JsonView(View.TasksComplete.class)
    @GetMapping(value = "/userTasks/{taskUserId}")
    public Tasks getTasksByUserId(@PathVariable("taskUserId") String taskUserId){
        return segurancaService.getTasksByUserId(taskUserId);
    }

    //pesquisando tarefa por ID
    @CrossOrigin
    @GetMapping(value = "/{taskId}")
    public Tasks getTaskById(@PathVariable("taskId") Long taskId){
        return segurancaService.getTaskById(taskId);
    } 

    //post
    @PostMapping
    public ResponseEntity<Tasks> signUpNewUser(@RequestBody Tasks tasks,
            UriComponentsBuilder uriComponentsBuilder){   
        tasks = segurancaService.createTask(tasks.getTaskUserId(), tasks.getTaskName(), tasks.getTaskCheck(), tasks.getTaskDateCreated(), tasks.getTaskDateDue());
        HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(
                uriComponentsBuilder.path(
                    "/task/" + tasks.getTaskId()).build().toUri());

        return new ResponseEntity<Tasks>(tasks, responseHeaders, HttpStatus.CREATED);
    }

    //atualizando uma task
    @JsonView(View.TasksComplete.class)
    @PutMapping(path = "/{taskId}")
    public ResponseEntity<Tasks> updateTask(@PathVariable Long taskId, @RequestBody Tasks tasks, UriComponentsBuilder uriComponentsBuilder) throws Exception{
        tasks = segurancaService.updateTaskInfo(tasks.getTaskId(), tasks.getTaskName(), tasks.getTaskCheck(), tasks.getTaskDateCreated(), tasks.getTaskDateDue());
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/task/" + tasks.getTaskId()).build().toUri());
        return new ResponseEntity<Tasks>(tasks, responHeaders, HttpStatus.CREATED);
    }

    // @JsonView(View.PedidoLista.class)
    // @PutMapping("/{id}")
    // public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido, UriComponentsBuilder uriComponentsBuilder) throws Exception {
    //     pedido = segurancaService.atualizarValorPedido(pedido.getPrice(), id);
    //     HttpHeaders responseHeaders = new HttpHeaders();
    //     responseHeaders.setLocation(uriComponentsBuilder.path("/pedido/" + pedido.getId()).build().toUri());
    //     return new ResponseEntity<Pedido>(pedido, responseHeaders, HttpStatus.CREATED);
    // }

    //deletando uma task
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Tasks> deleteTask(@PathVariable Long taskId){
        Tasks deleteTask = segurancaService.deleteTask(taskId);
        return new ResponseEntity<Tasks>(deleteTask, HttpStatus.OK);
    }
}
