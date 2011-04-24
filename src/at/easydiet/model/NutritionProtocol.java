package at.easydiet.model;

import java.sql.Clob;
import java.util.Date;
import java.util.Set;

/**
 * Represents a NutritionProtocol
 */
public class NutritionProtocol extends at.easydiet.model.DietPlan implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -1874487456969697799L;
    private Date _date;
    private String _contact;
    private Clob _notice;

    /**
     * Initializes a new instance of the {@link NutritionProtocol} class.
     */
    public NutritionProtocol() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link NutritionProtocol} class.
     * @param date the date to set for this instance
     */
    public NutritionProtocol(String name, Date createdOn, PlanType planType, DietTreatment dietTreatment, SystemUser creator, Date date) 
    {
        super(name, createdOn, planType, dietTreatment, creator);        
        _date = date;
    }

    /**
     * Initializes a new instance of the {@link NutritionProtocol} class.
     * @param date the date to set for this instance
     * @param contact the contact to set for this instance
     * @param notice the notice to set for this instance
     */
    public NutritionProtocol(String name, Date createdOn, PlanType planType, DietTreatment dietTreatment, Set<DietParameter> dietParameters, SystemUser creator, Set<TimeSpan> timeSpans, Date date, String contact, Clob notice) 
    {
        super(name, createdOn, planType, dietTreatment, dietParameters, creator, timeSpans);        
       _date = date;
       _contact = contact;
       _notice = notice;
    }
   
    /**       
     * Gets the date of this instance. 
     * @return the date currently set for this instance.
     */
    public Date getDate() 
    {
        return _date;
    }
    
    /**       
     * Sets the date of this instance. 
     * @param date the new date of this instance.
     */    
    public void setDate(Date date) 
    {
        _date = date;
    }
    
    /**       
     * Gets the contact of this instance. 
     * @return the contact currently set for this instance.
     */
    public String getContact() 
    {
        return _contact;
    }
    
    /**       
     * Sets the contact of this instance. 
     * @param contact the new contact of this instance.
     */    
    public void setContact(String contact) 
    {
        _contact = contact;
    }
    
    /**       
     * Gets the notice of this instance. 
     * @return the notice currently set for this instance.
     */
    public Clob getNotice() 
    {
        return _notice;
    }
    
    /**       
     * Sets the notice of this instance. 
     * @param notice the new notice of this instance.
     */    
    public void setNotice(Clob notice) 
    {
        _notice = notice;
    }
    
    /**
     * Returns a string representation of this instance.
     * @return a string
     */
    @Override
    public String toString() 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
        builder.append("]");
      
        return builder.toString();
    }
}
