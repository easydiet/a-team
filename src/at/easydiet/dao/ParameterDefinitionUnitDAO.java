package at.easydiet.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import at.easydiet.model.ParameterDefinitionUnit;

/**
 * A DAO implementation for ParameterDefinitionUnit objects.
 */
public class ParameterDefinitionUnitDAO extends
        GenericHibernateDAO<ParameterDefinitionUnit, Long>
{
    /**
     * Gets a {@link ParameterDefinitionUnit} by it's name
     * 
     * @param name
     *            The name of the {@link ParameterDefinitionUnit}
     * @return The {@link ParameterDefinitionUnit}
     */
    public ParameterDefinitionUnit findByName(String name)
    {
        ParameterDefinitionUnit unit = new ParameterDefinitionUnit();
        unit.setName(name);

        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Example.create(unit).excludeZeroes());
        return (ParameterDefinitionUnit) crit.uniqueResult();
    }
}