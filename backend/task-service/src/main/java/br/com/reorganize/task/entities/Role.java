package br.com.reorganize.task.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.com.reorganize.task.entities.enums.RoleName;

@Entity
@Table(name = "papel")
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_papel")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "nome_papel", nullable = false, unique = true)
	private RoleName roleName;
	
	public Role() {
		
	}
	
	public Role(Long id, RoleName roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		return roleName.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roleName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && roleName == other.roleName;
	}

	@Override
	public String toString() {
		return "Role " + id + "\n\tNome do papel = " + roleName.toString();
	}

}
