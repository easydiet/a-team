package at.easydiet.model;


/**
 * Represents a LaborParameter
 */
public class LaborParameter  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -4426074917114847316L;
    private long _laborParameterId;
    private String _Value;
    private ParameterDefinitionUnit _parameterDefinitionUnit;
    private CheckOperator _checkOperator;
    private ParameterDefinition _parameterDefinition;
    private LaborReport _laborReport;

    /**
     * Initializes a new instance of the {@link LaborParameter} class.
     */
    public LaborParameter() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link LaborParameter} class.
     * @param Value the Value to set for this instance
     * @param parameterDefinitionUnit the parameterDefinitionUnit to set for this instance
     * @param checkOperator the checkOperator to set for this instance
     * @param parameterDefinition the parameterDefinition to set for this instance
     */
    public LaborParameter(String Value, ParameterDefinitionUnit parameterDefinitionUnit, CheckOperator checkOperator, ParameterDefinition parameterDefinition) 
    {
        _Value = Value;
        _parameterDefinitionUnit = parameterDefinitionUnit;
        _checkOperator = checkOperator;
        _parameterDefinition = parameterDefinition;
    }

    /**
     * Initializes a new instance of the {@link LaborParameter} class.
     * @param Value the Value to set for this instance
     * @param parameterDefinitionUnit the parameterDefinitionUnit to set for this instance
     * @param checkOperator the checkOperator to set for this instance
     * @param parameterDefinition the parameterDefinition to set for this instance
     * @param laborReport the laborReport to set for this instance
     */
    public LaborParameter(String Value, ParameterDefinitionUnit parameterDefinitionUnit, CheckOperator checkOperator, ParameterDefinition parameterDefinition, LaborReport laborReport) 
    {
       _Value = Value;
       _parameterDefinitionUnit = parameterDefinitionUnit;
       _checkOperator = checkOperator;
       _parameterDefinition = parameterDefinition;
       _laborReport = laborReport;
    }
   
    /**       
     * Gets the laborParameterId of this instance. 
     * @return the laborParameterId currently set for this instance.
     */
    public long getLaborParameterId() 
    {
        return _laborParameterId;
    }
    
    /**       
     * Sets the laborParameterId of this instance. 
     * @param laborParameterId the new laborParameterId of this instance.
     */    
    public void setLaborParameterId(long laborParameterId) 
    {
        _laborParameterId = laborParameterId;
    }
    
    /**       
     * Gets the Value of this instance. 
     * @return the Value currently set for this instance.
     */
    public String getValue() 
    {
        return _Value;
    }
    
    /**       
     * Sets the Value of this instance. 
     * @param Value the new Value of this instance.
     */    
    public void setValue(String Value) 
    {
        _Value = Value;
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
     * Gets the laborReport of this instance. 
     * @return the laborReport currently set for this instance.
     */
    public LaborReport getLaborReport() 
    {
        return _laborReport;
    }
    
    /**       
     * Sets the laborReport of this instance. 
     * @param laborReport the new laborReport of this instance.
     */    
    public void setLaborReport(LaborReport laborReport) 
    {
        _laborReport = laborReport;
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
