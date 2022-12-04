package br.com.reorganize.task.dtos;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import br.com.reorganize.task.entities.Role;

public class UserDTO {
	
	@NotBlank
	private String username;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotEmpty
	private List<Role> roles;
	
	public UserDTO() {
		
	}

	public UserDTO(@NotBlank String username, @Email @NotBlank String email, @NotBlank String password,
			@NotEmpty List<Role> roles) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
