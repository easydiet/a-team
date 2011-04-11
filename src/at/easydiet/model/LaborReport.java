package at.easydiet.model;

import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a LaborReport
 */
public class LaborReport  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -5874498161136010551L;
    private long _laborReportId;
    private Date _date;
    private Clob _notice;
    private SystemUser _creator;
    private Set<DietParameter> _dietParameters = new HashSet<DietParameter>(0);

    /**
     * Initializes a new instance of the {@link LaborReport} class.
     */
    public LaborReport() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link LaborReport} class.
     * @param date the date to set for this instance
     * @param creator the creator to set for this instance
     */
    public LaborReport(Date date, SystemUser creator) 
    {
        _date = date;
        _creator = creator;
    }

    /**
     * Initializes a new instance of the {@link LaborReport} class.
     * @param date the date to set for this instance
     * @param notice the notice to set for this instance
     * @param creator the creator to set for this instance
     * @param dietParameters the dietParameters to set for this instance
     */
    public LaborReport(Date date, Clob notice, SystemUser creator, Set<DietParameter> dietParameters) 
    {
       _date = date;
       _notice = notice;
       _creator = creator;
       _dietParameters = dietParameters;
    }
   
    /**       
     * Gets the laborReportId of this instance. 
     * @return the laborReportId currently set for this instance.
     */
    public long getLaborReportId() 
    {
        return _laborReportId;
    }
    
    /**       
     * Sets the laborReportId of this instance. 
     * @param laborReportId the new laborReportId of this instance.
     */    
    public void setLaborReportId(long laborReportId) 
    {
        _laborReportId = laborReportId;
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
     * Gets the creator of this instance. 
     * @return the creator currently set for this instance.
     */
    public SystemUser getCreator() 
    {
        return _creator;
    }
    
    /**       
     * Sets the creator of this instance. 
     * @param creator the new creator of this instance.
     */    
    public void setCreator(SystemUser creator) 
    {
        _creator = creator;
    }
    
    /**       
     * Gets the dietParameters of this instance. 
     * @return the dietParameters currently set for this instance.
     */
    public Set<DietParameter> getDietParameters() 
    {
        return _dietParameters;
    }
    
    /**       
     * Sets the dietParameters of this instance. 
     * @param dietParameters the new dietParameters of this instance.
     */    
    public void setDietParameters(Set<DietParameter> dietParameters) 
    {
        _dietParameters = dietParameters;
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
