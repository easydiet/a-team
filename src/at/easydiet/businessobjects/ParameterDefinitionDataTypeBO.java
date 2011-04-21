package at.easydiet.businessobjects;

import at.easydiet.model.ParameterDefinitionDataType;

public enum ParameterDefinitionDataTypeBO {
	NUMBERS ("Numbers"),
	ENUMERATION ("Enumeration"),
	STRING ("String");

	private ParameterDefinitionDataType _parameterDefinitionDataType;
	
	private ParameterDefinitionDataTypeBO(String name) {
		_parameterDefinitionDataType = new ParameterDefinitionDataType(name);
	}

    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _parameterDefinitionDataType.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _parameterDefinitionDataType.setName(name);
    }
    
    public static ParameterDefinitionDataTypeBO getForOperator(ParameterDefinitionDataType data)
	{
		for (ParameterDefinitionDataTypeBO current : values()) 
		{
			if(current.getName().equals(data.getName()))
			{
				return current;
			}
		}
		//default value
		return NUMBERS;
	}
}
