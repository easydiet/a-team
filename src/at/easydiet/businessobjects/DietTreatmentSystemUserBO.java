package at.easydiet.businessobjects;

import at.easydiet.model.DietTreatmentSystemUser;

/**
 * This class encapsules a DietTreatmentSystemUser instance.
 */
public class DietTreatmentSystemUserBO 
{
	private DietTreatmentSystemUser _model;
	
    /**
     * Initializes a new instance of the {@link DietTreatmentSystemUserBO} class.
     */
	public DietTreatmentSystemUserBO()
	{
		// TODO: add default values
		this(new DietTreatmentSystemUser());
	}
	
    /**
     * Initializes a new instance of the {@link DietTreatmentSystemUserBO} class.
     * @param model the original model object
     */
	public DietTreatmentSystemUserBO(DietTreatmentSystemUser model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link DietTreatmentSystemUser} object.
	 */
 	public DietTreatmentSystemUser getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the dietTreatmentSystemUserId of this instance. 
     * @return the dietTreatmentSystemUserId currently set for this instance.
     */
    public long getDietTreatmentSystemUserId() 
    {
        return _model.getDietTreatmentSystemUserId();
    }
    
    /**       
     * Sets the dietTreatmentSystemUserId of this instance. 
     * @param dietTreatmentSystemUserId the new dietTreatmentSystemUserId of this instance.
     */    
    public void setDietTreatmentSystemUserId(long dietTreatmentSystemUserId) 
    {
        _model.setDietTreatmentSystemUserId(dietTreatmentSystemUserId);
    }

	
    private SystemUserBO _systemUser;
    
    /**
     * Gets the currently referenced SystemUser of this instance.
     * @return the SystemUser currently referenced in this DietTreatmentSystemUser. 
     */
    public SystemUserBO getSystemUser()
    {
        if(_systemUser == null)
        {
            _systemUser = new SystemUserBO(_model.getSystemUser());
        }
        return _systemUser;
    }
    
    /**
     * Sets the SystemUser to be referenced in this instance
     * @param systemUser the SystemUser to reference in this DietTreatmentSystemUser. 
     */
    public void setSystemUser(SystemUserBO systemUser)
    {
        _systemUser = systemUser;
        _model.setSystemUser(systemUser.getModel());
    }
	
    private SystemUserFunctionBO _function;
    
    /**
     * Gets the currently referenced Function of this instance.
     * @return the SystemUserFunction currently referenced in this DietTreatmentSystemUser. 
     */
    public SystemUserFunctionBO getFunction()
    {
        if(_function == null)
        {
            _function = SystemUserFunctionBO.getForModel(_model.getFunction());
        }
        return _function;
    }
    
    /**
     * Sets the Function to be referenced in this instance
     * @param function the SystemUserFunction to reference in this DietTreatmentSystemUser. 
     */
    public void setFunction(SystemUserFunctionBO function)
    {
        _function = function;
        _model.setFunction(function.getModel());
    }
}