package com.xantrix.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xantrix.webapp.entities.Prezzi;

public interface PrezziRepository extends JpaRepository<Prezzi, Long>
{
	public Prezzi findByCodArtAndIdList(String CodArt, String IdList); 
}
