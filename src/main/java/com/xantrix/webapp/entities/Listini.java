
package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "dettlistini")
@Data
public class Listini implements Serializable
{
    private static final long serialVersionUID = 3686007637156242157L;

    @Id
	@Column(name = "Id")
    private String id;

    @Column(name = "Descrizione")
    private String descrizione;

    @Column(name = "Obsoleto")
    private String obsoleto;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "listino", orphanRemoval = true)
	@JsonManagedReference
	private Set<DettListini> dettListini = new HashSet<>();
}