package at.easydiet.businessobjects;

import at.easydiet.model.DietParameterTemplate;

/**
 * This class encapsules a DietParameterTemplate instance.
 */
public class DietParameterTemplateBO 
{
	private DietParameterTemplate _model;
	
    /**
     * Initializes a new instance of the {@link DietParameterTemplateBO} class.
     */
	public DietParameterTemplateBO()
	{
		// TODO: add default values
		this(new DietParameterTemplate());
	}
	
    /**
     * Initializes a new instance of the {@link DietParameterTemplateBO} class.
     * @param model the original model object
     */
	public DietParameterTemplateBO(DietParameterTemplate model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link DietParameterTemplate} object.
	 */
 	public DietParameterTemplate getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the dietParameterTemplateId of this instance. 
     * @return the dietParameterTemplateId currently set for this instance.
     */
    public long getDietParameterTemplateId() 
    {
        return _model.getDietParameterTemplateId();
    }
    
    /**       
     * Sets the dietParameterTemplateId of this instance. 
     * @param dietParameterTemplateId the new dietParameterTemplateId of this instance.
     */    
    public void setDietParameterTemplateId(long dietParameterTemplateId) 
    {
        _model.setDietParameterTemplateId(dietParameterTemplateId);
    }

	
    private CheckOperatorBO _checkOperator;
    
    /**
     * Gets the currently referenced CheckOperator of this instance.
     * @return the CheckOperator currently referenced in this DietParameterTemplate. 
     */
    public CheckOperatorBO getCheckOperator()
    {
        if(_checkOperator == null)
        {
            _checkOperator = CheckOperatorBO.getForModel(_model.getCheckOperator());
        }
        return _checkOperator;
    }
    
    /**
     * Sets the CheckOperator to be referenced in this instance
     * @param checkOperator the CheckOperator to reference in this DietParameterTemplate. 
     */
    public void setCheckOperator(CheckOperatorBO checkOperator)
    {
        _checkOperator = checkOperator;
        _model.setCheckOperator(checkOperator.getModel());
    }
    /**       
     * Gets the duration of this instance. 
     * @return the duration currently set for this instance.
     */
    public int getDuration() 
    {
        return _model.getDuration();
    }
    
    /**       
     * Sets the duration of this instance. 
     * @param duration the new duration of this instance.
     */    
    public void setDuration(int duration) 
    {
        _model.setDuration(duration);
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

	
    private ParameterDefinitionUnitBO _parameterDefinitionUnit;
    
    /**
     * Gets the currently referenced ParameterDefinitionUnit of this instance.
     * @return the ParameterDefinitionUnit currently referenced in this DietParameterTemplate. 
     */
    public ParameterDefinitionUnitBO getParameterDefinitionUnit()
    {
        if(_parameterDefinitionUnit == null)
        {
            _parameterDefinitionUnit = new ParameterDefinitionUnitBO(_model.getParameterDefinitionUnit());
        }
        return _parameterDefinitionUnit;
    }
    
    /**
     * Sets the ParameterDefinitionUnit to be referenced in this instance
     * @param parameterDefinitionUnit the ParameterDefinitionUnit to reference in this DietParameterTemplate. 
     */
    public void setParameterDefinitionUnit(ParameterDefinitionUnitBO parameterDefinitionUnit)
    {
        _parameterDefinitionUnit = parameterDefinitionUnit;
        _model.setParameterDefinitionUnit(parameterDefinitionUnit.getModel());
    }
	
    private DietParameterTypeBO _dietParameterType;
    
    /**
     * Gets the currently referenced DietParameterType of this instance.
     * @return the DietParameterType currently referenced in this DietParameterTemplate. 
     */
    public DietParameterTypeBO getDietParameterType()
    {
        if(_dietParameterType == null)
        {
            _dietParameterType = DietParameterTypeBO.getForModel(_model.getDietParameterType());
        }
        return _dietParameterType;
    }
    
    /**
     * Sets the DietParameterType to be referenced in this instance
     * @param dietParameterType the DietParameterType to reference in this DietParameterTemplate. 
     */
    public void setDietParameterType(DietParameterTypeBO dietParameterType)
    {
        _dietParameterType = dietParameterType;
        _model.setDietParameterType(dietParameterType.getModel());
    }
	
    private ParameterDefinitionBO _parameterDefinition;
    
    /**
     * Gets the currently referenced ParameterDefinition of this instance.
     * @return the ParameterDefinition currently referenced in this DietParameterTemplate. 
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
     * @param parameterDefinition the ParameterDefinition to reference in this DietParameterTemplate. 
     */
    public void setParameterDefinition(ParameterDefinitionBO parameterDefinition)
    {
        _parameterDefinition = parameterDefinition;
        _model.setParameterDefinition(parameterDefinition.getModel());
    }
	
    private DietParameterSetBO _dietParameterSet;
    
    /**
     * Gets the currently referenced DietParameterSet of this instance.
     * @return the DietParameterSet currently referenced in this DietParameterTemplate. 
     */
    public DietParameterSetBO getDietParameterSet()
    {
        if(_dietParameterSet == null)
        {
            _dietParameterSet = new DietParameterSetBO(_model.getDietParameterSet());
        }
        return _dietParameterSet;
    }
    
    /**
     * Sets the DietParameterSet to be referenced in this instance
     * @param dietParameterSet the DietParameterSet to reference in this DietParameterTemplate. 
     */
    public void setDietParameterSet(DietParameterSetBO dietParameterSet)
    {
        _dietParameterSet = dietParameterSet;
        _model.setDietParameterSet(dietParameterSet.getModel());
    }
    
    public float getFloatValue()
    {
        // TODO: Mention datatype here!
        try
        {
            return Float.valueOf(getValue());
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}