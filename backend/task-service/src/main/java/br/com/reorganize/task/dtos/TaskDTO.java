package br.com.reorganize.task.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.reorganize.task.entities.User;
import br.com.reorganize.task.entities.enums.Priority;
import br.com.reorganize.task.entities.enums.Status;

public class TaskDTO {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	private Integer status;
	
	@NotNull
	private Date deadline;
	
	@NotNull
	private Integer priority;
	
	@NotNull
	private User user;
	
	public TaskDTO() {
		
	}

	public TaskDTO(String title, String description, Status status, Date deadline, Priority priority, User user) {
		this.title = title;
		this.description = description;
		setStatus(status);
		this.deadline = deadline;
		setPriority(priority);
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		if (status != null) {
			return Status.valueOf(status);
		}
		return null;
	}

	public void setStatus(Status status) {
		if (status != null) {
			this.status = status.getId();
		}
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Priority getPriority() {
		return Priority.valueOf(priority);
	}

	public void setPriority(Priority priority) {
		if (priority != null) {
			this.priority = priority.getId();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
