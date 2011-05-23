package at.easydiet.businessobjects;

import at.easydiet.model.NutrimentParameter;

/**
 * This class encapsules a NutrimentParameter instance.
 */
public class NutrimentParameterBO 
{
	private NutrimentParameter _model;
	
    /**
     * Initializes a new instance of the {@link NutrimentParameterBO} class.
     */
	public NutrimentParameterBO()
	{
		this(new NutrimentParameter("0", null, null));
	}
	
    /**
     * Initializes a new instance of the {@link NutrimentParameterBO} class.
     * @param model the original model object
     */
	public NutrimentParameterBO(NutrimentParameter model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link NutrimentParameter} object.
	 */
 	public NutrimentParameter getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the nutrimentParameterId of this instance. 
     * @return the nutrimentParameterId currently set for this instance.
     */
    public long getNutrimentParameterId() 
    {
        return _model.getNutrimentParameterId();
    }
    
    /**       
     * Sets the nutrimentParameterId of this instance. 
     * @param nutrimentParameterId the new nutrimentParameterId of this instance.
     */    
    public void setNutrimentParameterId(long nutrimentParameterId) 
    {
        _model.setNutrimentParameterId(nutrimentParameterId);
    }

    /**       
     * Gets the value of this instance. 
     * @return the value currently set for this instance.
     */
    public String getValue() 
    {
        return _model.getValue();
    }
    
    /**       
     * Sets the value of this instance. 
     * @param value the new value of this instance.
     */    
    public void setValue(String value) 
    {
        _model.setValue(value);
    }

	
    private ParameterDefinitionBO _parameterDefinition;
    
    /**
     * Gets the currently referenced ParameterDefinition of this instance.
     * @return the ParameterDefinition currently referenced in this NutrimentParameter. 
     */
    public ParameterDefinitionBO getParameterDefinition()
    {
        if(_parameterDefinition == null)
        {
            _parameterDefinition = new ParameterDefinitionBO(_model.getParameterDefinition());
        }
        return _parameterDefinition;
    }
    
    /**
     * Sets the ParameterDefinition to be referenced in this instance
     * @param parameterDefinition the ParameterDefinition to reference in this NutrimentParameter. 
     */
    public void setParameterDefinition(ParameterDefinitionBO parameterDefinition)
    {
        _parameterDefinition = parameterDefinition;
        _model.setParameterDefinition(parameterDefinition.getModel());
    }
	
    private ParameterDefinitionUnitBO _unit;
    
    /**
     * Gets the currently referenced Unit of this instance.
     * @return the ParameterDefinitionUnit currently referenced in this NutrimentParameter. 
     */
    public ParameterDefinitionUnitBO getUnit()
    {
        if(_unit == null)
        {
            _unit = new ParameterDefinitionUnitBO(_model.getUnit());
        }
        return _unit;
    }
    
    /**
     * Sets the Unit to be referenced in this instance
     * @param unit the ParameterDefinitionUnit to reference in this NutrimentParameter. 
     */
    public void setUnit(ParameterDefinitionUnitBO unit)
    {
        _unit = unit;
        _model.setUnit(unit.getModel());
    }
    
    public float getFloatValue()
    {
        // TODO: do some caching
        return Float.parseFloat(getValue());
    }
    
    public void setValue(float value)
    {
        setValue(new Float(value).toString());
    }
}