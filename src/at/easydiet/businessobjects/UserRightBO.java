package at.easydiet.businessobjects;

import at.easydiet.model.UserRight;

/**
 * This class encapsules a UserRight instance.
 */
public class UserRightBO 
{
	private UserRight _model;
	
    /**
     * Initializes a new instance of the {@link UserRightBO} class.
     */
	public UserRightBO()
	{
		// TODO: add default values
		this(new UserRight());
	}
	
    /**
     * Initializes a new instance of the {@link UserRightBO} class.
     * @param model the original model object
     */
	public UserRightBO(UserRight model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link UserRight} object.
	 */
 	public UserRight getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _model.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _model.setName(name);
    }

}