package com.xantrix.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dettlistini")
public class Prezzi implements Serializable
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
	
	public Prezzi() {}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getCodArt()
	{
		return codArt;
	}

	public void setCodArt(String codArt)
	{
		this.codArt = codArt;
	}

	public String getIdList()
	{
		return idList;
	}

	public void setIdList(String idList)
	{
		this.idList = idList;
	}

	public Double getPrezzo()
	{
		return prezzo;
	}

	public void setPrezzo(Double prezzo)
	{
		this.prezzo = prezzo;
	}
	
	

}
