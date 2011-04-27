package at.easydiet.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Helper Singleton class to manage Hibernate Sessions.
 */
public class HibernateUtil
{

    /** ThreadLocal Session Map */
    public static final ThreadLocal<Session> MAP                      = new ThreadLocal<Session>();

    private static final Logger              LOGGER                   = Logger.getLogger(HibernateUtil.class);

    private static final SessionFactory      SESSION_FACTORY;

    private static final String              KEY_EASYHIBERNATE_DRIVER = "easydiet.database.driver_class";
    private static final String              KEY_EASYHIBERNATE_URL = "easydiet.database.url";
    private static final String              KEY_EASYHIBERNATE_USERNAME = "easydiet.database.username";
    private static final String              KEY_EASYHIBERNATE_PASSWORD = "easydiet.database.password";
    private static final String              KEY_EASYHIBERNATE_DIALECT = "easydiet.database.dialect";

    /** Make default construct private */
    private HibernateUtil()
    {}

    /** Loads Hibernate config. */
    static
    {
        try
        {
            LOGGER.debug("HibernateUtil.static - loading config");

            Configuration cfg = new Configuration();
            cfg.configure();
            
            // load external set properties if available
            setProperties(cfg);

            SESSION_FACTORY = cfg.buildSessionFactory();
            LOGGER.debug("HibernateUtil.static - end");
        }
        catch (HibernateException ex)
        {
            throw new RuntimeException("Exception building SessionFactory: "
                    + ex.getMessage(), ex);
        }
    }


    private static void setProperties(Configuration cfg)
    {
        setProperty(cfg, "hibernate.connection.driver_class", KEY_EASYHIBERNATE_DRIVER);
        setProperty(cfg, "hibernate.connection.url", KEY_EASYHIBERNATE_URL);
        setProperty(cfg, "hibernate.connection.username", KEY_EASYHIBERNATE_USERNAME);
        setProperty(cfg, "hibernate.connection.password", KEY_EASYHIBERNATE_PASSWORD);
        setProperty(cfg, "hibernate.dialect", KEY_EASYHIBERNATE_DIALECT);
    }

    private static void setProperty(Configuration cfg, String hibernateKey,
            String easyKey)
    {
        if(System.getProperties().containsKey(easyKey))
        {
            cfg.setProperty(hibernateKey, System.getProperty(easyKey));
        }
    }

    /**
     * Gets Hibernate Session for current thread. When finished, users must
     * return session using {@link #closeSession() closeSession()} method.
     * @return Hibernate Session for current thread.
     * @throws HibernateException if there is an error opening a new session.
     */
    public static Session currentSession() throws HibernateException
    {
        Session s = (Session) MAP.get();
        // Open a new Session, if this Thread has none yet
        if (s == null)
        {
            s = SESSION_FACTORY.openSession();
            MAP.set(s);
        }
        return s;
    }

    /**
     * Closes the Hibernate Session. Users must call this method after calling
     * {@link #currentSession() currentSession()}.
     * @throws HibernateException if session has problem closing.
     */
    public static void closeSession() throws HibernateException
    {
        Session s = (Session) MAP.get();
        MAP.set(null);
        if (s != null)
        {
            s.close();
        }
    }
}