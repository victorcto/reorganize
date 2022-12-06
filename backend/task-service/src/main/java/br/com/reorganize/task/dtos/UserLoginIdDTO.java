package br.com.reorganize.task.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserLoginIdDTO {
	
	@NotNull
	private Long id;

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	public UserLoginIdDTO() {
		
	}

	public UserLoginIdDTO(@NotNull Long id, @NotBlank String username, @NotBlank String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
