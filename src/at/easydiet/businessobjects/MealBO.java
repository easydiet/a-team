package at.easydiet.businessobjects;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.DietParameter;
import at.easydiet.model.Meal;
import at.easydiet.model.MealLine;

/**
 * This class encapsules a Meal instance.
 */
public class MealBO implements IDietParameterizable
{
	private Meal _model;
	
    /**
     * Initializes a new instance of the {@link MealBO} class.
     */
	public MealBO()
	{
		this(new Meal("", "", null));
	}
	
    /**
     * Initializes a new instance of the {@link MealBO} class.
     * @param model the original model object
     */
	public MealBO(Meal model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link Meal} object.
	 */
 	public Meal getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the mealId of this instance. 
     * @return the mealId currently set for this instance.
     */
    public long getMealId() 
    {
        return _model.getMealId();
    }
    
    /**       
     * Sets the mealId of this instance. 
     * @param mealId the new mealId of this instance.
     */    
    public void setMealId(long mealId) 
    {
        _model.setMealId(mealId);
    }

    /**       
     * Gets the code of this instance. 
     * @return the code currently set for this instance.
     */
    public String getCode() 
    {
        return _model.getCode();
    }
    
    /**       
     * Sets the code of this instance. 
     * @param code the new code of this instance.
     */    
    public void setCode(String code) 
    {
        _model.setCode(code);
    }

    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _model.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _model.setName(name);
    }

	
    private TimeSpanBO _timeSpan;
    
    /**
     * Gets the currently referenced TimeSpan of this instance.
     * @return the TimeSpan currently referenced in this Meal. 
     */
    public TimeSpanBO getTimeSpan()
    {
        if(_timeSpan == null)
        {
            _timeSpan = new TimeSpanBO(_model.getTimeSpan());
        }
        return _timeSpan;
    }
    
    /**
     * Sets the TimeSpan to be referenced in this instance
     * @param timeSpan the TimeSpan to reference in this Meal. 
     */
    public void setTimeSpan(TimeSpanBO timeSpan)
    {
        _timeSpan = timeSpan;
        _model.setTimeSpan(timeSpan.getModel());
    }

	private List<DietParameterBO> _dietParameters;
	
    /**
     * Gets a list of referenced DietParameters of this instance.
     * This list is cached, use {@link Meal#updateDietParametersCache()) to update this cache.
     * @return a cached list of referenced DietParameters wrapped into the correct businessobject. 
     */
    public List<DietParameterBO> getDietParameters()
    {
        if(_dietParameters == null) 
        {
            _dietParameters = new ArrayList<DietParameterBO>();
            for(DietParameter dietParameters : _model.getDietParameters())
            {
                _dietParameters.add(new DietParameterBO(dietParameters));
            }
        }
        return _dietParameters;
    }
	
    /**
     * Adds a new DietParameter to the list of referenced dietParameters.
     * The cache will updated
     * @param dietParameters the DietParameter to add. 
     */
    public void addDietParameters(DietParameterBO dietParameters)
    {
        getDietParameters().add(dietParameters);
        _model.getDietParameters().add(dietParameters.getModel());
    }
    
        
    /**
     * Removes the given DietParameter from the list of referenced dietParameters.
     * The cache will updated
     * @param dietParameters the timespan to add. 
     */
    public void removeDietParameters(DietParameterBO dietParameters)
    {
        getDietParameters().remove(dietParameters);
        _model.getDietParameters().remove(dietParameters.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced dietParameters.
     */
    public void updateDietParametersCache()
    {
        _dietParameters = null;
        getDietParameters();
    }


	private List<MealLineBO> _mealLines;
	
    /**
     * Gets a list of referenced MealLines of this instance.
     * This list is cached, use {@link Meal#updateMealLinesCache()) to update this cache.
     * @return a cached list of referenced MealLines wrapped into the correct businessobject. 
     */
    public List<MealLineBO> getMealLines()
    {
        if(_mealLines == null) 
        {
            _mealLines = new ArrayList<MealLineBO>();
            for(MealLine mealLines : _model.getMealLines())
            {
                _mealLines.add(new MealLineBO(mealLines));
            }
        }
        return _mealLines;
    }
	
    /**
     * Adds a new MealLine to the list of referenced mealLines.
     * The cache will updated
     * @param mealLines the MealLine to add. 
     */
    public void addMealLines(MealLineBO mealLines)
    {
        getMealLines().add(mealLines);
        _model.getMealLines().add(mealLines.getModel());
    }
    

    /**
     * Adds a new MealLine to the list of referenced mealLines at the specified index.
     * The cache will updated
     * @param mealLines the MealLine to add. 
     */
    public void addMealLines(int index, MealLineBO alternative)
    {
        getMealLines().insert(alternative, index);
        _model.getMealLines().add(index, alternative.getModel());
    }
    
        
    /**
     * Removes the given MealLine from the list of referenced mealLines.
     * The cache will updated
     * @param mealLines the timespan to add. 
     */
    public void removeMealLines(MealLineBO mealLines)
    {
        getMealLines().remove(mealLines);
        _model.getMealLines().remove(mealLines.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced mealLines.
     */
    public void updateMealLinesCache()
    {
        _mealLines = null;
        getMealLines();
    }

    public String getDisplayText()
    {
        return String.format("%s - %s", getCode(), getName());
    }
}