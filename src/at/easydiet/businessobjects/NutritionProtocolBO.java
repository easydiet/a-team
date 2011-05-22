package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;

import at.easydiet.model.NutritionProtocol;

/**
 * This class encapsules a NutritionProtocol instance.
 */
public class NutritionProtocolBO extends DietPlanBO
{
	private NutritionProtocol _model;
	
    /**     * Initializes a new instance of the {@link NutritionProtocolBO} class.
     */
	public NutritionProtocolBO()
	{

		// TODO: add default values
		this(new NutritionProtocol());
	}
	
    /**
     * Initializes a new instance of the {@link NutritionProtocolBO} class.
     * @param model the original model object
     */
	public NutritionProtocolBO(NutritionProtocol model)
	{
		super(model);
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link NutritionProtocol} object.
	 */
 	public NutritionProtocol getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the date of this instance. 
     * @return the date currently set for this instance.
     */
    public Date getDate() 
    {
        return _model.getDate();
    }
    
    /**       
     * Sets the date of this instance. 
     * @param date the new date of this instance.
     */    
    public void setDate(Date date) 
    {
        _model.setDate(date);
    }

    /**       
     * Gets the contact of this instance. 
     * @return the contact currently set for this instance.
     */
    public String getContact() 
    {
        return _model.getContact();
    }
    
    /**       
     * Sets the contact of this instance. 
     * @param contact the new contact of this instance.
     */    
    public void setContact(String contact) 
    {
        _model.setContact(contact);
    }

    /**       
     * Gets the notice of this instance. 
     * @return the notice currently set for this instance.
     */
    public Clob getNotice() 
    {
        return _model.getNotice();
    }
    
    /**       
     * Sets the notice of this instance. 
     * @param notice the new notice of this instance.
     */    
    public void setNotice(Clob notice) 
    {
        _model.setNotice(notice);
    }

}