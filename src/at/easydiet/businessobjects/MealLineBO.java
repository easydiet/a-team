package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.List;

import at.easydiet.model.Meal;
import at.easydiet.model.MealLine;
import at.easydiet.model.ParameterDefinitionUnit;
import at.easydiet.model.Recipe;


public class MealLineBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(MealLineBO.class);

    private MealLine _mealLine;
    
    public MealLine getMealLine()
    {
        return _mealLine;
    }
    
    public MealLineBO()
    {
        this(new MealLine(100, null, null, null, null));
    }
    
    /** 
     * Initializes a new instance of the {@link MealLineBO} class. 
     * @param mealLine
     */
    public MealLineBO(MealLine mealLine)
    {
        super();
        _mealLine = mealLine;
    }
    
    /**
     * Gets a value indicating whether this meal line is an alternative to an other mealline
     * @return
     */
    public boolean isAlternative()
    {
        return getParent() != null;
    }
    
 
    
    /**
     * @return
     * @see at.easydiet.model.MealLine#getUnit()
     */
    public ParameterDefinitionUnit getUnit()
    {
        return _mealLine.getUnit();
    }

    /**
     * @param unit
     * @see at.easydiet.model.MealLine#setUnit(at.easydiet.model.ParameterDefinitionUnit)
     */
    public void setUnit(ParameterDefinitionUnit unit)
    {
        _mealLine.setUnit(unit);
    }
    
    /**
     * @param unit
     * @see at.easydiet.model.MealLine#setUnit(at.easydiet.model.ParameterDefinitionUnit)
     */
    public void setUnit(ParameterDefinitionUnitBO unit)
    {
        _mealLine.setUnit(unit.getParameterDefinitionUnit());
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getUnit()
     */
    public ParameterDefinitionUnit setUnit()
    {
        return _mealLine.getRecipe().getUnit();
    }

    
    
    /**
     * @return
     * @see at.easydiet.model.MealLine#getMealLineId()
     */
    public long getMealLineId()
    {
        return _mealLine.getMealLineId();
    }

    /**
     * @param mealLineId
     * @see at.easydiet.model.MealLine#setMealLineId(long)
     */
    public void setMealLineId(long mealLineId)
    {
        _mealLine.setMealLineId(mealLineId);
    }

    /**
     * @return
     * @see at.easydiet.model.MealLine#getQuantity()
     */
    public float getQuantity()
    {
        return _mealLine.getQuantity();
    }

    /**
     * @param quantity
     * @see at.easydiet.model.MealLine#setQuantity(float)
     */
    public void setQuantity(float quantity)
    {
        _mealLine.setQuantity(quantity);
    }

    /**
     * @return
     * @see at.easydiet.model.MealLine#getInfo()
     */
    public Clob getInfo()
    {
        return _mealLine.getInfo();
    }

    /**
     * @param info
     * @see at.easydiet.model.MealLine#setInfo(java.sql.Clob)
     */
    public void setInfo(Clob info)
    {
        _mealLine.setInfo(info);
    }

    

    /**
     * @return
     * @see at.easydiet.model.MealLine#getAlternatives()
     */
    public List<MealLine> getAlternatives()
    {
        return _mealLine.getAlternatives();
    }

    /**
     * @param alternatives
     * @see at.easydiet.model.MealLine#setAlternatives(java.util.List)
     */
    public void setAlternatives(List<MealLine> alternatives)
    {
        _mealLine.setAlternatives(alternatives);
    }

    /**
     * @return
     * @see at.easydiet.model.MealLine#getRecipe()
     */
    public Recipe getRecipe()
    {
        return _mealLine.getRecipe();
    }

    /**
     * @param recipe
     * @see at.easydiet.model.MealLine#setRecipe(at.easydiet.model.Recipe)
     */
    public void setRecipe(Recipe recipe)
    {
        _mealLine.setRecipe(recipe);
        if(getUnit() == null)
        {
            setUnit(recipe.getUnit());
        }
    }
    
    

    /**
     * @return
     * @see at.easydiet.model.MealLine#getParent()
     */
    public MealLine getParent()
    {
        return _mealLine.getParent();
    }

    /**
     * @param parent
     * @see at.easydiet.model.MealLine#setParent(at.easydiet.model.MealLine)
     */
    public void setParent(MealLine parent)
    {
        _mealLine.setParent(parent);
    }

    /**
     * @return
     * @see at.easydiet.model.MealLine#getMeal()
     */
    public Meal getMeal()
    {
        return _mealLine.getMeal();
    }

    /**
     * @param meal
     * @see at.easydiet.model.MealLine#setMeal(at.easydiet.model.Meal)
     */
    public void setMeal(Meal meal)
    {
        _mealLine.setMeal(meal);
    }
    
    

    public void removeFromMeal()
    {
        if(_mealLine.getMeal() != null)
        {
            _mealLine.getMeal().getMealLines().remove(_mealLine);
        }
    }
    
    
}
