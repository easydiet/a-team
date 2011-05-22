package at.easydiet.model;


/**
 * Represents a Illness
 */
public class Illness  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 5275615554243476827L;
    private long _illnessId;
    private String _Name;

    /**
     * Initializes a new instance of the {@link Illness} class.
     */
    public Illness() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link Illness} class.
     * @param Name the Name to set for this instance
     */
    public Illness(String Name) 
    {
       _Name = Name;
    }
   
    /**       
     * Gets the illnessId of this instance. 
     * @return the illnessId currently set for this instance.
     */
    public long getIllnessId() 
    {
        return _illnessId;
    }
    
    /**       
     * Sets the illnessId of this instance. 
     * @param illnessId the new illnessId of this instance.
     */    
    public void setIllnessId(long illnessId) 
    {
        _illnessId = illnessId;
    }
    
    /**       
     * Gets the Name of this instance. 
     * @return the Name currently set for this instance.
     */
    public String getName() 
    {
        return _Name;
    }
    
    /**       
     * Sets the Name of this instance. 
     * @param Name the new Name of this instance.
     */    
    public void setName(String Name) 
    {
        _Name = Name;
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
