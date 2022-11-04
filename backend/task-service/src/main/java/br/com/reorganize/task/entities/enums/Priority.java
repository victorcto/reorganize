package br.com.reorganize.task.entities.enums;

public enum Priority {
	HIGH (1, "Alta"),
	MEDIUM (2, "Média"),
	LOW (3, "Baixa");
	
	private int id;
	private String description;
	
	private Priority(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static Priority valueOf(int id) {
		for (Priority p : Priority.values()) {
			if (p.getId() == id) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Invalid Priority id.");
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
