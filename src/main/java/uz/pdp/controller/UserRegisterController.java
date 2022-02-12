package uz.pdp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.entity.UserRegister;
import uz.pdp.payload.Response;
import uz.pdp.service.UserRegisterService;

@RestController
@RequestMapping(value = "/register")
public class UserRegisterController {

	@Autowired
	UserRegisterService uService;
	
	@PostMapping
	public HttpEntity<?> addUserRe(@Valid @RequestBody UserRegister userRegister){
		Response response = uService.addUserRegister(userRegister);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
	}
	@GetMapping
	public HttpEntity<?> getUser(@Valid @RequestBody UserRegister userRegister){
		Response response = uService.getUser(userRegister);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
	}
	@PutMapping(value = "/{id}")
	public HttpEntity<?> editUser(@PathVariable Integer id, @RequestBody UserRegister userRegister){
		Response response = uService.editUser(id, userRegister);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
	}
	@DeleteMapping(value = "/{id}")
	public HttpEntity<?> deleteUser(@PathVariable Integer id){
		Response response = uService.deleteUser(id);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
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
