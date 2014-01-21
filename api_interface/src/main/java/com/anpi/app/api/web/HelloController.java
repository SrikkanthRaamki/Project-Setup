package com.anpi.app.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anpi.app.api.model.FaxLookup;
import com.anpi.app.api.service.FaxLookupService;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	FaxLookupService faxLookupService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
    String printWelcome() {
		List<Object> listofFax =  faxLookupService.getAll();
		for(Object o : listofFax){
			FaxLookup fax = (FaxLookup) o;
			System.out.println(fax.getNumber()+"-----"+fax.getEmail());
		}
		
		return "hello";
	}
}