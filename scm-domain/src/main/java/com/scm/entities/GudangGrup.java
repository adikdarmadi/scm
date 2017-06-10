/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scm.locking.OptimisticLockListener;

/**
 *
 * @author Adhityarismawan
 */

@Entity
@Table(name = "M_GUDANG_GRUP")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@EntityListeners(OptimisticLockListener.class)
public class GudangGrup extends BaseModel {
 
	@Id
	@NotBlank(message = "ID tidak boleh kosong")
	@Column(name = "GUDANG_GRUP_ID",nullable = false)
	private String id;
	
	@Version
	@NotNull(message = "Version tidak boleh kosong")
	@Column(name = "VERSION", nullable=false)
	private Integer version;
	
	@Column(name = "IS_ACTIVE", nullable = true)
	private Boolean isActive;

	@Column(name = "DATE_NON_ACTIVE")
	private Date dateNonActive;

	@Column(name = "NAMA", nullable = true,length=50)
    private String nama;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getDateNonActive() {
		return dateNonActive;
	}

	public void setDateNonActive(Date dateNonActive) {
		this.dateNonActive = dateNonActive;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
}
