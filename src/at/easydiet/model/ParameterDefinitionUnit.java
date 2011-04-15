package at.easydiet.model;


/**
 * Represents a ParameterDefinitionUnit
 */
public class ParameterDefinitionUnit  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -5166550150466202722L;
    private long _parameterDefinitionUnitId;
    private String _name;
    private ParameterDefinitionDataType _type;

    /**
     * Initializes a new instance of the {@link ParameterDefinitionUnit} class.
     */
    public ParameterDefinitionUnit() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link ParameterDefinitionUnit} class.
     * @param name the name to set for this instance
     * @param type the type to set for this instance
     */
    public ParameterDefinitionUnit(String name, ParameterDefinitionDataType type) 
    {
       _name = name;
       _type = type;
    }
   
    /**       
     * Gets the parameterDefinitionUnitId of this instance. 
     * @return the parameterDefinitionUnitId currently set for this instance.
     */
    public long getParameterDefinitionUnitId() 
    {
        return _parameterDefinitionUnitId;
    }
    
    /**       
     * Sets the parameterDefinitionUnitId of this instance. 
     * @param parameterDefinitionUnitId the new parameterDefinitionUnitId of this instance.
     */    
    public void setParameterDefinitionUnitId(long parameterDefinitionUnitId) 
    {
        _parameterDefinitionUnitId = parameterDefinitionUnitId;
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
     * Gets the type of this instance. 
     * @return the type currently set for this instance.
     */
    public ParameterDefinitionDataType getType() 
    {
        return _type;
    }
    
    /**       
     * Sets the type of this instance. 
     * @param type the new type of this instance.
     */    
    public void setType(ParameterDefinitionDataType type) 
    {
        _type = type;
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
