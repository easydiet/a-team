package at.easydiet.dao;

import java.util.List;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import at.easydiet.model.Recipe;

/**
 * A DAO implementation for Recipe objects.
 */
public class RecipeDAO extends GenericHibernateDAO<Recipe, Long>
{

    /**
     * Find {@link Recipe}s by query
     * 
     * @param queryString
     *            name and bls code of the recipe seperated by whitespace
     * @return List of all {@link Recipe} matching the name and/or bls code
     */
    public List<Recipe> findByQuery(String queryString)
    {
        Recipe template1 = new Recipe();
        template1.setName(queryString);

        Recipe template2 = new Recipe();
        template2.setBlsCode(queryString);

        Example ex = Example.create(template1).enableLike(MatchMode.START)
                .excludeZeroes().ignoreCase();
        Example ex2 = Example.create(template2).enableLike(MatchMode.START)
                .excludeZeroes().ignoreCase();

        return super.findByCriteriaSearch("name", Restrictions.or(ex, ex2));
    }

    /**
     * Find recipes by BLS code
     * 
     * @param blsPattern
     *            The matching BLS code
     * @return List of all {@link Recipe} matching the BLS Code
     */
    public List<Recipe> findByBlsCode(String blsPattern)
    {
        Recipe template1 = new Recipe();
        template1.setBlsCode(blsPattern);

        Example ex = Example.create(template1).enableLike(MatchMode.EXACT)
                .excludeZeroes().ignoreCase();

        return super.findByCriteriaSearch("name", ex);
    }
}