package com.example.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Employee")
public class Employee {

	@Id
	@Field("_id")
	private ObjectId id;
	private String name;
	private String gender;
	private String email;
	private String position;


	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Employee() {
	}

	@Override
	public String toString() {
		return String.format("Customer[id= %s , name ='%s' , Gender ='%s' , Email = '%s' , Position = '%s']", id, name,
				gender, email, position);
	}
}
