package at.easydiet.dao;

import java.util.List;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import at.easydiet.model.Recipe;

/**
 * A DAO implementation for Recipe objects.
 */
public class RecipeDAO 
        extends GenericHibernateDAO<Recipe, Long>
{

    public List<Recipe> findByQuery(String queryString)
    {
        Recipe template1 = new Recipe();
        template1.setName(queryString);
        
        Recipe template2 = new Recipe();
        template2.setBlsCode(queryString);
        
        Example ex = Example.create(template1).enableLike(MatchMode.START).excludeZeroes().ignoreCase();
        Example ex2 = Example.create(template2).enableLike(MatchMode.START).excludeZeroes().ignoreCase();
        
        return super.findByCriteriaSearch("name", Restrictions.or(ex, ex2));
    }

    public List<Recipe> findByBlsCode(String blsPattern)
    {
        Recipe template1 = new Recipe();
        template1.setBlsCode(blsPattern);
        
        Example ex = Example.create(template1).enableLike(MatchMode.EXACT).excludeZeroes().ignoreCase();

        return super.findByCriteriaSearch("name", ex);
    }
}