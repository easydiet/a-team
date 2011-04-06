package at.easydiet.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateFactory
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(HibernateFactory.class);
    private static SessionFactory               _sessionFactory;

    /**
     * Constructs a new Singleton SessionFactory
     * 
     * @return
     * @throws HibernateException
     */
    public static SessionFactory buildSessionFactory()
            throws HibernateException
    {
        if (_sessionFactory != null)
        {
            closeFactory();
        }
        return configureSessionFactory();
    }

    /**
     * Builds a SessionFactory, if it hasn't been already.
     */
    public static SessionFactory buildIfNeeded()
            throws DataAccessLayerException
    {
        if (_sessionFactory != null)
        {
            return _sessionFactory;
        }
        try
        {
            return configureSessionFactory();
        }
        catch (HibernateException e)
        {
            throw new DataAccessLayerException(e);
        }
    }

    /**
     * Returns the current session factory.
     * @return
     */
    public static SessionFactory getSessionFactory()
    {
        return _sessionFactory;
    }

    /**
     * Opens a new hibernate session.
     * @return the hibernate session
     * @throws HibernateException 
     */
    public static Session openSession() throws HibernateException
    {
        buildIfNeeded();
        return _sessionFactory.openSession();
    }

    /**
     * Closes the hibernate factory.
     */
    public static void closeFactory()
    {
        if (_sessionFactory != null)
        {
            try
            {
                _sessionFactory.close();
            }
            catch (HibernateException e)
            {
                LOGGER.error("Couldn't close SessionFactory", e);
            }
        }
    }

    /**
     * Closes the given hibernate session.
     * @param session the session to close
     */
    public static void close(Session session)
    {
        if (session != null)
        {
            try
            {
                session.close();
            }
            catch (HibernateException e)
            {
                LOGGER.error("Couldn't close Session", e);
            }
        }
    }

    /**
     * Performs a rollback on the given transaction.
     * @param tx the transaction to roll back.
     */
    public static void rollback(Transaction tx)
    {
        try
        {
            if (tx != null)
            {
                tx.rollback();
            }
        }
        catch (HibernateException e)
        {
            LOGGER.error("Couldn't rollback Transaction", e);
        }
    }

    /**
     * Configures the session factory to use the correct hibernate configuration.
     * @return the session factory which can be used.
     * @throws HibernateException
     */
    private static SessionFactory configureSessionFactory()
            throws HibernateException
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        _sessionFactory = configuration.buildSessionFactory();
        return _sessionFactory;
    }
}
