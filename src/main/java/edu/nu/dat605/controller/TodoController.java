package edu.nu.dat605.controller;

import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import edu.nu.dat605.entity.Todo;
import edu.nu.dat605.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api")
public class TodoController {

    @Autowired
    TodoRepo todoRepo;


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Todo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(path = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> getTodos(){

        List<Todo> todos = todoRepo.findAll();

        return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
    }

    @RequestMapping(path = "/todos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> getTodo(@PathVariable(name = "id", required = true) Integer id){

        Todo todo = todoRepo.findOne(id);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }


    @RequestMapping(path = "/todos", method = RequestMethod.POST)
    public ResponseEntity<?> createTodo(@RequestBody Todo _todo) {

        if(_todo.getId() != null && todoRepo.exists(_todo.getId())) {
            return new ResponseEntity<String>("Todo with Id "+ _todo.getId() +" already exists", HttpStatus.CONFLICT);
        }
        Todo todo = todoRepo.save(_todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/todos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTodo(@RequestBody Todo _todo) {

        if(_todo.getId() == null || !todoRepo.exists(_todo.getId())) {
            return new ResponseEntity<String>("Todo with Id " + _todo.getId() + " does not exist", HttpStatus.CONFLICT);
        }

        Todo todo = todoRepo.save(_todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @RequestMapping(path = "/todos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTodo(@PathVariable(name = "id") Integer id) {
        if(id == null || !todoRepo.exists(id)) {
            return new ResponseEntity<String>("Todo with Id " + id + " does not exist", HttpStatus.CONFLICT);
        }
        todoRepo.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
