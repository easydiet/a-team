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
 * Home object for domain model class NutritionProtocol.
 * @see at.easydiet.model.NutritionProtocol
 * @author Hibernate Tools
 */
public class NutritionProtocolHome {

    private static final Log log = LogFactory.getLog(NutritionProtocolHome.class);

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
    
    public void persist(NutritionProtocol transientInstance) {
        log.debug("persisting NutritionProtocol instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(NutritionProtocol instance) {
        log.debug("attaching dirty NutritionProtocol instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(NutritionProtocol instance) {
        log.debug("attaching clean NutritionProtocol instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(NutritionProtocol persistentInstance) {
        log.debug("deleting NutritionProtocol instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public NutritionProtocol merge(NutritionProtocol detachedInstance) {
        log.debug("merging NutritionProtocol instance");
        try {
            NutritionProtocol result = (NutritionProtocol) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public NutritionProtocol findById( long id) {
        log.debug("getting NutritionProtocol instance with id: " + id);
        try {
            NutritionProtocol instance = (NutritionProtocol) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.NutritionProtocol", id);
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
    
    public List<NutritionProtocol> findByExample(NutritionProtocol instance) {
        log.debug("finding NutritionProtocol instance by example");
        try {
            List<NutritionProtocol> results = (List<NutritionProtocol>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.NutritionProtocol")
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

