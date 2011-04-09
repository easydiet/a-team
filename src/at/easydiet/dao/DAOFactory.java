package at.easydiet.dao;

import org.hibernate.Session;

public class DAOFactory
{

    @SuppressWarnings("unused")
    private GenericHibernateDAO<?, ?> instantiateDAO(Class<?> daoClass)
    {
        try
        {
            GenericHibernateDAO<?, ?> dao = (GenericHibernateDAO<?, ?>) daoClass
                    .newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass,
                    ex);
        }
    }

    // You could override this if you don't want HibernateUtil for lookup
    protected Session getCurrentSession()
    {
        return HibernateUtil.currentSession();
    }
}
