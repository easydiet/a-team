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
 * Home object for domain model class DietParameterTemplate.
 * @see at.easydiet.model.DietParameterTemplate
 * @author Hibernate Tools
 */
public class DietParameterTemplateHome {

    private static final Log log = LogFactory.getLog(DietParameterTemplateHome.class);

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
    
    public void persist(DietParameterTemplate transientInstance) {
        log.debug("persisting DietParameterTemplate instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DietParameterTemplate instance) {
        log.debug("attaching dirty DietParameterTemplate instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DietParameterTemplate instance) {
        log.debug("attaching clean DietParameterTemplate instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DietParameterTemplate persistentInstance) {
        log.debug("deleting DietParameterTemplate instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DietParameterTemplate merge(DietParameterTemplate detachedInstance) {
        log.debug("merging DietParameterTemplate instance");
        try {
            DietParameterTemplate result = (DietParameterTemplate) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DietParameterTemplate findById( long id) {
        log.debug("getting DietParameterTemplate instance with id: " + id);
        try {
            DietParameterTemplate instance = (DietParameterTemplate) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.DietParameterTemplate", id);
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
    
    public List<DietParameterTemplate> findByExample(DietParameterTemplate instance) {
        log.debug("finding DietParameterTemplate instance by example");
        try {
            List<DietParameterTemplate> results = (List<DietParameterTemplate>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.DietParameterTemplate")
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

