package at.easydiet.businessobjects;

import at.easydiet.model.Gender;

/**
 * This class encapsules a Gender instance.
 */
public enum GenderBO
{
	// TODO: Add correct values here
	DEFAULT("")
	;

	private Gender _model;

    /**
     * Initializes a new instance of the {@link GenderBO} enum.
     * @param model the enum value
     */
	private GenderBO(String value)
	{
		_model = new Gender(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link Gender} object.
	 */
 	public Gender getModel()
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
	public static GenderBO getForModel(Gender model)
	{
		for(GenderBO bo : values())
		{
			if(bo.getModel().getName().equals(model.getName()))
			{
				return bo;
			}
		}
		return DEFAULT;
	}
}