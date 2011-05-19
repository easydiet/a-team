package at.easydiet.domainlogic;

import java.util.List;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.RecipeDAO;
import at.easydiet.model.Recipe;
import at.easydiet.util.StringUtils;

/**
 * Provides data and methods for the recipe search
 */
public class RecipeSearchController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(RecipeSearchController.class);

    /**
     * The search query
     */
    private String                               _queryString;

    /**
     * List of {@link RecipeBO}s
     */
    private ArrayList<RecipeBO>                  _recipes;

    /**
     * Initializes a new instance of the {@link RecipeSearchController} class.
     */
    public RecipeSearchController()
    {
        _recipes = new ArrayList<RecipeBO>();
    }

    /**
     * Gets all {@link RecipeBO}s matching the query
     * 
     * @return List of filtered {@link RecipeBO}s
     */
    public ArrayList<RecipeBO> getRecipes()
    {
        return _recipes;
    }

    /**
     * Gets the queryString.
     * 
     * @return the queryString
     */
    public String getQueryString()
    {
        return _queryString;
    }

    /**
     * Sets the queryString.
     * 
     * @param queryString
     *            the queryString to set
     */
    public void setQueryString(String queryString)
    {
        _queryString = queryString;
    }

    /**
     * Start the search
     */
    public void doSearch()
    {
        _recipes.clear();
        if (StringUtils.isNullOrWhitespaceOnly(_queryString))
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
