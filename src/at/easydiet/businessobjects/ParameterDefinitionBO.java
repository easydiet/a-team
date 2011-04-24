package at.easydiet.businessobjects;

import java.util.HashSet;
import java.util.Set;

import at.easydiet.model.ParameterDefinition;
import at.easydiet.model.ParameterDefinitionUnit;

public class ParameterDefinitionBO {
	private ParameterDefinition _parameterDefinition;
	
	public ParameterDefinitionBO(ParameterDefinition parameterDefinition) {
		_parameterDefinition = parameterDefinition;
	}
	
	/**       
     * Gets the parameterDefinitionId of this instance. 
     * @return the parameterDefinitionId currently set for this instance.
     */
    public long getParameterDefinitionId() 
    {
        return _parameterDefinition.getParameterDefinitionId();
    }
    
    /**       
     * Sets the parameterDefinitionId of this instance. 
     * @param parameterDefinitionId the new parameterDefinitionId of this instance.
     */    
    public void setParameterDefinitionId(long parameterDefinitionId) 
    {
        _parameterDefinition.setParameterDefinitionId(parameterDefinitionId);
    }
    
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _parameterDefinition.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _parameterDefinition.setName(name);
    }
    
    /**       
     * Gets the checkPattern of this instance. 
     * @return the checkPattern currently set for this instance.
     */
    public String getCheckPattern() 
    {
        return _parameterDefinition.getCheckPattern();
    }
    
    /**       
     * Sets the checkPattern of this instance. 
     * @param checkPattern the new checkPattern of this instance.
     */    
    public void setCheckPattern(String checkPattern) 
    {
        _parameterDefinition.setCheckPattern(checkPattern);
    }
    
    /**       
     * Gets the units of this instance. 
     * @return the units currently set for this instance.
     */
    public Set<ParameterDefinitionUnitBO> getUnitsBO() 
    {
    	Set<ParameterDefinitionUnitBO> parameterDefinitionUnitBOs = new HashSet<ParameterDefinitionUnitBO>();
    	
        for(ParameterDefinitionUnit parameterDefinitionUnit : _parameterDefinition.getUnits())
        {
        	parameterDefinitionUnitBOs.add(new ParameterDefinitionUnitBO(parameterDefinitionUnit));
        }
      
        return parameterDefinitionUnitBOs;
    }
    
    public boolean equals(Object o)
    {
    	ParameterDefinition compareDefinition = ((ParameterDefinitionBO)o)._parameterDefinition;
    	
    	if(		(
    				(
    					_parameterDefinition.getCheckPattern() == null 
    					&& compareDefinition.getCheckPattern() == null
	    			) 
	    			|| 
	    			(
	    				_parameterDefinition.getCheckPattern() != null
	    				&&
	    				compareDefinition.getCheckPattern() != null
	    				&&
	    				_parameterDefinition.getCheckPattern().equals(compareDefinition.getCheckPattern())
	    			)
	    		)
    			&& _parameterDefinition.getName().equals(compareDefinition.getName())
    			&& _parameterDefinition.getParameterDefinitionId() == compareDefinition.getParameterDefinitionId()
    			&& _parameterDefinition.getUnits().equals(compareDefinition.getUnits())
    			)
    	{
    		return true;
    	}
    	
    	//default value
    	return false;
    		
    }
}
