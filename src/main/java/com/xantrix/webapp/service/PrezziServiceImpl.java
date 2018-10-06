package com.xantrix.webapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.entities.Prezzi;
import com.xantrix.webapp.repository.PrezziRepository;

@Service
@Transactional
public class PrezziServiceImpl implements PrezziService
{
	@Autowired
	PrezziRepository prezziRepository;

	@Override
	public Prezzi SelPrezzo(String CodArt, String Listino)
	{
		return prezziRepository.findByCodArtAndIdList(CodArt, Listino); 
	}
	
	
}
