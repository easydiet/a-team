package at.easydiet.businessobjects;

import at.easydiet.model.FamilyStatus;

/**
 * This class encapsules a FamilyStatus instance.
 */
public enum FamilyStatusBO
{
	// TODO: Add correct values here
	DEFAULT("")
	;

	private FamilyStatus _model;

    /**
     * Initializes a new instance of the {@link FamilyStatusBO} enum.
     * @param model the enum value
     */
	private FamilyStatusBO(String value)
	{
		_model = new FamilyStatus(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link FamilyStatus} object.
	 */
 	public FamilyStatus getModel()
	{
		return _model;
	}
	
	/**
	 * Gets the name of this enum value.
	 * @return the name of this enum value
	 */
	public String getStatus()
	{
		return _model.getStatus();
	}
	
	/**
	 * Returns the BusinessObject matching to the specified model object.
	 * @returns the enum value matching to the given model or the default value. 
	 */
	public static FamilyStatusBO getForModel(FamilyStatus model)
	{
		for(FamilyStatusBO bo : values())
		{
			if(bo.getModel().getStatus().equals(model.getStatus()))
			{
				return bo;
			}
		}
		return DEFAULT;
	}
}