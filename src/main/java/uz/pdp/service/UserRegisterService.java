package uz.pdp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import uz.pdp.entity.Task;
import uz.pdp.entity.UserRegister;
import uz.pdp.payload.Response;
import uz.pdp.repository.TaskRepository;
import uz.pdp.repository.UserRegisterRepository;

@Service
public class UserRegisterService {

	@Autowired
	UserRegisterRepository uRepository;
	
	@Autowired
	TaskRepository tRepository;
	
	public Response addUserRegister(UserRegister userRegister) {
		if (uRepository.existsByEmail(userRegister.getEmail()))
			return new Response("User exist", false);
		if (userRegister.getTasks() != null) {
			Set<Task> ta = new HashSet<>();
			List<Task> tasks = new ArrayList<>(userRegister.getTasks());
			for (int i = 0; i < tasks.size(); i++) {
				Task task = tasks.get(i);
				Task task2 = tRepository.save(task);
				ta.add(task2);
			}
			UserRegister userRegister2 = new UserRegister(userRegister.getEmail(), userRegister.getPassword(), ta);
			uRepository.save(userRegister2);
			return new Response("User added", true);
		} else {
			UserRegister userRegister2 = new UserRegister(userRegister.getEmail(), userRegister.getPassword());
			uRepository.save(userRegister2);
			return new Response("User added", true);
		}
	}
	public Response getUser(UserRegister userRegister) {
		Optional<UserRegister> uOptional = uRepository.findByEmailAndPassword(userRegister.getEmail(), userRegister.getPassword());
		if(uOptional.isPresent()){
			List<Task> task = tRepository.findAll();
			return new Response("you are successfuly registired",true, task);
		}
		return new Response("Password or Email error or User not found ",false);
	}
	public Response editUser(Integer id, UserRegister userRegister) {
		if(uRepository.existsByEmailAndIdNot(userRegister.getEmail(), id))
			return new Response("User exist", false); 
		Optional<UserRegister> uOptional = uRepository.findById(id);
		if(!uOptional.isPresent())
			return new Response("User not found", false); 
		UserRegister userRegister2 = uOptional.get();
		userRegister2.setEmail(userRegister.getEmail());
		userRegister2.setPassword(userRegister.getPassword());
		uRepository.save(userRegister2);
		return new Response("User edited", true);
	}
	public Response deleteUser(Integer id) {
		Optional<UserRegister> uOptional = uRepository.findById(id);
		if(!uOptional.isPresent()) {
			uRepository.deleteById(id);
			return new Response("User deleted", false);
		}
		return new Response("User not found", false); 
	}
}
