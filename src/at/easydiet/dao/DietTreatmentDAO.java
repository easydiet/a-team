package at.easydiet.dao;

import org.hibernate.CacheMode;

import at.easydiet.model.DietTreatment;

/**
 * A DAO implementation for DietTreatment objects.
 */
public class DietTreatmentDAO extends GenericHibernateDAO<DietTreatment, Long>
{
    @Override
    public void refresh(DietTreatment entity)
    {
        getSession().setCacheMode(CacheMode.IGNORE);
        getSession().evict(entity);
        super.refresh(entity);
        getSession().setCacheMode(CacheMode.NORMAL);
    }
}