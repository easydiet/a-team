package at.easydiet.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;

/**
 * This is the base class for creating Data Access Objects.
 * @author Daniel
 */
public abstract class AbstractDao
{
    private Session     session;
    private Transaction tx;

    /**
     * Initializes a new instance of the {@link AbstractDao} class.
     */
    public AbstractDao()
    {
        HibernateFactory.buildIfNeeded();
    }

    /**
     * Updates the specified object within the database. 
     * If the object does not exist yet, it will be created. 
     * @param obj the object to save.
     */
    protected void saveOrUpdate(Object obj)
    {
        try
        {
            startOperation();
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch (HibernateException e)
        {
            handleException(e);
        }
        finally
        {
            HibernateFactory.close(session);
        }
    }

    /**
     * Deletes the specified object from the database.
     * @param obj the object to delete
     */
    protected void delete(Object obj)
    {
        try
        {
            startOperation();
            session.delete(obj);
            tx.commit();
        }
        catch (HibernateException e)
        {
            handleException(e);
        }
        finally
        {
            HibernateFactory.close(session);
        }
    }

    /**
     * Loads the instance with the specified id from the database.
     * @param clazz the type of the item to load
     * @param id the id 
     * @return the object loaded from the database or null if the object was not found.
     */
    protected Object find(Class<?> clazz, Long id)
    {
        Object obj = null;
        try
        {
            startOperation();
            obj = session.load(clazz, id);
            tx.commit();
        }
        catch (HibernateException e)
        {
            handleException(e);
        }
        finally
        {
            HibernateFactory.close(session);
        }
        return obj;
    }

    /**
     * Loads all items from the database.
     * @param clazz the type of the items to load. 
     * @return a list containing all items loaded or null if an error occured
     */
    protected List<?> findAll(Class<?> clazz)
    {
        List<?> objects = null;
        try
        {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            tx.commit();
        }
        catch (HibernateException e)
        {
            handleException(e);
        }
        finally
        {
            HibernateFactory.close(session);
        }
        return objects;
    }

    /**
     * Specifieds how to handle exceptions. 
     * @param e the exception thrown 
     * @throws DataAccessLayerException always thrown. 
     */
    protected void handleException(HibernateException e)
            throws DataAccessLayerException
    {
        HibernateFactory.rollback(tx);
        throw new DataAccessLayerException(e);
    }

    /**
     * Starts a new transaction. 
     * @throws HibernateException
     */
    protected void startOperation() throws HibernateException
    {
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
    }
}
