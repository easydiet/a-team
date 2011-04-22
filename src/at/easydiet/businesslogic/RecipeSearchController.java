package at.easydiet.businesslogic;

import java.util.List;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.RecipeDAO;
import at.easydiet.model.Recipe;
import at.easydiet.util.StringUtils;

public class RecipeSearchController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(RecipeSearchController.class);
    
    private String _queryString;
    private ArrayList<RecipeBO> _recipes;
    
    public RecipeSearchController()
    {
        _recipes = new ArrayList<RecipeBO>();
    }

    
    public ArrayList<RecipeBO> getRecipes()
    {
        return _recipes;
    }
    
    /**
     * Gets the queryString.
     * @return the queryString
     */
    public String getQueryString()
    {
        return _queryString;
    }

    /**
     * Sets the queryString.
     * @param queryString the queryString to set
     */
    public void setQueryString(String queryString)
    {
        _queryString = queryString;
    }
    
    public void doSearch()
    {
        _recipes.clear();
        if(StringUtils.isNullOrWhitespaceOnly(_queryString))
        {
            return;
        }
        RecipeDAO dao = DAOFactory.getInstance().getRecipeDAO();
        
        List<Recipe> recipes = dao.findByQuery(_queryString);
        for (Recipe recipe : recipes)
        {
            _recipes.add(new RecipeBO(recipe));
        }
    }
}
