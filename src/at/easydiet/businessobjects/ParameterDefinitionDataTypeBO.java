package at.easydiet.businessobjects;

import at.easydiet.model.ParameterDefinitionDataType;

/**
 * This class encapsules a ParameterDefinitionDataType instance.
 */
public enum ParameterDefinitionDataTypeBO
{
    NUMBERS ("Numbers"),
    ENUMERATION ("Enumeration"),
    STRING ("String");

	private ParameterDefinitionDataType _model;

    /**
     * Initializes a new instance of the {@link ParameterDefinitionDataTypeBO} enum.
     * @param model the enum value
     */
	private ParameterDefinitionDataTypeBO(String value)
	{
		_model = new ParameterDefinitionDataType(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link ParameterDefinitionDataType} object.
	 */
 	public ParameterDefinitionDataType getModel()
	{
		return _model;
	}
	
	/**
	 * Gets the name of this enum value.
	 * @return the name of this enum value
	 */
	public String getName()
	{
		return _model.getName();
	}
	
	/**
	 * Returns the BusinessObject matching to the specified model object.
	 * @returns the enum value matching to the given model or the default value. 
	 */
	public static ParameterDefinitionDataTypeBO getForModel(ParameterDefinitionDataType model)
	{
		for(ParameterDefinitionDataTypeBO bo : values())
		{
			if(bo.getModel().getName().equals(model.getName()))
			{
				return bo;
			}
		}
		return NUMBERS;
	}
}