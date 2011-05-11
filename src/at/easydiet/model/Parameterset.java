package at.easydiet.model;


/**
 * Represents a NutrimentParameter
 */
public class Parameterset  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 2579863774576179705L;
    private long _nutrimentParameterId;
    private String _value;
    private ParameterDefinition _parameterDefinition;
    private ParameterDefinitionUnit _unit;

    /**
     * Initializes a new instance of the {@link Parameterset} class.
     */
    public Parameterset() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link Parameterset} class.
     * @param value the value to set for this instance
     * @param parameterDefinition the parameterDefinition to set for this instance
     * @param unit the unit to set for this instance
     */
    public Parameterset(String value, ParameterDefinition parameterDefinition, ParameterDefinitionUnit unit) 
    {
       _value = value;
       _parameterDefinition = parameterDefinition;
       _unit = unit;
    }
   
    /**       
     * Gets the nutrimentParameterId of this instance. 
     * @return the nutrimentParameterId currently set for this instance.
     */
    public long getNutrimentParameterId() 
    {
        return _nutrimentParameterId;
    }
    
    /**       
     * Sets the nutrimentParameterId of this instance. 
     * @param nutrimentParameterId the new nutrimentParameterId of this instance.
     */    
    public void setNutrimentParameterId(long nutrimentParameterId) 
    {
        _nutrimentParameterId = nutrimentParameterId;
    }
    
    /**       
     * Gets the value of this instance. 
     * @return the value currently set for this instance.
     */
    public String getValue() 
    {
        return _value;
    }
    
    /**       
     * Sets the value of this instance. 
     * @param value the new value of this instance.
     */    
    public void setValue(String value) 
    {
        _value = value;
    }
    
    /**       
     * Gets the parameterDefinition of this instance. 
     * @return the parameterDefinition currently set for this instance.
     */
    public ParameterDefinition getParameterDefinition() 
    {
        return _parameterDefinition;
    }
    
    /**       
     * Sets the parameterDefinition of this instance. 
     * @param parameterDefinition the new parameterDefinition of this instance.
     */    
    public void setParameterDefinition(ParameterDefinition parameterDefinition) 
    {
        _parameterDefinition = parameterDefinition;
    }
    
    /**       
     * Gets the unit of this instance. 
     * @return the unit currently set for this instance.
     */
    public ParameterDefinitionUnit getUnit() 
    {
        return _unit;
    }
    
    /**       
     * Sets the unit of this instance. 
     * @param unit the new unit of this instance.
     */    
    public void setUnit(ParameterDefinitionUnit unit) 
    {
        _unit = unit;
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


	public Object getDietParameters() {
		return null;
	}
}
