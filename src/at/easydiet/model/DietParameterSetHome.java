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
 * Home object for domain model class DietParameterSet.
 * @see at.easydiet.model.DietParameterSet
 * @author Hibernate Tools
 */
public class DietParameterSetHome {

    private static final Log log = LogFactory.getLog(DietParameterSetHome.class);

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
    
    public void persist(DietParameterSet transientInstance) {
        log.debug("persisting DietParameterSet instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DietParameterSet instance) {
        log.debug("attaching dirty DietParameterSet instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DietParameterSet instance) {
        log.debug("attaching clean DietParameterSet instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DietParameterSet persistentInstance) {
        log.debug("deleting DietParameterSet instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DietParameterSet merge(DietParameterSet detachedInstance) {
        log.debug("merging DietParameterSet instance");
        try {
            DietParameterSet result = (DietParameterSet) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DietParameterSet findById( long id) {
        log.debug("getting DietParameterSet instance with id: " + id);
        try {
            DietParameterSet instance = (DietParameterSet) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.DietParameterSet", id);
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
    
    public List<DietParameterSet> findByExample(DietParameterSet instance) {
        log.debug("finding DietParameterSet instance by example");
        try {
            List<DietParameterSet> results = (List<DietParameterSet>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.DietParameterSet")
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

