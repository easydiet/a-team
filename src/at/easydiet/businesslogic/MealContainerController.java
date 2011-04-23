package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;

public class MealContainerController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(MealContainerController.class);

    private MealBO                              _meal;
    private ArrayList<MealLineBO>               _mealLines;

    /**
     * Gets the meal.
     * @return the meal
     */
    public MealBO getMeal()
    {
        return _meal;
    }

    /**
     * Sets the meal.
     * @param meal the meal to set
     */
    public void setMeal(MealBO meal)
    {
        _meal = meal;
        refresh();
    }

    public void refresh()
    {
        _mealLines = _meal.getMealLineBOs();
    }

    /**
     * Gets the mealLines.
     * @return the mealLines
     */
    public ArrayList<MealLineBO> getMealLines()
    {
        return _mealLines;
    }

    public MealContainerController()
    {}
    
    
}
