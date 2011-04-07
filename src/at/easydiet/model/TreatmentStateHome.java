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
 * Home object for domain model class TreatmentState.
 * @see at.easydiet.model.TreatmentState
 * @author Hibernate Tools
 */
public class TreatmentStateHome {

    private static final Log log = LogFactory.getLog(TreatmentStateHome.class);

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
    
    public void persist(TreatmentState transientInstance) {
        log.debug("persisting TreatmentState instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(TreatmentState instance) {
        log.debug("attaching dirty TreatmentState instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TreatmentState instance) {
        log.debug("attaching clean TreatmentState instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(TreatmentState persistentInstance) {
        log.debug("deleting TreatmentState instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TreatmentState merge(TreatmentState detachedInstance) {
        log.debug("merging TreatmentState instance");
        try {
            TreatmentState result = (TreatmentState) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public TreatmentState findById( java.lang.String id) {
        log.debug("getting TreatmentState instance with id: " + id);
        try {
            TreatmentState instance = (TreatmentState) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.TreatmentState", id);
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
    
    public List<TreatmentState> findByExample(TreatmentState instance) {
        log.debug("finding TreatmentState instance by example");
        try {
            List<TreatmentState> results = (List<TreatmentState>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.TreatmentState")
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

