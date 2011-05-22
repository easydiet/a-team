package at.easydiet.businesslogic;

import org.apache.pivot.collections.List;

import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.view.MealContainer;

/**
 * This controller provides data and functions for the {@link MealContainer}
 * 
 */
public class MealContainerController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(MealContainerController.class);

    /**
     * This is the current meal
     */
    private MealBO                               _meal;

    /**
     * Stores all the meallines for this meal
     */
    private List<MealLineBO>                     _mealLines;

    /**
     * Gets the meal.
     * 
     * @return the meal
     */
    public MealBO getMeal()
    {
        return _meal;
    }

    /**
     * Sets the meal.
     * 
     * @param meal
     *            the meal to set
     */
    public void setMeal(MealBO meal)
    {
        _meal = meal;
        refresh();
    }

    /**
     * Reload the meal
     */
    public void refresh()
    {
        _mealLines = _meal.getMealLines();
    }

    /**
     * Gets the mealLines.
     * 
     * @return the mealLines
     */
    public List<MealLineBO> getMealLines()
    {
        return _mealLines;
    }
}
