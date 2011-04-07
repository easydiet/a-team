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
 * Home object for domain model class NutrimentParameter.
 * @see at.easydiet.model.NutrimentParameter
 * @author Hibernate Tools
 */
public class NutrimentParameterHome {

    private static final Log log = LogFactory.getLog(NutrimentParameterHome.class);

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
    
    public void persist(NutrimentParameter transientInstance) {
        log.debug("persisting NutrimentParameter instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(NutrimentParameter instance) {
        log.debug("attaching dirty NutrimentParameter instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(NutrimentParameter instance) {
        log.debug("attaching clean NutrimentParameter instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(NutrimentParameter persistentInstance) {
        log.debug("deleting NutrimentParameter instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public NutrimentParameter merge(NutrimentParameter detachedInstance) {
        log.debug("merging NutrimentParameter instance");
        try {
            NutrimentParameter result = (NutrimentParameter) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public NutrimentParameter findById( long id) {
        log.debug("getting NutrimentParameter instance with id: " + id);
        try {
            NutrimentParameter instance = (NutrimentParameter) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.NutrimentParameter", id);
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
    
    public List<NutrimentParameter> findByExample(NutrimentParameter instance) {
        log.debug("finding NutrimentParameter instance by example");
        try {
            List<NutrimentParameter> results = (List<NutrimentParameter>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.NutrimentParameter")
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

