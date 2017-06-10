package com.scm.vo;


import org.hibernate.validator.constraints.NotBlank;

/**
 * class GudangGrup
 * 
 * @author Generator
 */
public class GudangGrupVO {

	@NotBlank(message="id tidak bokeh kosong")
	private String id;

	@NotBlank(message="nama tidak bokeh kosong")
	private String nama;
	
	private Integer version;

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
}