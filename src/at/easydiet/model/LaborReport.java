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
    private static final long serialVersionUID = 5175617997757707421L;
    private long _laborReportId;
    private Date _date;
    private Clob _notice;
    private SystemUser _creator;
    private Set<LaborParameter> _laborParameters = new HashSet<LaborParameter>(0);
    private LaborReportType _laborReportType;
    private Patient _patient;

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
     * @param patient the patient to set for this instance
     */
    public LaborReport(Date date, SystemUser creator, Patient patient) 
    {
        _date = date;
        _creator = creator;
        _patient = patient;
    }

    /**
     * Initializes a new instance of the {@link LaborReport} class.
     * @param date the date to set for this instance
     * @param notice the notice to set for this instance
     * @param creator the creator to set for this instance
     * @param laborParameters the laborParameters to set for this instance
     * @param laborReportType the laborReportType to set for this instance
     * @param patient the patient to set for this instance
     */
    public LaborReport(Date date, Clob notice, SystemUser creator, Set<LaborParameter> laborParameters, LaborReportType laborReportType, Patient patient) 
    {
       _date = date;
       _notice = notice;
       _creator = creator;
       _laborParameters = laborParameters;
       _laborReportType = laborReportType;
       _patient = patient;
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
     * Gets the laborParameters of this instance. 
     * @return the laborParameters currently set for this instance.
     */
    public Set<LaborParameter> getLaborParameters() 
    {
        return _laborParameters;
    }
    
    /**       
     * Sets the laborParameters of this instance. 
     * @param laborParameters the new laborParameters of this instance.
     */    
    public void setLaborParameters(Set<LaborParameter> laborParameters) 
    {
        _laborParameters = laborParameters;
    }
    
    /**       
     * Gets the laborReportType of this instance. 
     * @return the laborReportType currently set for this instance.
     */
    public LaborReportType getLaborReportType() 
    {
        return _laborReportType;
    }
    
    /**       
     * Sets the laborReportType of this instance. 
     * @param laborReportType the new laborReportType of this instance.
     */    
    public void setLaborReportType(LaborReportType laborReportType) 
    {
        _laborReportType = laborReportType;
    }
    
    /**       
     * Gets the patient of this instance. 
     * @return the patient currently set for this instance.
     */
    public Patient getPatient() 
    {
        return _patient;
    }
    
    /**       
     * Sets the patient of this instance. 
     * @param patient the new patient of this instance.
     */    
    public void setPatient(Patient patient) 
    {
        _patient = patient;
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
