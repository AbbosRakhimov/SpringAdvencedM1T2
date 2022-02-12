package uz.pdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.pdp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

	boolean existsByTaskText(String taskText);
	boolean existsByTaskTextAndIdNot(String taskText, Integer id);
	List<Task> findAllByProgrammLanguageId(Integer id);
}
