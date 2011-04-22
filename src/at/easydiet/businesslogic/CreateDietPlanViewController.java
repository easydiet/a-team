package at.easydiet.businesslogic;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.view.MealBO;

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

}
