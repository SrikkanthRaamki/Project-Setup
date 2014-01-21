package com.anpi.app.api.service.impl;

import com.anpi.app.api.dao.GenericDAO;
import com.anpi.app.api.service.GenericService;
import org.hibernate.ReplicationMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * The Class AbstractGenericServiceImpl.
 */
@Service
@Transactional(propagation = SUPPORTS)
public class AbstractGenericServiceImpl<T, PK extends Serializable> implements GenericService<Object, PK> {

//    @Autowired
//    @Qualifier("genericDAO")
	private GenericDAO<Object, PK> genericDAO;

	/**
	 * Instantiates a new abstract generic service impl.
	 */
	public AbstractGenericServiceImpl() {
	}

	public List<Object> getAll() {
        System.out.println("=======================================");
        System.out.println(genericDAO);
		return genericDAO.getAll();
	}

//	public List<Object> findAll() {
//		return genericDAO.findAll();
//	}

	public Object get(PK id) {
		return genericDAO.get(id);
	}

//	public List<Object> get(Set<PK> ids) {
//		return genericDAO.get(ids);
//	}

	public boolean exists(PK id) {
		return genericDAO.exists(id);
	}

	@Transactional(propagation = REQUIRED)
	public Object save(Object object) {
		Object returnObject = genericDAO.save(object);
		afterSave(object);
		return returnObject;
	}

//	@Transactional(propagation = REQUIRED)
//	public Set<Object> saveAll(Set<Object> objects) {
//		Set<Object> returnSet = genericDAO.saveAll(objects);
//		for (Object object : returnSet) {
//			afterSave(object);
//		}
//		return returnSet;
//	}

	/**
	 * After save.
	 *
	 * @param object the object
	 */
	public void afterSave(Object object) {

	}

	@Transactional(propagation = REQUIRED)
	public void delete(PK id) {
		genericDAO.delete(id);
	}
//	@Transactional(propagation = REQUIRED)
//	public void deleteAll(Set<Object> objects) {
//		genericDAO.deleteAll(objects);
//	}

	@Transactional(propagation = REQUIRED)
	public void delete(Object object) {
		genericDAO.delete(object);
	}

	/**
	 * Gets the generic dao.
	 *
	 * @return the generic dao
	 */
	public GenericDAO<Object, PK> getGenericDAO() {
        return genericDAO;
	}

	/**
	 * Sets the generic dao.
	 *
	 * @param genericDAO the generic dao
	 */
	public void setGenericDAO(GenericDAO<Object, PK> genericDAO) {
		this.genericDAO = genericDAO;
	}


	public void flush() {
		genericDAO.flush();
	}

	@Transactional(propagation = REQUIRED)
	public void replicate(Object object, ReplicationMode replicationMode) {
		getGenericDAO().replicate(object, replicationMode);
	}

	@Transactional(propagation = REQUIRED)
	public void evict(Object object){
		getGenericDAO().evict(object);
	}

	@Transactional(propagation = REQUIRED)
	public Object merge(Object object) {
		Object returnObject = genericDAO.merge(object);
		return returnObject;
	}


}
