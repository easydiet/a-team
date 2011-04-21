package at.easydiet.businessobjects;

import at.easydiet.model.CheckOperator;
import at.easydiet.model.DietParameterTemplate;
import at.easydiet.model.DietParameterType;
import at.easydiet.model.ParameterDefinition;

public class DietParameterTemplateBO {
	
	private DietParameterTemplate _dietParameterTemplate;
	
	public DietParameterTemplateBO(DietParameterTemplate dietParameterTemplate) {
		_dietParameterTemplate = dietParameterTemplate;
	}
	
	/**       
     * Gets the dietParameterTemplateId of this instance. 
     * @return the dietParameterTemplateId currently set for this instance.
     */
    public long getDietParameterTemplateId() 
    {
        return _dietParameterTemplate.getDietParameterTemplateId();
    }
    
    /**       
     * Sets the dietParameterTemplateId of this instance. 
     * @param dietParameterTemplateId the new dietParameterTemplateId of this instance.
     */    
    public void setDietParameterTemplateId(long dietParameterTemplateId) 
    {
    	_dietParameterTemplate.setDietParameterTemplateId(dietParameterTemplateId);
    }
    
    /**       
     * Gets the checkOperator of this instance. 
     * @return the checkOperator currently set for this instance.
     */
    public CheckOperator getCheckOperator() 
    {
        return _dietParameterTemplate.getCheckOperator();
    }
    
    /**       
     * Sets the checkOperator of this instance. 
     * @param checkOperator the new checkOperator of this instance.
     */    
    public void setCheckOperator(CheckOperator checkOperator) 
    {
    	_dietParameterTemplate.setCheckOperator(checkOperator);
    }
    
    /**       
     * Gets the duration of this instance. 
     * @return the duration currently set for this instance.
     */
    public int getDuration() 
    {
    	return _dietParameterTemplate.getDuration();
    }
    
    /**       
     * Sets the duration of this instance. 
     * @param duration the new duration of this instance.
     */    
    public void setDuration(int duration) 
    {
    	_dietParameterTemplate.setDuration(duration);
    }
    
    /**       
     * Gets the value of this instance. 
     * @return the value currently set for this instance.
     */
    public String getValue() 
    {
    	return _dietParameterTemplate.getValue();
    }
    
    /**       
     * Sets the value of this instance. 
     * @param value the new value of this instance.
     */    
    public void setValue(String value) 
    {
    	_dietParameterTemplate.setValue(value);
    }
    
    /**       
     * Gets the dietParameterType of this instance. 
     * @return the dietParameterType currently set for this instance.
     */
    public DietParameterType getDietParameterType() 
    {
    	return _dietParameterTemplate.getDietParameterType();
    }
    
    /**       
     * Sets the dietParameterType of this instance. 
     * @param dietParameterType the new dietParameterType of this instance.a
     */    
    public void setDietParameterType(DietParameterType dietParameterType) 
    {
    	_dietParameterTemplate.setDietParameterType(dietParameterType);
    }
    
    /**       
     * Gets the parameterDefinition of this instance. 
     * @return the parameterDefinition currently set for this instance.
     */
    public ParameterDefinition getParameterDefinition() 
    {
        return _dietParameterTemplate.getParameterDefinition();
    }
    
    /**       
     * Sets the parameterDefinition of this instance. 
     * @param parameterDefinition the new parameterDefinition of this instance.
     */    
    public void setParameterDefinition(ParameterDefinition parameterDefinition) 
    {
    	_dietParameterTemplate.setParameterDefinition(parameterDefinition);
    }	
    

	public ParameterDefinitionUnitBO getUnitBO() {
		return new ParameterDefinitionUnitBO(_dietParameterTemplate.getParameterDefinitionUnit());
	}
}
