package at.easydiet.model;


/**
 * Represents a DietParameterTemplate
 */
public class DietParameterTemplate  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -143113779143097936L;
    private long _dietParameterTemplateId;
    private CheckOperator _checkOperator;
    private int _duration;
    private String _value;
    private ParameterDefinitionUnit _parameterDefinitionUnit;
    private DietParameterType _dietParameterType;
    private ParameterDefinition _parameterDefinition;
    private DietParameterSet _dietParameterSet;

    /**
     * Initializes a new instance of the {@link DietParameterTemplate} class.
     */
    public DietParameterTemplate() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link DietParameterTemplate} class.
     * @param checkOperator the checkOperator to set for this instance
     * @param parameterDefinitionUnit the parameterDefinitionUnit to set for this instance
     * @param dietParameterType the dietParameterType to set for this instance
     * @param parameterDefinition the parameterDefinition to set for this instance
     */
    public DietParameterTemplate(CheckOperator checkOperator, ParameterDefinitionUnit parameterDefinitionUnit, DietParameterType dietParameterType, ParameterDefinition parameterDefinition) 
    {
        _checkOperator = checkOperator;
        _parameterDefinitionUnit = parameterDefinitionUnit;
        _dietParameterType = dietParameterType;
        _parameterDefinition = parameterDefinition;
    }

    /**
     * Initializes a new instance of the {@link DietParameterTemplate} class.
     * @param checkOperator the checkOperator to set for this instance
     * @param duration the duration to set for this instance
     * @param value the value to set for this instance
     * @param parameterDefinitionUnit the parameterDefinitionUnit to set for this instance
     * @param dietParameterType the dietParameterType to set for this instance
     * @param parameterDefinition the parameterDefinition to set for this instance
     * @param dietParameterSet the dietParameterSet to set for this instance
     */
    public DietParameterTemplate(CheckOperator checkOperator, int duration, String value, ParameterDefinitionUnit parameterDefinitionUnit, DietParameterType dietParameterType, ParameterDefinition parameterDefinition, DietParameterSet dietParameterSet) 
    {
       _checkOperator = checkOperator;
       _duration = duration;
       _value = value;
       _parameterDefinitionUnit = parameterDefinitionUnit;
       _dietParameterType = dietParameterType;
       _parameterDefinition = parameterDefinition;
       _dietParameterSet = dietParameterSet;
    }
   
    /**       
     * Gets the dietParameterTemplateId of this instance. 
     * @return the dietParameterTemplateId currently set for this instance.
     */
    public long getDietParameterTemplateId() 
    {
        return _dietParameterTemplateId;
    }
    
    /**       
     * Sets the dietParameterTemplateId of this instance. 
     * @param dietParameterTemplateId the new dietParameterTemplateId of this instance.
     */    
    public void setDietParameterTemplateId(long dietParameterTemplateId) 
    {
        _dietParameterTemplateId = dietParameterTemplateId;
    }
    
    /**       
     * Gets the checkOperator of this instance. 
     * @return the checkOperator currently set for this instance.
     */
    public CheckOperator getCheckOperator() 
    {
        return _checkOperator;
    }
    
    /**       
     * Sets the checkOperator of this instance. 
     * @param checkOperator the new checkOperator of this instance.
     */    
    public void setCheckOperator(CheckOperator checkOperator) 
    {
        _checkOperator = checkOperator;
    }
    
    /**       
     * Gets the duration of this instance. 
     * @return the duration currently set for this instance.
     */
    public int getDuration() 
    {
        return _duration;
    }
    
    /**       
     * Sets the duration of this instance. 
     * @param duration the new duration of this instance.
     */    
    public void setDuration(int duration) 
    {
        _duration = duration;
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
     * Gets the parameterDefinitionUnit of this instance. 
     * @return the parameterDefinitionUnit currently set for this instance.
     */
    public ParameterDefinitionUnit getParameterDefinitionUnit() 
    {
        return _parameterDefinitionUnit;
    }
    
    /**       
     * Sets the parameterDefinitionUnit of this instance. 
     * @param parameterDefinitionUnit the new parameterDefinitionUnit of this instance.
     */    
    public void setParameterDefinitionUnit(ParameterDefinitionUnit parameterDefinitionUnit) 
    {
        _parameterDefinitionUnit = parameterDefinitionUnit;
    }
    
    /**       
     * Gets the dietParameterType of this instance. 
     * @return the dietParameterType currently set for this instance.
     */
    public DietParameterType getDietParameterType() 
    {
        return _dietParameterType;
    }
    
    /**       
     * Sets the dietParameterType of this instance. 
     * @param dietParameterType the new dietParameterType of this instance.
     */    
    public void setDietParameterType(DietParameterType dietParameterType) 
    {
        _dietParameterType = dietParameterType;
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
     * Gets the dietParameterSet of this instance. 
     * @return the dietParameterSet currently set for this instance.
     */
    public DietParameterSet getDietParameterSet() 
    {
        return _dietParameterSet;
    }
    
    /**       
     * Sets the dietParameterSet of this instance. 
     * @param dietParameterSet the new dietParameterSet of this instance.
     */    
    public void setDietParameterSet(DietParameterSet dietParameterSet) 
    {
        _dietParameterSet = dietParameterSet;
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
