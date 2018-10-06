package com.xantrix.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xantrix.webapp.appconf.AppConfig;
import com.xantrix.webapp.entities.DettListini;
import com.xantrix.webapp.service.PrezziService;

@RestController
public class PrezziController
{
	private static final Logger logger = LoggerFactory.getLogger(PrezziController.class);

	@Autowired
	private PrezziService prezziService;
	
	@Autowired
    private AppConfig Config;

	@RequestMapping(value = "/prezzo/{codart}", method = RequestMethod.GET)
	public Double getPriceCodArt(@PathVariable("codart") String CodArt)  
	{
		String IdList = Config.getListino();
		
		logger.info("Listino di Riferimento: " + IdList);
		
		DettListini prezzo =  prezziService.SelPrezzo(CodArt, IdList);
		
		if (prezzo != null)
		{
			logger.info("Prezzo Articolo: " + prezzo.getPrezzo());
		
			return prezzo.getPrezzo();
		}
		else
		{
			logger.warn("Prezzo Articolo Assente!!");
			
			return 0.0;
		}
			
	}

}
