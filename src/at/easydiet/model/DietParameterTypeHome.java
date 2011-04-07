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
 * Home object for domain model class DietParameterType.
 * @see at.easydiet.model.DietParameterType
 * @author Hibernate Tools
 */
public class DietParameterTypeHome {

    private static final Log log = LogFactory.getLog(DietParameterTypeHome.class);

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
    
    public void persist(DietParameterType transientInstance) {
        log.debug("persisting DietParameterType instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DietParameterType instance) {
        log.debug("attaching dirty DietParameterType instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DietParameterType instance) {
        log.debug("attaching clean DietParameterType instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DietParameterType persistentInstance) {
        log.debug("deleting DietParameterType instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DietParameterType merge(DietParameterType detachedInstance) {
        log.debug("merging DietParameterType instance");
        try {
            DietParameterType result = (DietParameterType) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DietParameterType findById( java.lang.String id) {
        log.debug("getting DietParameterType instance with id: " + id);
        try {
            DietParameterType instance = (DietParameterType) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.DietParameterType", id);
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
    
    public List<DietParameterType> findByExample(DietParameterType instance) {
        log.debug("finding DietParameterType instance by example");
        try {
            List<DietParameterType> results = (List<DietParameterType>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.DietParameterType")
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

