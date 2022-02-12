package uz.pdp.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String taskText;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ProgrammLanguage programmLanguage;

	public Task(String taskText) {
		super();
		this.taskText = taskText;
	}

	public Task(String taskText, ProgrammLanguage programmLanguage) {
		super();
		this.taskText = taskText;
		this.programmLanguage = programmLanguage;
	}
	
	
}
