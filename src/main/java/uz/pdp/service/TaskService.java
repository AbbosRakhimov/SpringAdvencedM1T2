package uz.pdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.entity.ProgrammLanguage;
import uz.pdp.entity.Task;
import uz.pdp.payload.Response;
import uz.pdp.payload.TaskDto;
import uz.pdp.repository.ProgrammLanguageRepository;
import uz.pdp.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	@Autowired
	ProgrammLanguageRepository pRepository;
	
	public Response addTask(TaskDto taskDto) {
		if(taskRepository.existsByTaskText(taskDto.getTaskText()))
			return new Response("Text exist", false);
		Optional<ProgrammLanguage> pOptional = pRepository.findById(taskDto.getProgrammLanguageId());
		if(!pOptional.isPresent())
			return new Response("Langúage not found", false);
		Task task = new Task(taskDto.getTaskText(), pOptional.get());
		taskRepository.save(task);
		return new Response("Text added", false);
	}
	public Response getTask(Integer id) {
		Optional<Task> tOptional = taskRepository.findById(id);
		if(tOptional.isPresent()) {
			return new Response("Text exist", true, tOptional.get());
		}
		return new Response("Task not found", false);
	}
	public List<Task> getAllTaskByLaguageId(Integer id){
		return taskRepository.findAllByProgrammLanguageId(id);
	}
}
