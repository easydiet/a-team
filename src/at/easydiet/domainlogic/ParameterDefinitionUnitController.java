package at.easydiet.domainlogic;

import java.util.List;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.ParameterDefinitionUnitDAO;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * Provides data and methods for compatibility with recipes,...
 */
public class ParameterDefinitionUnitController
{
    /**
     * Logger for debugging purposes
     */
    public static final org.apache.log4j.Logger      LOG = org.apache.log4j.Logger
                                                                 .getLogger(ParameterDefinitionUnitController.class);

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static ParameterDefinitionUnitController _singleton;

    /**
     * Get a Instance of this {@link ParameterDefinitionUnitController}
     * 
     * @return The instance of this {@link ParameterDefinitionUnitController}
     */
    public static ParameterDefinitionUnitController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new ParameterDefinitionUnitController();
        }
        return _singleton;
    }

    /**
     * Get a list of {@link ParameterDefinitionUnitBO}s that are compatible with
     * a {@link RecipeBO}
     * 
     * @param recipe
     *            The {@link RecipeBO}
     * @return List of compatible {@link ParameterDefinitionUnitBO}s
     */
    public ArrayList<ParameterDefinitionUnitBO> getUnitsCompatibleWithRecipe(
            RecipeBO recipe)
    {
        // TODO: Check for available type converters with recipe.getUnit()
        ParameterDefinitionUnitDAO dao = DAOFactory.getInstance()
                .getParameterDefinitionUnitDAO();
        List<ParameterDefinitionUnit> units = dao.findAll();
        ArrayList<ParameterDefinitionUnitBO> bos = new ArrayList<ParameterDefinitionUnitBO>();
        for (ParameterDefinitionUnit unit : units)
        {
            bos.add(new ParameterDefinitionUnitBO(unit));
        }
        return bos;
    }

    /**
     * Initializes a new instance of the
     * {@link ParameterDefinitionUnitController} class.
     */
    private ParameterDefinitionUnitController()
    {}

    /**
     * Gets the default {@link ParameterDefinitionUnitBO}
     * 
     * @return Instance of the default {@link ParameterDefinitionUnitBO}
     */
    public ParameterDefinitionUnitBO getDefault()
    {
        ParameterDefinitionUnitDAO dao = DAOFactory.getInstance()
                .getParameterDefinitionUnitDAO();
        return new ParameterDefinitionUnitBO(dao.findByName("g"));
    }
}
