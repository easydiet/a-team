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
 * Home object for domain model class ParameterDefinition.
 * @see at.easydiet.model.ParameterDefinition
 * @author Hibernate Tools
 */
public class ParameterDefinitionHome {

    private static final Log log = LogFactory.getLog(ParameterDefinitionHome.class);

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
    
    public void persist(ParameterDefinition transientInstance) {
        log.debug("persisting ParameterDefinition instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ParameterDefinition instance) {
        log.debug("attaching dirty ParameterDefinition instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ParameterDefinition instance) {
        log.debug("attaching clean ParameterDefinition instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ParameterDefinition persistentInstance) {
        log.debug("deleting ParameterDefinition instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ParameterDefinition merge(ParameterDefinition detachedInstance) {
        log.debug("merging ParameterDefinition instance");
        try {
            ParameterDefinition result = (ParameterDefinition) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ParameterDefinition findById( long id) {
        log.debug("getting ParameterDefinition instance with id: " + id);
        try {
            ParameterDefinition instance = (ParameterDefinition) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.ParameterDefinition", id);
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
    
    public List<ParameterDefinition> findByExample(ParameterDefinition instance) {
        log.debug("finding ParameterDefinition instance by example");
        try {
            List<ParameterDefinition> results = (List<ParameterDefinition>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.ParameterDefinition")
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

