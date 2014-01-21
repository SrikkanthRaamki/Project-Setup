package com.anpi.app.api.dao.impl; 
 
import org.springframework.stereotype.Repository; 
import  com.anpi.app.api.dao.FaxLookupDAO; 
import  com.anpi.app.api.model.FaxLookup; 
import  com.anpi.app.api.dao.impl.AbstractGenericDAO; 
 
@Repository("FaxLookupDAO") 
public class FaxLookupDAOImpl  extends AbstractGenericDAO <FaxLookup, Integer> implements FaxLookupDAO{ } 
