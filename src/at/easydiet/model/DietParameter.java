package at.easydiet.model;

import java.util.Date;

/**
 * Represents a DietParameter
 */
public class DietParameter extends at.easydiet.model.DietParameterTemplate implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -5415629633663504397L;
    private Date _start;

    /**
     * Initializes a new instance of the {@link DietParameter} class.
     */
    public DietParameter() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link DietParameter} class.
     */
    public DietParameter(CheckOperator checkOperator, ParameterDefinitionDataType parameterDefinitionDataType, DietParameterType dietParameterType, ParameterDefinition parameterDefinition) 
    {
        super(checkOperator, parameterDefinitionDataType, dietParameterType, parameterDefinition);        
    }

    /**
     * Initializes a new instance of the {@link DietParameter} class.
     * @param start the start to set for this instance
     */
    public DietParameter(CheckOperator checkOperator, int duration, String value, ParameterDefinitionDataType parameterDefinitionDataType, DietParameterType dietParameterType, ParameterDefinition parameterDefinition, Date start) 
    {
        super(checkOperator, duration, value, parameterDefinitionDataType, dietParameterType, parameterDefinition);        
       _start = start;
    }
   
    /**       
     * Gets the start of this instance. 
     * @return the start currently set for this instance.
     */
    public Date getStart() 
    {
        return _start;
    }
    
    /**       
     * Sets the start of this instance. 
     * @param start the new start of this instance.
     */    
    public void setStart(Date start) 
    {
        _start = start;
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
