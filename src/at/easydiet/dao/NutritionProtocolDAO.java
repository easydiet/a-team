package at.easydiet.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import at.easydiet.model.DietTreatment;
import at.easydiet.model.NutritionProtocol;

/**
 * A DAO implementation for NutritionProtocol objects.
 */
public class NutritionProtocolDAO 
        extends GenericHibernateDAO<NutritionProtocol, Long>
{
    public List<NutritionProtocol> findByDietTreatment(DietTreatment model) 
    {
        return findByCriteria(Restrictions.eq("dietTreatment", model));
    }
}