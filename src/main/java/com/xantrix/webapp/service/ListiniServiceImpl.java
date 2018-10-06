
package com.xantrix.webapp.service;

import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xantrix.webapp.entities.Listini;
import com.xantrix.webapp.repository.ListiniRepository;
 

@Service
@Transactional
public class ListiniServiceImpl implements ListiniService 
{

    @Autowired
	ListiniRepository listiniRepository;

    @Override
    public void InsListino(Listini listino) 
    {
        listiniRepository.saveAndFlush(listino);
	}
}