package br.com.reorganize.task.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.reorganize.task.entities.enums.Priority;
import br.com.reorganize.task.entities.enums.Status;

@Entity
@Table(name = "tarefa")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tarefa")
	private Long id;

	@Column(name = "titulo", nullable = false)
	private String title;

	@Column(name = "descricao")
	private String description;

	@Column(nullable = false)
	private Integer status;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "data_limite", nullable = false)
	private Date deadline;

	@Column(name = "prioridade", nullable = false)
	private Integer priority;

	public Task() {

	}

	public Task(Long id, String title, String description, Status status, Date deadline, Priority priority) {
		this.id = id;
		this.title = title;
		this.description = description;
		setStatus(status);
		this.deadline = deadline;
		setPriority(priority);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return Status.valueOf(status);
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Task " + id + ":\n\tTítulo = " + title + "\n\tDescrição = " + description + "\n\tStatus = "
				+ Status.valueOf(status).getDescription() + "\n\tData Limite = " + deadline + "\n\tPrioridade = "
				+ Priority.valueOf(priority).getDescription();
	}

}
