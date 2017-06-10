/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.entities;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "M_USER")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User extends BaseModel {
	
	@Id
	@NotEmpty(message = "ID tidak boleh kosong")
	@Column(name = "USER_ID", nullable = false,length=50)
	private String id;
	
	@NotEmpty(message = "Nama tidak boleh kosong")
	@Column(name = "NAMA", nullable = false,length=50)
	private String nama;
	
	@NotEmpty(message = "Password tidak boleh kosong")
	@Column(name = "PASSWORD", nullable = false,length=50)
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

	
}	
