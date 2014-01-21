package com.anpi.app.api.service.impl; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import  com.anpi.app.api.dao.FaxLookupDAO; 
import  com.anpi.app.api.model.FaxLookup; 
import  com.anpi.app.api.service.FaxLookupService; 
import  com.anpi.app.api.service.impl.AbstractGenericServiceImpl; 
 
@Service 
@Transactional 
public class FaxLookupServiceImpl  extends AbstractGenericServiceImpl<FaxLookup, Integer> implements FaxLookupService { 
private FaxLookupDAO FaxLookupDAO; 
public FaxLookupDAO getFaxLookupDAO() { 
return FaxLookupDAO; 
} 
@Autowired 
public void setFaxLookupDAO(FaxLookupDAO FaxLookupDAO) { 
this.FaxLookupDAO = FaxLookupDAO; 
setGenericDAO(FaxLookupDAO); 
} 
} 
