package at.easydiet.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a ParameterDefinition
 */
public class ParameterDefinition  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 3183702814462580641L;
    private long _parameterDefinitionId;
    private String _name;
    private String _checkPattern;
    private Set<ParameterDefinitionUnit> _units = new HashSet<ParameterDefinitionUnit>(0);

    /**
     * Initializes a new instance of the {@link ParameterDefinition} class.
     */
    public ParameterDefinition() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link ParameterDefinition} class.
     * @param name the name to set for this instance
     */
    public ParameterDefinition(String name) 
    {
        _name = name;
    }

    /**
     * Initializes a new instance of the {@link ParameterDefinition} class.
     * @param name the name to set for this instance
     * @param checkPattern the checkPattern to set for this instance
     * @param units the units to set for this instance
     */
    public ParameterDefinition(String name, String checkPattern, Set<ParameterDefinitionUnit> units) 
    {
       _name = name;
       _checkPattern = checkPattern;
       _units = units;
    }
   
    /**       
     * Gets the parameterDefinitionId of this instance. 
     * @return the parameterDefinitionId currently set for this instance.
     */
    public long getParameterDefinitionId() 
    {
        return _parameterDefinitionId;
    }
    
    /**       
     * Sets the parameterDefinitionId of this instance. 
     * @param parameterDefinitionId the new parameterDefinitionId of this instance.
     */    
    public void setParameterDefinitionId(long parameterDefinitionId) 
    {
        _parameterDefinitionId = parameterDefinitionId;
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
     * Gets the checkPattern of this instance. 
     * @return the checkPattern currently set for this instance.
     */
    public String getCheckPattern() 
    {
        return _checkPattern;
    }
    
    /**       
     * Sets the checkPattern of this instance. 
     * @param checkPattern the new checkPattern of this instance.
     */    
    public void setCheckPattern(String checkPattern) 
    {
        _checkPattern = checkPattern;
    }
    
    /**       
     * Gets the units of this instance. 
     * @return the units currently set for this instance.
     */
    public Set<ParameterDefinitionUnit> getUnits() 
    {
        return _units;
    }
    
    /**       
     * Sets the units of this instance. 
     * @param units the new units of this instance.
     */    
    public void setUnits(Set<ParameterDefinitionUnit> units) 
    {
        _units = units;
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
