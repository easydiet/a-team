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
 * Home object for domain model class PatientStateType.
 * @see at.easydiet.model.PatientStateType
 * @author Hibernate Tools
 */
public class PatientStateTypeHome {

    private static final Log log = LogFactory.getLog(PatientStateTypeHome.class);

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
    
    public void persist(PatientStateType transientInstance) {
        log.debug("persisting PatientStateType instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(PatientStateType instance) {
        log.debug("attaching dirty PatientStateType instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PatientStateType instance) {
        log.debug("attaching clean PatientStateType instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(PatientStateType persistentInstance) {
        log.debug("deleting PatientStateType instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PatientStateType merge(PatientStateType detachedInstance) {
        log.debug("merging PatientStateType instance");
        try {
            PatientStateType result = (PatientStateType) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public PatientStateType findById( java.lang.String id) {
        log.debug("getting PatientStateType instance with id: " + id);
        try {
            PatientStateType instance = (PatientStateType) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.PatientStateType", id);
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
    
    public List<PatientStateType> findByExample(PatientStateType instance) {
        log.debug("finding PatientStateType instance by example");
        try {
            List<PatientStateType> results = (List<PatientStateType>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.PatientStateType")
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

