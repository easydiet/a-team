package at.easydiet.businessobjects;

import java.sql.Clob;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.MealLine;

/**
 * This class encapsules a MealLine instance.
 */
public class MealLineBO
{
    private MealLine _model;

    /**
     * Initializes a new instance of the {@link MealLineBO} class.
     */
    public MealLineBO()
    {
        this(new MealLine(0, null, null, null, null));
    }

    /**
     * Initializes a new instance of the {@link MealLineBO} class.
     * @param model the original model object
     */
    public MealLineBO(MealLine model)
    {
        _model = model;
    }

    /**
     * Gets the original model object used as object store for this
     * BusinessObject.
     * @return the original {@link MealLine} object.
     */
    public MealLine getModel()
    {
        return _model;
    }

    /**
     * Gets the mealLineId of this instance.
     * @return the mealLineId currently set for this instance.
     */
    public long getMealLineId()
    {
        return _model.getMealLineId();
    }

    /**
     * Sets the mealLineId of this instance.
     * @param mealLineId the new mealLineId of this instance.
     */
    public void setMealLineId(long mealLineId)
    {
        _model.setMealLineId(mealLineId);
    }

    /**
     * Gets the quantity of this instance.
     * @return the quantity currently set for this instance.
     */
    public float getQuantity()
    {
        return _model.getQuantity();
    }

    /**
     * Sets the quantity of this instance.
     * @param quantity the new quantity of this instance.
     */
    public void setQuantity(float quantity)
    {
        _model.setQuantity(quantity);
    }

    /**
     * Gets the info of this instance.
     * @return the info currently set for this instance.
     */
    public Clob getInfo()
    {
        return _model.getInfo();
    }

    /**
     * Sets the info of this instance.
     * @param info the new info of this instance.
     */
    public void setInfo(Clob info)
    {
        _model.setInfo(info);
    }

    private List<MealLineBO> _alternatives;

/**
     * Gets a list of referenced Alternatives of this instance.
     * This list is cached, use {@link MealLine#updateAlternativesCache()) to update this cache.
     * @return a cached list of referenced Alternatives wrapped into the correct businessobject. 
     */
    public List<MealLineBO> getAlternatives()
    {
        if (_alternatives == null)
        {
            _alternatives = new ArrayList<MealLineBO>();
            for (MealLine alternatives : _model.getAlternatives())
            {
                _alternatives.add(new MealLineBO(alternatives));
            }
        }
        return _alternatives;
    }

    /**
     * Adds a new MealLine to the list of referenced alternatives. The cache
     * will updated
     * @param alternatives the MealLine to add.
     */
    public void addAlternatives(MealLineBO alternatives)
    {
        alternatives.setParent(this);
        getAlternatives().add(alternatives);
        _model.getAlternatives().add(alternatives.getModel());
    }

    /**
     * Removes the given MealLine from the list of referenced alternatives. The
     * cache will updated
     * @param alternatives the timespan to add.
     */
    public void removeAlternatives(MealLineBO alternatives)
    {
        getAlternatives().remove(alternatives);
        _model.getAlternatives().remove(alternatives.getModel());
    }

    /**
     * Rebuilds the cache for referenced alternatives.
     */
    public void updateAlternativesCache()
    {
        _alternatives = null;
        getAlternatives();
    }

    private RecipeBO _recipe;

    /**
     * Gets the currently referenced Recipe of this instance.
     * @return the Recipe currently referenced in this MealLine.
     */
    public RecipeBO getRecipe()
    {
        if (_recipe == null)
        {
            _recipe = new RecipeBO(_model.getRecipe());
        }
        return _recipe;
    }

    /**
     * Sets the Recipe to be referenced in this instance
     * @param recipe the Recipe to reference in this MealLine.
     */
    public void setRecipe(RecipeBO recipe)
    {
        _recipe = recipe;
        _model.setRecipe(recipe.getModel());
        if(_model.getUnit() == null)
        {
            setUnit(recipe.getUnit());
        }
    }

    private ParameterDefinitionUnitBO _unit;

    /**
     * Gets the currently referenced Unit of this instance.
     * @return the ParameterDefinitionUnit currently referenced in this
     *         MealLine.
     */
    public ParameterDefinitionUnitBO getUnit()
    {
        if (_unit == null)
        {
            _unit = new ParameterDefinitionUnitBO(_model.getUnit());
        }
        return _unit;
    }

    /**
     * Sets the Unit to be referenced in this instance
     * @param unit the ParameterDefinitionUnit to reference in this MealLine.
     */
    public void setUnit(ParameterDefinitionUnitBO unit)
    {
        _unit = unit;
        _model.setUnit(unit.getModel());
    }

    private MealLineBO _parent;

    /**
     * Gets the currently referenced Parent of this instance.
     * @return the MealLine currently referenced in this MealLine.
     */
    public MealLineBO getParent()
    {
        if (_parent == null)
        {
            _parent = new MealLineBO(_model.getParent());
        }
        return _parent;
    }

    /**
     * Sets the Parent to be referenced in this instance
     * @param parent the MealLine to reference in this MealLine.
     */
    public void setParent(MealLineBO parent)
    {
        _parent = parent;
        _model.setParent(parent.getModel());
    }

    private MealBO _meal;

    /**
     * Gets the currently referenced Meal of this instance.
     * @return the Meal currently referenced in this MealLine.
     */
    public MealBO getMeal()
    {
        if (_meal == null)
        {
            _meal = new MealBO(_model.getMeal());
        }
        return _meal;
    }

    /**
     * Sets the Meal to be referenced in this instance
     * @param meal the Meal to reference in this MealLine.
     */
    public void setMeal(MealBO meal)
    {
        _meal = meal;
        _model.setMeal(meal.getModel());
    }

    /**
     * Gets a value indicating whether this meal line is an alternative to an
     * other mealline
     * @return
     */
    public boolean isAlternative()
    {
        return _model.getParent() != null;
    }
}