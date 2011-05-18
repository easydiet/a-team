package at.easydiet.dao;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import at.easydiet.model.Meal;

/**
 * A DAO implementation for Meal objects.
 */
public class MealDAO extends GenericHibernateDAO<Meal, Long>
{

    /**
     * Find all meal codes
     * 
     * @return Collection of meal codes as Strings
     */
    @SuppressWarnings("unchecked")
    public Collection<String> findCodes()
    {
        Criteria crit = getSession().createCriteria(getPersistentClass())
                .setProjection(Projections.groupProperty("code"))
                .addOrder(Order.asc("code"));
        return crit.list();
    }

    /**
     * Find all meal names
     * 
     * @return Collection of all meal names as Strings
     */
    @SuppressWarnings("unchecked")
    public Collection<String> findNames()
    {
        Criteria crit = getSession().createCriteria(getPersistentClass())
                .setProjection(Projections.groupProperty("name"))
                .addOrder(Order.asc("name"));
        return crit.list();
    }
}