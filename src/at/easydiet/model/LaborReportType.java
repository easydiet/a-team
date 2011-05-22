package at.easydiet.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a LaborReportType
 */
public class LaborReportType  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 6386572436096191564L;
    private long _laborReportTypeId;
    private String _Name;
    private Set<ParameterDefinition> _parameterDefinitions = new HashSet<ParameterDefinition>(0);

    /**
     * Initializes a new instance of the {@link LaborReportType} class.
     */
    public LaborReportType() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link LaborReportType} class.
     * @param Name the Name to set for this instance
     */
    public LaborReportType(String Name) 
    {
        _Name = Name;
    }

    /**
     * Initializes a new instance of the {@link LaborReportType} class.
     * @param Name the Name to set for this instance
     * @param parameterDefinitions the parameterDefinitions to set for this instance
     */
    public LaborReportType(String Name, Set<ParameterDefinition> parameterDefinitions) 
    {
       _Name = Name;
       _parameterDefinitions = parameterDefinitions;
    }
   
    /**       
     * Gets the laborReportTypeId of this instance. 
     * @return the laborReportTypeId currently set for this instance.
     */
    public long getLaborReportTypeId() 
    {
        return _laborReportTypeId;
    }
    
    /**       
     * Sets the laborReportTypeId of this instance. 
     * @param laborReportTypeId the new laborReportTypeId of this instance.
     */    
    public void setLaborReportTypeId(long laborReportTypeId) 
    {
        _laborReportTypeId = laborReportTypeId;
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
     * Gets the parameterDefinitions of this instance. 
     * @return the parameterDefinitions currently set for this instance.
     */
    public Set<ParameterDefinition> getParameterDefinitions() 
    {
        return _parameterDefinitions;
    }
    
    /**       
     * Sets the parameterDefinitions of this instance. 
     * @param parameterDefinitions the new parameterDefinitions of this instance.
     */    
    public void setParameterDefinitions(Set<ParameterDefinition> parameterDefinitions) 
    {
        _parameterDefinitions = parameterDefinitions;
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
