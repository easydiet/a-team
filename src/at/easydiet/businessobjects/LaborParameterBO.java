package at.easydiet.businessobjects;

import at.easydiet.model.LaborParameter;

/**
 * This class encapsules a LaborParameter instance.
 */
public class LaborParameterBO 
{
	private LaborParameter _model;
	
    /**
     * Initializes a new instance of the {@link LaborParameterBO} class.
     */
	public LaborParameterBO()
	{
		// TODO: add default values
		this(new LaborParameter());
	}
	
    /**
     * Initializes a new instance of the {@link LaborParameterBO} class.
     * @param model the original model object
     */
	public LaborParameterBO(LaborParameter model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link LaborParameter} object.
	 */
 	public LaborParameter getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the laborParameterId of this instance. 
     * @return the laborParameterId currently set for this instance.
     */
    public long getLaborParameterId() 
    {
        return _model.getLaborParameterId();
    }
    
    /**       
     * Sets the laborParameterId of this instance. 
     * @param laborParameterId the new laborParameterId of this instance.
     */    
    public void setLaborParameterId(long laborParameterId) 
    {
        _model.setLaborParameterId(laborParameterId);
    }

    /**       
     * Gets the Value of this instance. 
     * @return the Value currently set for this instance.
     */
    public String getValue() 
    {
        return _model.getValue();
    }
    
    /**       
     * Sets the Value of this instance. 
     * @param Value the new Value of this instance.
     */    
    public void setValue(String Value) 
    {
        _model.setValue(Value);
    }

	
    private ParameterDefinitionUnitBO _parameterDefinitionUnit;
    
    /**
     * Gets the currently referenced ParameterDefinitionUnit of this instance.
     * @return the ParameterDefinitionUnit currently referenced in this LaborParameter. 
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
     * @param parameterDefinitionUnit the ParameterDefinitionUnit to reference in this LaborParameter. 
     */
    public void setParameterDefinitionUnit(ParameterDefinitionUnitBO parameterDefinitionUnit)
    {
        _parameterDefinitionUnit = parameterDefinitionUnit;
        _model.setParameterDefinitionUnit(parameterDefinitionUnit.getModel());
    }
	
    private CheckOperatorBO _checkOperator;
    
    /**
     * Gets the currently referenced CheckOperator of this instance.
     * @return the CheckOperator currently referenced in this LaborParameter. 
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
     * @param checkOperator the CheckOperator to reference in this LaborParameter. 
     */
    public void setCheckOperator(CheckOperatorBO checkOperator)
    {
        _checkOperator = checkOperator;
        _model.setCheckOperator(checkOperator.getModel());
    }
	
    private ParameterDefinitionBO _parameterDefinition;
    
    /**
     * Gets the currently referenced ParameterDefinition of this instance.
     * @return the ParameterDefinition currently referenced in this LaborParameter. 
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
     * @param parameterDefinition the ParameterDefinition to reference in this LaborParameter. 
     */
    public void setParameterDefinition(ParameterDefinitionBO parameterDefinition)
    {
        _parameterDefinition = parameterDefinition;
        _model.setParameterDefinition(parameterDefinition.getModel());
    }
	
    private LaborReportBO _laborReport;
    
    /**
     * Gets the currently referenced LaborReport of this instance.
     * @return the LaborReport currently referenced in this LaborParameter. 
     */
    public LaborReportBO getLaborReport()
    {
        if(_laborReport == null)
        {
            _laborReport = new LaborReportBO(_model.getLaborReport());
        }
        return _laborReport;
    }
    
    /**
     * Sets the LaborReport to be referenced in this instance
     * @param laborReport the LaborReport to reference in this LaborParameter. 
     */
    public void setLaborReport(LaborReportBO laborReport)
    {
        _laborReport = laborReport;
        _model.setLaborReport(laborReport.getModel());
    }
}