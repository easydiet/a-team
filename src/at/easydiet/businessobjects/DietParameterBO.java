package at.easydiet.businessobjects;

import java.util.Date;

import at.easydiet.dao.DAOFactory;
import at.easydiet.model.DietParameter;

public class DietParameterBO extends DietParameterTemplateBO
{
	private DietParameter _dietParameter;
	
	public DietParameterBO(DietParameter dietParameter) {
		super(dietParameter);
		_dietParameter = dietParameter;
	}
	
	
	public String getParameterValue()
	{
		return _dietParameter.getValue();
	}
	
	public void setParameterValue(String value)
	{
		_dietParameter.setValue(value);
	}
	
    /**       
     * Gets the start of this instance. 
     * @return the start currently set for this instance.
     */
    public Date getStart() 
    {
        return _dietParameter.getStart();
    }
    
    /**       
     * Sets the start of this instance. 
     * @param start the new start of this instance.
     */    
    public void setStart(Date start) 
    {
        _dietParameter.setStart(start);
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
        builder.append("DietParameterTemplateID").append("='").append(_dietParameter.getDietParameterTemplateId()).append("' ");		
        builder.append("Duration").append("='").append(_dietParameter.getDuration()).append("' ");		
        builder.append("Value").append("='").append(_dietParameter.getValue()).append("' ");
        builder.append("CheckOperator").append("='").append(_dietParameter.getCheckOperator()).append("' ");
        builder.append("DietParameterType").append("='").append(_dietParameter.getDietParameterType()).append("' ");	
        builder.append("ParameterDefinition").append("='").append(_dietParameter.getParameterDefinition()).append("' ");		
        builder.append("ParameterDefinitionUnit").append("='").append(_dietParameter.getParameterDefinitionUnit()).append("' ");
        builder.append("Start").append("='").append(_dietParameter.getStart()).append("' ");		
        builder.append("]");
      
        return builder.toString();
    }

	public DietParameterTypeBO getDietParameterTypeBO() {
		return new DietParameterTypeBO(_dietParameter.getDietParameterType());
	}

	public ParameterDefinitionBO getParameterDefinitionBO() {
		return new ParameterDefinitionBO(_dietParameter.getParameterDefinition());
	}
	
	public void setParameterDefinitionBO(ParameterDefinitionBO parameterDefinitionBO) {
		_dietParameter.setParameterDefinition(DAOFactory.getInstance().getParameterDefinitionDAO().findById(parameterDefinitionBO.getParameterDefinitionId(), false));
	}
	
	public CheckOperatorBO getCheckOperatorBO() {
		return CheckOperatorBO.getForOperator(_dietParameter.getCheckOperator());
	}
	
	public void setCheckOperatorBO(CheckOperatorBO checkOperatorBO)
	{
		_dietParameter.setCheckOperator(checkOperatorBO.getCheckOperator());
	}

	public ParameterDefinitionUnitBO getSelectedUnitBO()
	{
		return new ParameterDefinitionUnitBO(_dietParameter.getParameterDefinitionUnit());
	}
	
	public void setSelectedUnitBO(ParameterDefinitionUnitBO parameterDefinitionUnitBO)
	{
		//TODO: check
		_dietParameter.setParameterDefinitionUnit(parameterDefinitionUnitBO.getParameterDefinitionUnit());
	}
}