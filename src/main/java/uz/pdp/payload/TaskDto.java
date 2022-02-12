package uz.pdp.payload;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TaskDto {
	
	@NotNull(message = "TaskText not by Empty")
	private String taskText;
	
	@NotNull(message = "TaskText not by Empty")
	private Integer programmLanguageId;
}
