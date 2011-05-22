package at.easydiet.businessobjects;

import at.easydiet.model.Illness;

/**
 * This class encapsules a Illness instance.
 */
public class IllnessBO 
{
	private Illness _model;
	
    /**
     * Initializes a new instance of the {@link IllnessBO} class.
     */
	public IllnessBO()
	{
		// TODO: add default values
		this(new Illness());
	}
	
    /**
     * Initializes a new instance of the {@link IllnessBO} class.
     * @param model the original model object
     */
	public IllnessBO(Illness model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link Illness} object.
	 */
 	public Illness getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the illnessId of this instance. 
     * @return the illnessId currently set for this instance.
     */
    public long getIllnessId() 
    {
        return _model.getIllnessId();
    }
    
    /**       
     * Sets the illnessId of this instance. 
     * @param illnessId the new illnessId of this instance.
     */    
    public void setIllnessId(long illnessId) 
    {
        _model.setIllnessId(illnessId);
    }

    /**       
     * Gets the Name of this instance. 
     * @return the Name currently set for this instance.
     */
    public String getName() 
    {
        return _model.getName();
    }
    
    /**       
     * Sets the Name of this instance. 
     * @param Name the new Name of this instance.
     */    
    public void setName(String Name) 
    {
        _model.setName(Name);
    }

}