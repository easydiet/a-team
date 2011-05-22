package at.easydiet.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import at.easydiet.model.ParameterDefinitionUnit;

/**
 * A DAO implementation for ParameterDefinitionUnit objects.
 */
public class ParameterDefinitionUnitDAO 
        extends GenericHibernateDAO<ParameterDefinitionUnit, Long>
{

    public ParameterDefinitionUnit findByName(String name)
    {
        ParameterDefinitionUnit unit = new ParameterDefinitionUnit();
        unit.setName(name);
        
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Example.create(unit).excludeZeroes());
        return (ParameterDefinitionUnit) crit.uniqueResult();
    }
}