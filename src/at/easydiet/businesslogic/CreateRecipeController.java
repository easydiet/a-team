package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.HibernateException;

import at.easydiet.businessobjects.NutrimentParameterBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.RecipeIngredientBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.RecipeDAO;
import at.easydiet.domainlogic.DietParameterController;
import at.easydiet.domainlogic.ParameterDefinitionUnitController;
import at.easydiet.domainlogic.DietParameterController.ValidationResult;
import at.easydiet.util.StringUtils;

/**
 * Provides data and actions for the {@link CreateRecipeController}.
 */
public class CreateRecipeController
{
    /**
     * Logger for debugging purposes
     */
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateRecipeController.class);

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static CreateRecipeController       _singleton;

    /**
     * Returns a globally unique instance of this class.
     * 
     * @return a globally unique instance which gets initiated on the first
     *         call.
     */
    public static CreateRecipeController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new CreateRecipeController();
        }
        return _singleton;
    }

    /**
     * Initializes a new instance of the {@link CreateRecipeController} class.
     */
    private CreateRecipeController()
    {
        _errors = new ArrayList<String>();
    }

    /**
     * Stores the current opened {@link RecipeBO}
     */
    private RecipeBO     _currentRecipe;
    /**
     * Stores a list of error messages
     */
    private List<String> _errors;

    /**
     * Gets the {@link RecipeBO}
     * 
     * @return The current opened {@link RecipeBO}
     */
    public RecipeBO getRecipe()
    {
        return _currentRecipe;
    }

    /**
     * Sets the {@link RecipeBO}
     * 
     * @param recipe
     *            The new {@link RecipeBO}
     */
    public void setRecipe(RecipeBO recipe)
    {
        _currentRecipe = recipe;
    }

    /**
     * Create a new {@link RecipeBO}
     */
    public void createNew()
    {
        _currentRecipe = new RecipeBO();
        _currentRecipe.setUnit(ParameterDefinitionUnitController.getInstance()
                .getDefault());
    }

    /**
     * Add an ingredient to a {@link RecipeBO}
     * 
     * @param ingredient
     *            The recipe to add
     * @return The {@link RecipeIngredientBO}
     */
    public RecipeIngredientBO addRecipeIngredient(RecipeBO ingredient)
    {
        RecipeIngredientBO bo = new RecipeIngredientBO();
        bo.setRecipe(_currentRecipe);
        bo.setIngredient(ingredient);
        bo.setAmount(1);
        _currentRecipe.addIngredients(bo);
        return bo;
    }

    /**
     * Remoce a {@link RecipeIngredientBO} from the {@link RecipeBO}
     * 
     * @param recipeIngredientBO
     *            The {@link RecipeIngredientBO} to remove
     */
    public void removeRecipeIngredient(RecipeIngredientBO recipeIngredientBO)
    {
        _currentRecipe.removeIngredients(recipeIngredientBO);
    }

    /**
     * Checks if the {@link RecipeBO} data is correct
     */
    public void checkRecipe()
    {
        _errors.clear();

        recalculateNutrimentParameters();

        if (StringUtils.isNullOrWhitespaceOnly(_currentRecipe.getName()))
        {
            _errors.add("Kein Rezept Name angegeben!");
        }

        if (_currentRecipe.getIngredients().getLength() == 0)
        {
            _errors.add("Sie müssen mindestens eine Zutat zum Rezept hinzufügen!");
        }

        validateNutrimentParameters();
    }

    /**
     * Validate the {@link NutrimentParameterBO}s of the {@link RecipeBO}
     */
    private void validateNutrimentParameters()
    {
        List<ValidationResult> violations = DietParameterController
                .getInstance().validateRecipeDietParameters(_currentRecipe);

        for (ValidationResult validationResult : violations)
        {

            String error = String
                    .format("Zielparameter '%s' von '%s' wird nicht eingehalten: %.2f%s ist %s %s%s",
                            validationResult.getDietParameter()
                                    .getParameterDefinition().getName(),
                            validationResult.getAffectedObject()
                                    .getDisplayText(), validationResult
                                    .getCurrentValue(), validationResult
                                    .getDietParameter()
                                    .getParameterDefinitionUnit().getName(),
                            validationResult.getErrorType().getDisplayText(),
                            validationResult.getDietParameter().getValue(),
                            validationResult.getDietParameter()
                                    .getParameterDefinitionUnit().getName());

            _errors.add(error);
        }
    }

    /**
     * Gets the list of error messages
     * 
     * @return List of error messages
     */
    public List<String> getErrors()
    {
        return _errors;
    }

    /**
     * Save the {@link RecipeBO}
     * 
     * @return True if save was successful
     */
    public boolean saveRecipe()
    {
        checkRecipe();

        if (getErrors().getLength() > 0) return false;

        // add cooking duration to cooking description
        _currentRecipe.setCookInstructions(String.format(
                "%s%nZubereitungszeit: %s Stunden",
                _currentRecipe.getCookInstructions(),
                _currentRecipe.getCookingTime()));
        _currentRecipe.setBlsCode("Z100000");

        try
        {
            HibernateUtil.currentSession().beginTransaction();
            RecipeDAO dao = DAOFactory.getInstance().getRecipeDAO();
            dao.makePersistent(_currentRecipe.getModel());
            HibernateUtil.currentSession().getTransaction().commit();
            _currentRecipe = null;
            return true;
        }
        catch (HibernateException e)
        {
            LOG.error("Could not save recipe", e);
            HibernateUtil.currentSession().getTransaction().rollback();
            return false;
        }
    }

    /**
     * Recalculate the {@link NutrimentParameterBO}s
     */
    public void recalculateNutrimentParameters()
    {
        _currentRecipe.calcParameters();
    }
}
