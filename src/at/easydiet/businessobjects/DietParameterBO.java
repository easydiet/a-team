package at.easydiet.businessobjects;

import java.util.Date;

import at.easydiet.model.DietParameter;

/**
 * This class encapsules a DietParameter instance.
 */
public class DietParameterBO extends DietParameterTemplateBO
{
	private DietParameter _model;
	
    /**
     * Initializes a new instance of the {@link DietParameterBO} class.
     */
	public DietParameterBO()
	{
		// TODO: add default values
		this(new DietParameter());
	}
	
    /**
     * Initializes a new instance of the {@link DietParameterBO} class.
     * @param model the original model object
     */
	public DietParameterBO(DietParameter model)
	{
		super(model);
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link DietParameter} object.
	 */
 	public DietParameter getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the start of this instance. 
     * @return the start currently set for this instance.
     */
    public Date getStart() 
    {
        return _model.getStart();
    }
    
    /**       
     * Sets the start of this instance. 
     * @param start the new start of this instance.
     */    
    public void setStart(Date start) 
    {
        _model.setStart(start);
    }
}