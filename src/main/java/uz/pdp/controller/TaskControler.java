package uz.pdp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.entity.Task;
import uz.pdp.payload.Response;
import uz.pdp.payload.TaskDto;
import uz.pdp.service.TaskService;

@RestController
@RequestMapping(value = "/task")
public class TaskControler {

	@Autowired
	TaskService tService;
	
	@PostMapping
	public HttpEntity<?> addLagu(@RequestBody TaskDto taskDto){
		Response response = tService.addTask(taskDto);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
	}
	@GetMapping(value = "/{id}")
	public HttpEntity<?> getTask(@PathVariable Integer id){
		Response response = tService.getTask(id);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
	}
	@GetMapping(value = "/language/{id}")
	public HttpEntity<?> getAllTaskByLauageId(@PathVariable Integer id){
		List<Task> response = tService.getAllTaskByLaguageId(id);
		return ResponseEntity.status(response.isEmpty()?HttpStatus.NOT_FOUND:HttpStatus.OK).body(response);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
