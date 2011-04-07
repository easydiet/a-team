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
 * Home object for domain model class ContactJournal.
 * @see at.easydiet.model.ContactJournal
 * @author Hibernate Tools
 */
public class ContactJournalHome {

    private static final Log log = LogFactory.getLog(ContactJournalHome.class);

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
    
    public void persist(ContactJournal transientInstance) {
        log.debug("persisting ContactJournal instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ContactJournal instance) {
        log.debug("attaching dirty ContactJournal instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ContactJournal instance) {
        log.debug("attaching clean ContactJournal instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ContactJournal persistentInstance) {
        log.debug("deleting ContactJournal instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ContactJournal merge(ContactJournal detachedInstance) {
        log.debug("merging ContactJournal instance");
        try {
            ContactJournal result = (ContactJournal) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ContactJournal findById( long id) {
        log.debug("getting ContactJournal instance with id: " + id);
        try {
            ContactJournal instance = (ContactJournal) sessionFactory.getCurrentSession()
                    .get("at.easydiet.model.ContactJournal", id);
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
    
    public List<ContactJournal> findByExample(ContactJournal instance) {
        log.debug("finding ContactJournal instance by example");
        try {
            List<ContactJournal> results = (List<ContactJournal>) sessionFactory.getCurrentSession()
                    .createCriteria("at.easydiet.model.ContactJournal")
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

