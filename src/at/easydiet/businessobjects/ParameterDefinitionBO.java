package at.easydiet.businessobjects;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.ParameterDefinition;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * This class encapsules a ParameterDefinition instance.
 */
public class ParameterDefinitionBO
{
	private ParameterDefinition _model;
	
    /**
     * Initializes a new instance of the {@link ParameterDefinitionBO} class.
     */
	public ParameterDefinitionBO()
	{
		// TODO: add default values
		this(new ParameterDefinition());
	}
	
    /**
     * Initializes a new instance of the {@link ParameterDefinitionBO} class.
     * @param model the original model object
     */
	public ParameterDefinitionBO(ParameterDefinition model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link ParameterDefinition} object.
	 */
 	public ParameterDefinition getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the parameterDefinitionId of this instance. 
     * @return the parameterDefinitionId currently set for this instance.
     */
    public long getParameterDefinitionId() 
    {
        return _model.getParameterDefinitionId();
    }
    
    /**       
     * Sets the parameterDefinitionId of this instance. 
     * @param parameterDefinitionId the new parameterDefinitionId of this instance.
     */    
    public void setParameterDefinitionId(long parameterDefinitionId) 
    {
        _model.setParameterDefinitionId(parameterDefinitionId);
    }

    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _model.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _model.setName(name);
    }

    /**       
     * Gets the checkPattern of this instance. 
     * @return the checkPattern currently set for this instance.
     */
    public String getCheckPattern() 
    {
        return _model.getCheckPattern();
    }
    
    /**       
     * Sets the checkPattern of this instance. 
     * @param checkPattern the new checkPattern of this instance.
     */    
    public void setCheckPattern(String checkPattern) 
    {
        _model.setCheckPattern(checkPattern);
    }


	private List<ParameterDefinitionUnitBO> _units;
	
    /**
     * Gets a list of referenced Units of this instance.
     * This list is cached, use {@link ParameterDefinition#updateUnitsCache()) to update this cache.
     * @return a cached list of referenced Units wrapped into the correct businessobject. 
     */
    public List<ParameterDefinitionUnitBO> getUnits()
    {
        if(_units == null) 
        {
            _units = new ArrayList<ParameterDefinitionUnitBO>();
            for(ParameterDefinitionUnit units : _model.getUnits())
            {
                _units.add(new ParameterDefinitionUnitBO(units));
            }
        }
        return _units;
    }
	
    /**
     * Adds a new ParameterDefinitionUnit to the list of referenced units.
     * The cache will updated
     * @param units the ParameterDefinitionUnit to add. 
     */
    public void addUnit(ParameterDefinitionUnitBO unit)
    {
        getUnits().add(unit);
        _model.getUnits().add(unit.getModel());
    }
    
        
    /**
     * Removes the given ParameterDefinitionUnit from the list of referenced units.
     * The cache will updated
     * @param units the timespan to add. 
     */
    public void removeUnits(ParameterDefinitionUnitBO units)
    {
        getUnits().remove(units);
        _model.getUnits().remove(units.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced units.
     */
    public void updateUnitsCache()
    {
        _units = null;
        getUnits();
    }

    public String toString()
    {
    	return getName();
    }
    
    public boolean equals(Object obj)
    {
    	ParameterDefinitionBO bo = (ParameterDefinitionBO) obj;
    	//TODO:
    	return false;
    }
}