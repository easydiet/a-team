package at.easydiet.domainlogic;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.easydiet.EasyDietApplication;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietPlanDAO;

public class DietPlanEditingController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietPlanEditingController.class);

    private DietPlanBO                          _dietPlan;

    /**
     * Gets the dietPlan.
     * @return the dietPlan
     */
    public DietPlanBO getDietPlan()
    {
        return _dietPlan;
    }

    public void setDietPlan(DietPlanBO dietPlan)
    {
        _dietPlan = dietPlan;
    }

    public TimeSpanBO createTimeSpan()
    {
        TimeSpanBO t = new TimeSpanBO();
        t.addToDietPlan(_dietPlan);
        return t;
    }

    private static DietPlanEditingController _singleton;

    public static DietPlanEditingController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new DietPlanEditingController();
        }
        return _singleton;
    }

    public void createNew(DietTreatmentBO dietTreatment)
    {
        _dietPlan = new DietPlanBO();
        _dietPlan.setDietTreatment(dietTreatment.getTreatment());
    }

    public MealBO createMeal(TimeSpanBO timeSpan)
    {
        MealBO meal = new MealBO();
        meal.addToTimeSpan(timeSpan);
        return meal;
    }

    public MealLineBO addRecipeToMeal(MealBO meal, RecipeBO recipe)
    {
        MealLineBO line = new MealLineBO();
        line.setMeal(meal.getMeal());
        line.setRecipe(recipe.getRecipe());
        line.setQuantity(recipe.getAmount());
        meal.getMealLines().add(line.getMealLine());
        return line;
    }

    public void removeMealLine(MealLineBO selectedRow)
    {
        if (selectedRow == null) return;
        selectedRow.removeFromMeal();
    }

    public MealLineBO addRecipeAsAlternative(MealLineBO mealLine,
            RecipeBO recipe)
    {
        MealLineBO alternative = new MealLineBO();
        alternative.setParent(mealLine.getMealLine());
        alternative.setMeal(mealLine.getMeal());
        alternative.setRecipe(recipe.getRecipe());
        alternative.setQuantity(recipe.getAmount());

        // insert alternative after mealLine
        int index = mealLine.getMeal().getMealLines()
                .indexOf(mealLine.getMealLine()) + 1;
        mealLine.getMeal().getMealLines().add(index, alternative.getMealLine());
        return alternative;
    }

    public void saveDietPlan()
    {
        validateDietPlan();

        SimpleDateFormat formatter = new SimpleDateFormat(
                EasyDietApplication.DATE_FORMAT);
        // generate a good name if it's a new plan
        if (_dietPlan.getDietPlanId() <= 0)
        {
            _dietPlan.setCreatedOn(new Date());
            String name = String.format("Diätplan vom %s",
                    formatter.format(_dietPlan.getCreatedOn()));
            _dietPlan.setName(name);
        }

        // update creator
        _dietPlan.setCreator(SystemUserController.getInstance()
                .getCurrentUser().getSystemUser());

        DietPlanDAO dao = DAOFactory.getInstance().getDietPlanDAO();
        dao.makePersistent(_dietPlan.getDietPlan());
        dao.flush();
        _dietPlan = null;
    }

    public void validateDietPlan()
    {

    }

    private DietPlanEditingController()
    {

    }
}
