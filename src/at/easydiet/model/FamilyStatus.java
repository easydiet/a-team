package at.easydiet.model;


/**
 * Represents a FamilyStatus
 */
public class FamilyStatus  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 2273876791625288594L;
    private String _status;

    /**
     * Initializes a new instance of the {@link FamilyStatus} class.
     */
    public FamilyStatus() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link FamilyStatus} class.
     * @param status the status to set for this instance
     */
    public FamilyStatus(String status) 
    {
       _status = status;
    }
   
    /**       
     * Gets the status of this instance. 
     * @return the status currently set for this instance.
     */
    public String getStatus() 
    {
        return _status;
    }
    
    /**       
     * Sets the status of this instance. 
     * @param status the new status of this instance.
     */    
    public void setStatus(String status) 
    {
        _status = status;
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
        builder.append("status").append("='").append(getStatus()).append("' ");			
        builder.append("]");
      
        return builder.toString();
    }
}
