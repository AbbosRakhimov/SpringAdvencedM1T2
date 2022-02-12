package uz.pdp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Email not by empty")
	@Column(nullable = false, unique =  true)
	private String email;
	
	@NotNull(message = "Password not by Empty")
	@Column(nullable = false)
	private String password;
	
	@ManyToMany
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Task> tasks;

	public UserRegister(@NotNull(message = "Email not by empty") String email,
			@NotNull(message = "Password not by Empty") String password, Set<Task> tasks) {
		super();
		this.email = email;
		this.password = password;
		this.tasks = tasks;
	}

	public UserRegister(@NotNull(message = "Email not by empty") String email,
			@NotNull(message = "Password not by Empty") String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
	
}
