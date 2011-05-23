package at.easydiet.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a DietParameterSet
 */
public class DietParameterSet  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 999651491618976517L;
    private long _dietParameterSetId;
    private String _name;
    private Set<DietParameterTemplate> _dietParameterTemplates = new HashSet<DietParameterTemplate>(0);

    /**
     * Initializes a new instance of the {@link DietParameterSet} class.
     */
    public DietParameterSet() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link DietParameterSet} class.
     * @param name the name to set for this instance
     */
    public DietParameterSet(String name) 
    {
        _name = name;
    }

    /**
     * Initializes a new instance of the {@link DietParameterSet} class.
     * @param name the name to set for this instance
     * @param dietParameterTemplates the dietParameterTemplates to set for this instance
     */
    public DietParameterSet(String name, Set<DietParameterTemplate> dietParameterTemplates) 
    {
       _name = name;
       _dietParameterTemplates = dietParameterTemplates;
    }
   
    /**       
     * Gets the dietParameterSetId of this instance. 
     * @return the dietParameterSetId currently set for this instance.
     */
    public long getDietParameterSetId() 
    {
        return _dietParameterSetId;
    }
    
    /**       
     * Sets the dietParameterSetId of this instance. 
     * @param dietParameterSetId the new dietParameterSetId of this instance.
     */    
    public void setDietParameterSetId(long dietParameterSetId) 
    {
        _dietParameterSetId = dietParameterSetId;
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
     * Gets the dietParameterTemplates of this instance. 
     * @return the dietParameterTemplates currently set for this instance.
     */
    public Set<DietParameterTemplate> getDietParameterTemplates() 
    {
        return _dietParameterTemplates;
    }
    
    /**       
     * Sets the dietParameterTemplates of this instance. 
     * @param dietParameterTemplates the new dietParameterTemplates of this instance.
     */    
    public void setDietParameterTemplates(Set<DietParameterTemplate> dietParameterTemplates) 
    {
        _dietParameterTemplates = dietParameterTemplates;
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
