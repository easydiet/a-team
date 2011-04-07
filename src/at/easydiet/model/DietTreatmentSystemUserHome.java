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
 * Home object for domain model class DietTreatmentSystemUser.
 * @see at.easydiet.model.DietTreatmentSystemUser
 * @author Hibernate Tools
 */
public class DietTreatmentSystemUserHome {

    private static final Log log = LogFactory.getLog(DietTreatmentSystemUserHome.class);

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
    
    public void persist(DietTreatmentSystemUser transientInstance) {
        log.debug("persisting DietTreatmentSystemUser instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DietTreatmentSystemUser instance) {
        log.debug("attaching dirty DietTreatmentSystemUser instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DietTreatmentSystemUser instance) {
        log.debug("attaching clean DietTreatmentSystemUser instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DietTreatmentSystemUser persistentInstance) {
        log.debug("deleting DietTreatmentSystemUser instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DietTreatmentSystemUser merge(DietTreatmentSystemUser detachedInstance) {
        log.debug("merging DietTreatmentSystemUser instance");
        try {
            DietTreatmentSystemUser result = (DietTreatmentSystemUser) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DietTreatmentSystemUser findById( int id) {
        log.debug("getting DietTreatmentSystemUser instance with id: " + id);
        try {
            DietTreatmentSystemUser instance = (DietTreatmentSystemUser) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.DietTreatmentSystemUser", id);
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
    
    public List<DietTreatmentSystemUser> findByExample(DietTreatmentSystemUser instance) {
        log.debug("finding DietTreatmentSystemUser instance by example");
        try {
            List<DietTreatmentSystemUser> results = (List<DietTreatmentSystemUser>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.DietTreatmentSystemUser")
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

