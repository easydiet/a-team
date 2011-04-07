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
 * Home object for domain model class DietTreatment.
 * @see at.easydiet.model.DietTreatment
 * @author Hibernate Tools
 */
public class DietTreatmentHome {

    private static final Log log = LogFactory.getLog(DietTreatmentHome.class);

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
    
    public void persist(DietTreatment transientInstance) {
        log.debug("persisting DietTreatment instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DietTreatment instance) {
        log.debug("attaching dirty DietTreatment instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DietTreatment instance) {
        log.debug("attaching clean DietTreatment instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DietTreatment persistentInstance) {
        log.debug("deleting DietTreatment instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DietTreatment merge(DietTreatment detachedInstance) {
        log.debug("merging DietTreatment instance");
        try {
            DietTreatment result = (DietTreatment) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DietTreatment findById( long id) {
        log.debug("getting DietTreatment instance with id: " + id);
        try {
            DietTreatment instance = (DietTreatment) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.DietTreatment", id);
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
    
    public List<DietTreatment> findByExample(DietTreatment instance) {
        log.debug("finding DietTreatment instance by example");
        try {
            List<DietTreatment> results = (List<DietTreatment>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.DietTreatment")
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

