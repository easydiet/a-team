package at.easydiet.businessobjects;

import java.util.List;
import java.util.Set;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.DietParameter;
import at.easydiet.model.Meal;
import at.easydiet.model.MealLine;


public class MealBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(MealBO.class);

    private Meal _meal;
    
    public Meal getMeal()
    {
        return _meal;
    }

    public MealBO()
    {
        this(new Meal("MA", "Mahlzeit", null));
    }
    
    /** 
     * Initializes a new instance of the {@link MealBO} class. 
     * @param meal
     */
    public MealBO(Meal meal)
    {
        super();
        _meal = meal;
    }

    /**
     * @return
     * @see at.easydiet.model.Meal#getMealId()
     */
    public long getMealId()
    {
        return _meal.getMealId();
    }

    /**
     * @param mealId
     * @see at.easydiet.model.Meal#setMealId(long)
     */
    public void setMealId(long mealId)
    {
        _meal.setMealId(mealId);
    }

    /**
     * @return
     * @see at.easydiet.model.Meal#getCode()
     */
    public String getCode()
    {
        return _meal.getCode();
    }

    /**
     * @param code
     * @see at.easydiet.model.Meal#setCode(java.lang.String)
     */
    public void setCode(String code)
    {
        _meal.setCode(code);
    }

    /**
     * @return
     * @see at.easydiet.model.Meal#getName()
     */
    public String getName()
    {
        return _meal.getName();
    }

    /**
     * @param name
     * @see at.easydiet.model.Meal#setName(java.lang.String)
     */
    public void setName(String name)
    {
        _meal.setName(name);
    }

    /**
     * @return
     * @see at.easydiet.model.Meal#getDietParameters()
     */
    public Set<DietParameter> getDietParameters()
    {
        return _meal.getDietParameters();
    }

    /**
     * @param dietParameters
     * @see at.easydiet.model.Meal#setDietParameters(java.util.Set)
     */
    public void setDietParameters(Set<DietParameter> dietParameters)
    {
        _meal.setDietParameters(dietParameters);
    }

    /**
     * @return
     * @see at.easydiet.model.Meal#getMealLines()
     */
    public List<MealLine> getMealLines()
    {
        return _meal.getMealLines();
    }
    

    public ArrayList<MealLineBO> getMealLineBOs()
    {
        ArrayList<MealLineBO> bos = new ArrayList<MealLineBO>();
        for (MealLine line : getMealLines())
        {
            bos.add(new MealLineBO(line));
        }
        return bos;
    }

    /**
     * @param mealLines
     * @see at.easydiet.model.Meal#setMealLines(java.util.Set)
     */
    public void setMealLines(List<MealLine> mealLines)
    {
        _meal.setMealLines(mealLines);
    }
    
    public void removeFromTimeSpan()
    {
        if(_meal.getTimeSpan() != null)
        {
            _meal.getTimeSpan().getMeals().remove(this);
            _meal.setTimeSpan(null);
        }
    }
    
    public void addToTimeSpan(TimeSpanBO timeSpan)
    {
        timeSpan.getMeals().add(_meal);
        _meal.setTimeSpan(timeSpan.getTimeSpan());
    }



}

