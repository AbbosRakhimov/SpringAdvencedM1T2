package uz.pdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.entity.ProgrammLanguage;
import uz.pdp.payload.Response;
import uz.pdp.repository.ProgrammLanguageRepository;

@Service
public class ProgrammLanguageService {

	@Autowired
	ProgrammLanguageRepository pRepository;
	
	public Response addProgLanguage(ProgrammLanguage pLanguage) {
		if(pRepository.existsByLanguage(pLanguage.getLanguage()))
			return new Response("Language exist", false);
		ProgrammLanguage programmLanguage= new ProgrammLanguage(pLanguage.getLanguage());
		pRepository.save(programmLanguage);
		return new Response("Language added", true);
	}
	public List<ProgrammLanguage> getLanguages(Integer id){
		return pRepository.findAllProgrammLanguageWithTask(id);
	}
	public Response editPLang(Integer id, ProgrammLanguage programmLanguage) {
		if(pRepository.existsByLanguageAndIdNot(programmLanguage.getLanguage(), id))
			return new Response("Language exist", false);
		Optional<ProgrammLanguage> pOptional = pRepository.findById(id);
		if(!pOptional.isPresent())
			return new Response("Langiage not found", false);
		ProgrammLanguage programmLanguage2 = pOptional.get();
		programmLanguage2.setLanguage(programmLanguage.getLanguage());
		pRepository.save(programmLanguage2);
		return new Response("Language edited", true);
	}
	public Response deletePlang(Integer id) {
		Optional<ProgrammLanguage> pOptional = pRepository.findById(id);
		if(pOptional.isPresent()) {
			pRepository.deleteById(id);
			return new Response("Language deleted", true);
		}
		return new Response("Langiage not found", false);
	}
}
