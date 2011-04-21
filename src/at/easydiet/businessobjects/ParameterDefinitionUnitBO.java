package at.easydiet.businessobjects;

import at.easydiet.model.ParameterDefinitionDataType;
import at.easydiet.model.ParameterDefinitionUnit;

public class ParameterDefinitionUnitBO {
	private ParameterDefinitionUnit _parameterDefinitionUnit;
	
	public ParameterDefinitionUnitBO(ParameterDefinitionUnit parameterDefinitionUnit) 
	{
		_parameterDefinitionUnit = parameterDefinitionUnit;
	}
	
	/**       
     * Gets the parameterDefinitionUnitId of this instance. 
     * @return the parameterDefinitionUnitId currently set for this instance.
     */
    public long getParameterDefinitionUnitId() 
    {
        return _parameterDefinitionUnit.getParameterDefinitionUnitId();
    }
    
    /**       
     * Sets the parameterDefinitionUnitId of this instance. 
     * @param parameterDefinitionUnitId the new parameterDefinitionUnitId of this instance.
     */    
    public void setParameterDefinitionUnitId(long parameterDefinitionUnitId) 
    {
        _parameterDefinitionUnit.setParameterDefinitionUnitId(parameterDefinitionUnitId);
    }
    
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _parameterDefinitionUnit.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _parameterDefinitionUnit.setName(name);
    }
    
    /**       
     * Gets the type of this instance. 
     * @return the type currently set for this instance.
     */
    public ParameterDefinitionDataTypeBO getTypeBO() 
    {
        return ParameterDefinitionDataTypeBO.getForOperator(_parameterDefinitionUnit.getType());
    }
    
    /**       
     * Sets the type of this instance. 
     * @param type the new type of this instance.
     */    
    public void setType(ParameterDefinitionDataType type) 
    {
        _parameterDefinitionUnit.setType(type);
    }
	

}
