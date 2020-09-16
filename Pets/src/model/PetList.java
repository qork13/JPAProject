package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petlist")
public class PetList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="TYPE")
	private String type;
	@Column(name="NAME")
	private String name;
	@Column(name="OWNER")
	private String owner;
	
	
	//PetList no arg constructor
	public PetList() {
		super();
	}
	
	//PetList constructor taking in type, name and owner
	public PetList(String type, String name, String owner) {
		super();
		this.type = type;
		this.name = name;
		this.owner = owner;
	}
	
	
	//Getters and Setters for id, type, name, and owner
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	//Method to return the Type, Name and Owner to the program as a readable String
	public String returnPetDetails() {
		return "Type: " + type + " Name: " + name + " Owner: " + owner;
	}

}
