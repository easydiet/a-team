package at.easydiet.model;


/**
 * Represents a PatientStateType
 */
public class PatientStateType  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 2899376213658626422L;
    private String _name;

    /**
     * Initializes a new instance of the {@link PatientStateType} class.
     */
    public PatientStateType() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link PatientStateType} class.
     * @param name the name to set for this instance
     */
    public PatientStateType(String name) 
    {
       _name = name;
    }
   
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _name;
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _name = name;
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
        builder.append("name").append("='").append(getName()).append("' ");			
        builder.append("]");
      
        return builder.toString();
    }
}
