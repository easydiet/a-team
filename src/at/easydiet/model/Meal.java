package at.easydiet.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a Meal
 */
public class Meal  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 6046825185580603544L;
    private long _mealId;
    private String _code;
    private String _name;
    private TimeSpan _timeSpan;
    private Set<DietParameter> _dietParameters = new HashSet<DietParameter>(0);
    private List<MealLine> _mealLines = new ArrayList<MealLine>(0);

    /**
     * Initializes a new instance of the {@link Meal} class.
     */
    public Meal() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link Meal} class.
     * @param code the code to set for this instance
     * @param name the name to set for this instance
     * @param timeSpan the timeSpan to set for this instance
     */
    public Meal(String code, String name, TimeSpan timeSpan) 
    {
        _code = code;
        _name = name;
        _timeSpan = timeSpan;
    }

    /**
     * Initializes a new instance of the {@link Meal} class.
     * @param code the code to set for this instance
     * @param name the name to set for this instance
     * @param timeSpan the timeSpan to set for this instance
     * @param dietParameters the dietParameters to set for this instance
     * @param mealLines the mealLines to set for this instance
     */
    public Meal(String code, String name, TimeSpan timeSpan, Set<DietParameter> dietParameters, List<MealLine> mealLines) 
    {
       _code = code;
       _name = name;
       _timeSpan = timeSpan;
       _dietParameters = dietParameters;
       _mealLines = mealLines;
    }
   
    /**       
     * Gets the mealId of this instance. 
     * @return the mealId currently set for this instance.
     */
    public long getMealId() 
    {
        return _mealId;
    }
    
    /**       
     * Sets the mealId of this instance. 
     * @param mealId the new mealId of this instance.
     */    
    public void setMealId(long mealId) 
    {
        _mealId = mealId;
    }
    
    /**       
     * Gets the code of this instance. 
     * @return the code currently set for this instance.
     */
    public String getCode() 
    {
        return _code;
    }
    
    /**       
     * Sets the code of this instance. 
     * @param code the new code of this instance.
     */    
    public void setCode(String code) 
    {
        _code = code;
    }
    
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _name;
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _name = name;
    }
    
    /**       
     * Gets the timeSpan of this instance. 
     * @return the timeSpan currently set for this instance.
     */
    public TimeSpan getTimeSpan() 
    {
        return _timeSpan;
    }
    
    /**       
     * Sets the timeSpan of this instance. 
     * @param timeSpan the new timeSpan of this instance.
     */    
    public void setTimeSpan(TimeSpan timeSpan) 
    {
        _timeSpan = timeSpan;
    }
    
    /**       
     * Gets the dietParameters of this instance. 
     * @return the dietParameters currently set for this instance.
     */
    public Set<DietParameter> getDietParameters() 
    {
        return _dietParameters;
    }
    
    /**       
     * Sets the dietParameters of this instance. 
     * @param dietParameters the new dietParameters of this instance.
     */    
    public void setDietParameters(Set<DietParameter> dietParameters) 
    {
        _dietParameters = dietParameters;
    }
    
    /**       
     * Gets the mealLines of this instance. 
     * @return the mealLines currently set for this instance.
     */
    public List<MealLine> getMealLines() 
    {
        return _mealLines;
    }
    
    /**       
     * Sets the mealLines of this instance. 
     * @param mealLines the new mealLines of this instance.
     */    
    public void setMealLines(List<MealLine> mealLines) 
    {
        _mealLines = mealLines;
    }
    
    /**
     * Returns a string representation of this instance.
     * @return a string
     */
    @Override
    public String toString() 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
        builder.append("]");
      
        return builder.toString();
    }
}
