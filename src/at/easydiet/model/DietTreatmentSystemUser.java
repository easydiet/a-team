package at.easydiet.model;


/**
 * Represents a DietTreatmentSystemUser
 */
public class DietTreatmentSystemUser  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -8412868472198205735L;
    private long _dietTreatmentSystemUserId;
    private SystemUser _systemUser;
    private SystemUserFunction _function;

    /**
     * Initializes a new instance of the {@link DietTreatmentSystemUser} class.
     */
    public DietTreatmentSystemUser() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link DietTreatmentSystemUser} class.
     * @param systemUser the systemUser to set for this instance
     * @param function the function to set for this instance
     */
    public DietTreatmentSystemUser(SystemUser systemUser, SystemUserFunction function) 
    {
       _systemUser = systemUser;
       _function = function;
    }
   
    /**       
     * Gets the dietTreatmentSystemUserId of this instance. 
     * @return the dietTreatmentSystemUserId currently set for this instance.
     */
    public long getDietTreatmentSystemUserId() 
    {
        return _dietTreatmentSystemUserId;
    }
    
    /**       
     * Sets the dietTreatmentSystemUserId of this instance. 
     * @param dietTreatmentSystemUserId the new dietTreatmentSystemUserId of this instance.
     */    
    public void setDietTreatmentSystemUserId(long dietTreatmentSystemUserId) 
    {
        _dietTreatmentSystemUserId = dietTreatmentSystemUserId;
    }
    
    /**       
     * Gets the systemUser of this instance. 
     * @return the systemUser currently set for this instance.
     */
    public SystemUser getSystemUser() 
    {
        return _systemUser;
    }
    
    /**       
     * Sets the systemUser of this instance. 
     * @param systemUser the new systemUser of this instance.
     */    
    public void setSystemUser(SystemUser systemUser) 
    {
        _systemUser = systemUser;
    }
    
    /**       
     * Gets the function of this instance. 
     * @return the function currently set for this instance.
     */
    public SystemUserFunction getFunction() 
    {
        return _function;
    }
    
    /**       
     * Sets the function of this instance. 
     * @param function the new function of this instance.
     */    
    public void setFunction(SystemUserFunction function) 
    {
        _function = function;
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
