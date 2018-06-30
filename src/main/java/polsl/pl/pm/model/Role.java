package polsl.pl.pm.model;

import javafx.beans.DefaultProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name="role_id")
	private int id;


	@Column(name="role", unique = true)
	private String role;

    public Role() {
    }

    public Role(String role) {
		this.role = role;

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
