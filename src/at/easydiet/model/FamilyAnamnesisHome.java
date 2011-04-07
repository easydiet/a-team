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
 * Home object for domain model class FamilyAnamnesis.
 * @see at.easydiet.model.FamilyAnamnesis
 * @author Hibernate Tools
 */
public class FamilyAnamnesisHome {

    private static final Log log = LogFactory.getLog(FamilyAnamnesisHome.class);

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
    
    public void persist(FamilyAnamnesis transientInstance) {
        log.debug("persisting FamilyAnamnesis instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(FamilyAnamnesis instance) {
        log.debug("attaching dirty FamilyAnamnesis instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FamilyAnamnesis instance) {
        log.debug("attaching clean FamilyAnamnesis instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(FamilyAnamnesis persistentInstance) {
        log.debug("deleting FamilyAnamnesis instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FamilyAnamnesis merge(FamilyAnamnesis detachedInstance) {
        log.debug("merging FamilyAnamnesis instance");
        try {
            FamilyAnamnesis result = (FamilyAnamnesis) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public FamilyAnamnesis findById( long id) {
        log.debug("getting FamilyAnamnesis instance with id: " + id);
        try {
            FamilyAnamnesis instance = (FamilyAnamnesis) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.FamilyAnamnesis", id);
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
    
    public List<FamilyAnamnesis> findByExample(FamilyAnamnesis instance) {
        log.debug("finding FamilyAnamnesis instance by example");
        try {
            List<FamilyAnamnesis> results = (List<FamilyAnamnesis>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.FamilyAnamnesis")
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

