package at.easydiet.domainlogic;

import java.util.List;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.ParameterDefinitionUnitDAO;
import at.easydiet.model.ParameterDefinitionUnit;

public class ParameterDefinitionUnitController
{
    public static final org.apache.log4j.Logger      LOG = org.apache.log4j.Logger
                                                                 .getLogger(ParameterDefinitionUnitController.class);

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static ParameterDefinitionUnitController _singleton;

    public static ParameterDefinitionUnitController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new ParameterDefinitionUnitController();
        }
        return _singleton;
    }

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

    private ParameterDefinitionUnitController()
    {
    }
}
