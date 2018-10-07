package com.xantrix.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xantrix.webapp.appconf.AppConfig;
import com.xantrix.webapp.entities.DettListini;
import com.xantrix.webapp.exception.BindingException;
import com.xantrix.webapp.service.PrezziService;

@RestController
@RequestMapping("/prezzi")
public class PrezziController
{
	private static final Logger logger = LoggerFactory.getLogger(PrezziController.class);

	@Autowired
	private PrezziService prezziService;
	 
	@Autowired
	private AppConfig Config;
	
	@Autowired
	private ResourceBundleMessageSource errMessage;

	@RequestMapping(value = "/cerca/codice/{codart}", method = RequestMethod.GET)
	public ResponseEntity<?> getPriceCodArt(@PathVariable("codart") String CodArt)  
	{
		double retVal = 0;

		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectNode responseNode = mapper.createObjectNode();


		String IdList = Config.getListino();
		
		logger.info("Listino di Riferimento: " + IdList);
		
		DettListini prezzo =  prezziService.SelPrezzo(CodArt, IdList);
		
		if (prezzo != null)
		{
			logger.info("Prezzo Articolo: " + prezzo.getPrezzo());
		
			retVal = prezzo.getPrezzo();
		}
		else
		{
			logger.warn("Prezzo Articolo Assente!!");
		}

		responseNode.put("prezzo", retVal);

		return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
			
	}
	// ------------------- INSERT PREZZO LISTINO ------------------------------------
	@RequestMapping(value = "/inserisci", method = RequestMethod.POST)
	public ResponseEntity<DettListini> createPrice(@Valid @RequestBody DettListini dettListini, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) 
			throws BindingException 
	{
		logger.info(String.format("Salviamo il prezzo %s dell'articolo %s", dettListini.getPrezzo(),  dettListini.getCodArt()));
		
		if (bindingResult.hasErrors())
		{
			String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
			
			logger.warn(MsgErr);

			throw new BindingException(MsgErr);
		}
		 
		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectNode responseNode = mapper.createObjectNode();

		prezziService.InsPrezzo(dettListini);
		
		responseNode.put("code", HttpStatus.OK.toString());
		responseNode.put("message", "Inserimento Prezzo " + dettListini.getPrezzo() + " Eseguito Con Successo");

		return new ResponseEntity<DettListini>(headers, HttpStatus.CREATED);
	}

	// ------------------- DELETE PREZZO LISTINO ------------------------------------
	@RequestMapping(value = "/elimina/{codart}/{idlist}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePrice(@PathVariable("codart") String CodArt, @PathVariable("idlist") String IdList)
	{
		logger.info(String.format("Eliminazione prezzo listino %s dell'articolo %s",IdList,CodArt));

		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectNode responseNode = mapper.createObjectNode();

		prezziService.DelPrezzo(CodArt, IdList);

		responseNode.put("code", HttpStatus.OK.toString());
		responseNode.put("message", "Eliminazione Prezzo Eseguita Con Successo");

		return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
	}

}
