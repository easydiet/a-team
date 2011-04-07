package at.easydiet.model;
// Generated 06.04.2011 16:43:36 by Hibernate Tools 3.4.0.CR1


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ParameterDefinitionDataType.
 * @see at.easydiet.model.ParameterDefinitionDataType
 * @author Hibernate Tools
 */
public class ParameterDefinitionDataTypeHome {

    private static final Log log = LogFactory.getLog(ParameterDefinitionDataTypeHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();
    
    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(ParameterDefinitionDataType transientInstance) {
        log.debug("persisting ParameterDefinitionDataType instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ParameterDefinitionDataType instance) {
        log.debug("attaching dirty ParameterDefinitionDataType instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ParameterDefinitionDataType instance) {
        log.debug("attaching clean ParameterDefinitionDataType instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ParameterDefinitionDataType persistentInstance) {
        log.debug("deleting ParameterDefinitionDataType instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ParameterDefinitionDataType merge(ParameterDefinitionDataType detachedInstance) {
        log.debug("merging ParameterDefinitionDataType instance");
        try {
            ParameterDefinitionDataType result = (ParameterDefinitionDataType) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ParameterDefinitionDataType findById( java.lang.String id) {
        log.debug("getting ParameterDefinitionDataType instance with id: " + id);
        try {
            ParameterDefinitionDataType instance = (ParameterDefinitionDataType) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.ParameterDefinitionDataType", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<ParameterDefinitionDataType> findByExample(ParameterDefinitionDataType instance) {
        log.debug("finding ParameterDefinitionDataType instance by example");
        try {
            List<ParameterDefinitionDataType> results = (List<ParameterDefinitionDataType>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.ParameterDefinitionDataType")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
}

