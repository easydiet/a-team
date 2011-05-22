package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.HibernateException;

import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.RecipeIngredientBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.RecipeDAO;
import at.easydiet.domainlogic.DietParameterController;
import at.easydiet.domainlogic.ParameterDefinitionUnitController;
import at.easydiet.domainlogic.DietParameterController.ValidationResult;
import at.easydiet.util.StringUtils;

public class CreateRecipeController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateRecipeController.class);

    private static CreateRecipeController       _singleton;

    public static CreateRecipeController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new CreateRecipeController();
        }
        return _singleton;
    }

    /**
     * Constructor
     */
    private CreateRecipeController()
    {
        _errors = new ArrayList<String>();
    }

    // the recipe, which will be created and edited by this instance of the
    // controller
    private RecipeBO     _currentRecipe;
    private List<String> _errors;

    public RecipeBO getRecipe()
    {
        return _currentRecipe;
    }

    public void setRecipe(RecipeBO recipe)
    {
        _currentRecipe = recipe;
    }

    public void createNew()
    {
        _currentRecipe = new RecipeBO();
        _currentRecipe.setUnit(ParameterDefinitionUnitController.getInstance()
                .getDefault());
    }

    public RecipeIngredientBO addRecipeIngredient(RecipeBO ingredient)
    {
        RecipeIngredientBO bo = new RecipeIngredientBO();
        bo.setRecipe(_currentRecipe);
        bo.setIngredient(ingredient);
        bo.setAmount(1);
        _currentRecipe.addIngredients(bo);
        return bo;
    }

    public void removeRecipeIngredient(RecipeIngredientBO recipeIngredientBO)
    {
        _currentRecipe.removeIngredients(recipeIngredientBO);
    }

    public void checkRecipe()
    {
        _errors.clear();

        CreateRecipeController.getInstance().recalculateNutrimentParameters();

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

    public List<String> getErrors()
    {
        return _errors;
    }

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

    public void recalculateNutrimentParameters()
    {
        _currentRecipe.calcParameters();
    }
}
