package br.com.reorganize.task.entities.enums;

public enum Status {
	CREATE (1, "Criada"),
	IN_PROGRESS (2, "Em progresso"),
	COMPLETED (3, "Concluída");

	private int id;
	private String description;
	
	private Status(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static Status valueOf(int id) {
		for (Status s : Status.values()) {
			if (s.getId() == id) {
				return s;
			}
		}
		
		throw new IllegalArgumentException("Invalid Status id.");
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
