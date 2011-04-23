package at.easydiet.businesslogic;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.model.MealLine;

public class CreateDietPlanViewController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateDietPlanViewController.class);

    private DietPlanBO                          _dietPlan;

    /**
     * Gets the dietPlan.
     * @return the dietPlan
     */
    public DietPlanBO getDietPlan()
    {
        return _dietPlan;
    }

    public TimeSpanBO createTimeSpan()
    {
        TimeSpanBO t = new TimeSpanBO();
        t.addToDietPlan(_dietPlan);
        return t;
    }

    public static CreateDietPlanViewController _singleton;

    public static CreateDietPlanViewController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new CreateDietPlanViewController();
        }
        return _singleton;
    }

    public void createNew()
    {
        _dietPlan = new DietPlanBO();
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
        if(selectedRow == null) return;
        selectedRow.removeFromMeal();
    }

    public MealLineBO addRecipeAsAlternative(MealLineBO mealLine, RecipeBO recipe)
    { 
        MealLineBO alternative = new MealLineBO();
        alternative.setParent(mealLine.getMealLine());
        alternative.setMeal(mealLine.getMeal());
        alternative.setRecipe(recipe.getRecipe());
        alternative.setQuantity(recipe.getAmount());
        
        // insert alternative after mealLine
        int index = mealLine.getMeal().getMealLines().indexOf(mealLine.getMealLine()) + 1;
        mealLine.getMeal().getMealLines().add(index, alternative.getMealLine());
        return alternative;
    }

}
