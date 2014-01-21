package com.anpi.app.api.dao.impl;

import com.anpi.app.api.dao.GenericDAO;
import com.anpi.app.api.util.ClassUtils;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class AbstractGenericDAO.
 */

public abstract class AbstractGenericDAO<T, PK extends Serializable> implements GenericDAO<Object, PK> {

    /** The log. */
  //  final Logger log = SecurityUtility.getLogger(getClass());

    /** The persistent class. */
    private  Class<T> persistentClass;

    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

    /** The hibernate template. */
    //HibernateTemplate hibernateTemplate;

    /**
     * Instantiates a new abstract generic dao.
     */
    @SuppressWarnings("unchecked")
    public AbstractGenericDAO() {
      this.persistentClass = (Class<T>) ClassUtils.getTypeArguments(AbstractGenericDAO.class, getClass()).get(0);
    }

    /**
     * Instantiates a new abstract generic dao.
     *
     * @param persistentClass the persistent class
     */
    public AbstractGenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }


    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Object entity = (Object) this.getCurrentSession().get(this.persistentClass, id);
        return entity != null;
    }

    public List<Object> getAll() {
        return    (List<Object>) generify(this.getCurrentSession().createQuery("from " + this.persistentClass.getName()).list());
    }
    @SuppressWarnings("unchecked")
    public Object get(PK id) {
        return  (Object) this.getCurrentSession().get(this.persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public Object get(PK id, LockMode lockMode) {
        return (Object) this.getCurrentSession().get(this.persistentClass, id, lockMode);
    }

    public Object save(Object object) {
        this.getCurrentSession().saveOrUpdate(object);
        return object;
    }

    @SuppressWarnings("unchecked")
    public Object merge(Object object) {
        return (Object)this.getCurrentSession().merge(object);
    }

    public void delete(PK id) {
        this.getCurrentSession().delete(get(id));
    }

    public void delete(Object object) {
        this.getCurrentSession().delete(object);
    }

    public void flush() {
        this.getCurrentSession().flush();
    }

//    @SuppressWarnings("unchecked")
//	public List<Object> findAll() {
//
//        try {
//            List results = (List) getHibernateTemplate().execute(new HibernateCallback() {
//                public Object doInHibernate(Session session) throws HibernateException, SQLException {
//                    Query q = session.getNamedQuery(persistentClass.getSimpleName() + ".findAll");
//                    return q.list();
//                }
//            });
//            return generify(results);
//        } catch (HibernateException ex) {
//            return getAll();
//        }
//    }


   public List<Object> findByCriteria(Criterion...criterion) {
       Criteria criteria = this.getCurrentSession().createCriteria(getPersistentClass());
       for (Criterion c : criterion) {
           criteria.add(c);
       }
       return criteria.list();
   }


//   public List<Object> findByNamedQuery(String namedQuery) {
//        return findByNamedQuery(namedQuery, new String[0], new Object[0]);
//    }
//
//    public List<Object> findByNamedQuery(String namedQuery, String name, Object param) {
//        List<String> names = new ArrayList<String>();
//        names.add(name);
//        List<Object> params = new ArrayList<Object>();
//        params.add(param);
//        return findByNamedQuery(
//                namedQuery,
//                names,
//                params);
//    }

//    @SuppressWarnings("unchecked")
//	public List<Object> findByNamedQuery(String namedQuery, String names[], Object params[]) {
//        String simpleName = getPersistentClass().getSimpleName();
//        if (!namedQuery.startsWith(simpleName)) {
//            namedQuery = simpleName + "." + namedQuery;
//        }
//
//        List results = getHibernateTemplate().findByNamedQueryAndNamedParam(
//                namedQuery,
//                names,
//                params
//        );
//
//        return generify(results);
//    }

//    public List<Object> findByNamedQuery(String namedQuery, Map<String, Object> params) {
//        List<String> names = new ArrayList<String>();
//        List<Object> values = new ArrayList<Object>();
//        for (Map.Entry<String, Object> entry : params.entrySet()) {
//            names.add(entry.getKey());
//            values.add(entry.getValue());
//        }
//
//        return findByNamedQuery(namedQuery, names, values);
//    }

//    public List<Object> findByNamedQuery(String namedQuery, Map.Entry<String, Object>... params) {
//        List<String> names = new ArrayList<String>();
//        List<Object> values = new ArrayList<Object>();
//        for (Map.Entry<String, Object> entry : params) {
//            names.add(entry.getKey());
//            values.add(entry.getValue());
//        }
//        return findByNamedQuery(namedQuery, names, values);
//    }
//    public Object findSingleResult(String namedQuery) {
//        return findSingleResult(
//                namedQuery,
//                new ArrayList<String>(),
//                new ArrayList<Object>()
//        );
//    }

//    public Object findSingleResult(String namedQuery, String name, Object param) {
//        List<String> names = new ArrayList<String>();
//        names.add(name);
//        List<Object> params = new ArrayList<Object>();
//        params.add(param);
//        return findSingleResult(
//                namedQuery,
//                names,
//                params);
//    }

//    public Object findSingleResult(String namedQuery, String[] names, Object[] params) {
//        return findSingleResult(namedQuery, Arrays.asList(names), Arrays.asList(params));
//    }
//
//    public Object findSingleResult(String namedQuery, Map<String, Object> params) {
//        List<String> names = new ArrayList<String>();
//        List<Object> values = new ArrayList<Object>();
//        for (Map.Entry<String, Object> entry : params.entrySet()) {
//            names.add(entry.getKey());
//            values.add(entry.getValue());
//        }
//
//        return findSingleResult(namedQuery, names, values);
//    }
//
//    public Object findSingleResult(String namedQuery, Map.Entry<String, Object>... params) {
//        List<String> names = new ArrayList<String>();
//        List<Object> values = new ArrayList<Object>();
//        for (Map.Entry<String, Object> entry : params) {
//            names.add(entry.getKey());
//            values.add(entry.getValue());
//        }
//        return findSingleResult(namedQuery, names, values);
//    }

    /**
     * Find single result.
     *
     * @param namedQuery the named query
     * @param names the names
     * @param params the params
     *
     * @return the t
     */
//    public Object findSingleResult(String namedQuery, List<String> names, List<Object> params) {
//        List<Object> results = findByNamedQuery(namedQuery, names, params);
//
//        if (results == null || results.size() == 0) { //no results
//            return null;
//        } else if (results.size() == 1) {             //the normal case
//            return results.get(0);
//        } else {                                      //cases where joins return the same instance multiple times
//            int size = results.size();
//		    Object first = results.get(0);
//		    for ( int i=1; i<size; i++ ) {
//			    if ( results.get(i)!=first ) {
//				    throw new NonUniqueResultException( results.size() );
//			    }
//		    }
//		    return generify(first);
//        }
//    }

    /**
     * Find by named query.
     *
     * @param namedQuery the named query
     * @param names the names
     * @param params the params
     *
     * @return the list< t>
     */
//    public List<Object> findByNamedQuery(String namedQuery, List<String> names, List params) {
//        String[] stringNames = names.toArray(new String[names.size()]);
//        return  findByNamedQuery(namedQuery, stringNames, params.toArray());
//    }

    /**
     * Generify.
     *
     * @param results the results
     *
     * @return the list< t>
     *
     * @throws ClassCastException the class cast exception
     */
    @SuppressWarnings("unchecked")
    protected List<Object> generify(List<Object> results) throws ClassCastException {
        ArrayList<Object> genericResults = new ArrayList<Object>(results.size());
        for (Object o : results) {
            genericResults.add((Object) o);
        }
        return genericResults;
    }

    /**
     * Generify.
     *
     * @param object the object
     *
     * @return the t
     *
     * @throws ClassCastException the class cast exception
     */
    @SuppressWarnings("unchecked")
    protected Object generify(Object object) throws ClassCastException {
        return (Object) object;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void replicate(Object entity, ReplicationMode replicationMode) throws DataAccessException {
        this.getCurrentSession().replicate(entity, replicationMode);
    }

    public void replicate(String entityName, Object entity, ReplicationMode replicationMode) throws DataAccessException {
        this.getCurrentSession().replicate(entityName, entity, replicationMode);
    }

	/**
	 * Gets the hibernate template.
	 *
	 * @return the hibernate template
	 */
//	public HibernateTemplate getHibernateTemplate() {
//		return hibernateTemplate;
//	}

	public void evict(Object object){
        this.getCurrentSession().evict(object);
	}

}
