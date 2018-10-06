package com.xantrix.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "dettlistini")
@Data
public class DettListini implements Serializable
{
	private static final long serialVersionUID = 8777751177774522519L;
	
	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "CODART")
	private String codArt;
	
	@Column(name = "IDLIST")
	private String idList;
	
	@Column(name = "PREZZO")
	private Double prezzo;

	@ManyToOne
	@JoinColumn(name = "Id", referencedColumnName = "Id")
	@JsonBackReference
	private Listini listino;

}
