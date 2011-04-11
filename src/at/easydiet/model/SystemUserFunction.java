package at.easydiet.model;


/**
 * Represents a SystemUserFunction
 */
public class SystemUserFunction  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 6446211373297601243L;
    private String _name;

    /**
     * Initializes a new instance of the {@link SystemUserFunction} class.
     */
    public SystemUserFunction() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link SystemUserFunction} class.
     * @param name the name to set for this instance
     */
    public SystemUserFunction(String name) 
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
        builder.append("]");
      
        return builder.toString();
    }
}
