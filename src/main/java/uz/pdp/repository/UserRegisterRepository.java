package uz.pdp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.pdp.entity.UserRegister;

public interface UserRegisterRepository extends JpaRepository<UserRegister, Integer> {

	boolean existsByEmail(String email);
	boolean existsByEmailAndIdNot(String email, Integer id);
	boolean existsByEmailAndPassword(String email, String password);
	Optional<UserRegister> findByEmailAndPassword(String email, String password);
	
}
