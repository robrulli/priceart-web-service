package com.xantrix.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xantrix.webapp.entities.DettListini;

public interface PrezziRepository extends JpaRepository<DettListini, Long>
{
	public DettListini findByCodArtAndIdList(String CodArt, String IdList); 
}
