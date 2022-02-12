package uz.pdp.controller;

import java.util.HashMap;
import java.util.List;
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

import uz.pdp.entity.ProgrammLanguage;
import uz.pdp.payload.Response;
import uz.pdp.service.ProgrammLanguageService;

@RestController
@RequestMapping(value = "/language")
public class ProgrammLanguageController {

	@Autowired
	ProgrammLanguageService pService;
	
	@PostMapping
	public HttpEntity<?> addPLan(@Valid @RequestBody ProgrammLanguage programmLanguage){
		Response response = pService.addProgLanguage(programmLanguage);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(response);
	}
	@GetMapping(value = "/{id}")
	public HttpEntity<?> getPLanhg(@PathVariable Integer id){
		List<ProgrammLanguage> pLanguages=pService.getLanguages(id);
		return ResponseEntity.status(pLanguages.isEmpty()?HttpStatus.NOT_FOUND:HttpStatus.OK).body(pLanguages);
	}
	@PutMapping(value = "/{id}")
	public HttpEntity<?> editPlan(@PathVariable Integer id, @Valid @RequestBody ProgrammLanguage programmLanguage){
		Response response = pService.editPLang(id, programmLanguage);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
	}
	@DeleteMapping(value = "/{id}")
	public HttpEntity<?> deletPlan(@PathVariable Integer id){
		Response response = pService.deletePlang(id);
		return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
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
