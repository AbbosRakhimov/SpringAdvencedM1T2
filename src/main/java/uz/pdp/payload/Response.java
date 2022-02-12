package uz.pdp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

	private String message;
	private boolean success;
	private Object object;
	
	public Response(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	
}
