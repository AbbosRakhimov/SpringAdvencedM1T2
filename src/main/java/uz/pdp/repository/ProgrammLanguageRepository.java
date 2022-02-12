package uz.pdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uz.pdp.entity.ProgrammLanguage;

public interface ProgrammLanguageRepository extends JpaRepository<ProgrammLanguage, Integer> {

	boolean existsByLanguage(String language);
	boolean existsByLanguageAndIdNot(String language, Integer id);
	
	@Query(value = "select t.task_text, p.language from programmlanguage p join task t on p.id=t.programm_language_id where p.id=:id", nativeQuery = true)
	List<ProgrammLanguage> findAllProgrammLanguageWithTask(Integer id);
}
