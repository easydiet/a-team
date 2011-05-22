package at.easydiet.businessobjects;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.LaborReportType;
import at.easydiet.model.ParameterDefinition;

/**
 * This class encapsules a LaborReportType instance.
 */
public class LaborReportTypeBO 
{
	private LaborReportType _model;
	
    /**
     * Initializes a new instance of the {@link LaborReportTypeBO} class.
     */
	public LaborReportTypeBO()
	{
		// TODO: add default values
		this(new LaborReportType());
	}
	
    /**
     * Initializes a new instance of the {@link LaborReportTypeBO} class.
     * @param model the original model object
     */
	public LaborReportTypeBO(LaborReportType model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link LaborReportType} object.
	 */
 	public LaborReportType getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the laborReportTypeId of this instance. 
     * @return the laborReportTypeId currently set for this instance.
     */
    public long getLaborReportTypeId() 
    {
        return _model.getLaborReportTypeId();
    }
    
    /**       
     * Sets the laborReportTypeId of this instance. 
     * @param laborReportTypeId the new laborReportTypeId of this instance.
     */    
    public void setLaborReportTypeId(long laborReportTypeId) 
    {
        _model.setLaborReportTypeId(laborReportTypeId);
    }

    /**       
     * Gets the Name of this instance. 
     * @return the Name currently set for this instance.
     */
    public String getName() 
    {
        return _model.getName();
    }
    
    /**       
     * Sets the Name of this instance. 
     * @param Name the new Name of this instance.
     */    
    public void setName(String Name) 
    {
        _model.setName(Name);
    }


	private List<ParameterDefinitionBO> _parameterDefinitions;
	
    /**
     * Gets a list of referenced ParameterDefinitions of this instance.
     * This list is cached, use {@link LaborReportType#updateParameterDefinitionsCache()) to update this cache.
     * @return a cached list of referenced ParameterDefinitions wrapped into the correct businessobject. 
     */
    public List<ParameterDefinitionBO> getParameterDefinitions()
    {
        if(_parameterDefinitions == null) 
        {
            _parameterDefinitions = new ArrayList<ParameterDefinitionBO>();
            for(ParameterDefinition parameterDefinitions : _model.getParameterDefinitions())
            {
                _parameterDefinitions.add(new ParameterDefinitionBO(parameterDefinitions));
            }
        }
        return _parameterDefinitions;
    }
	
    /**
     * Adds a new ParameterDefinition to the list of referenced parameterDefinitions.
     * The cache will updated
     * @param parameterDefinitions the ParameterDefinition to add. 
     */
    public void addParameterDefinitions(ParameterDefinitionBO parameterDefinitions)
    {
        getParameterDefinitions().add(parameterDefinitions);
        _model.getParameterDefinitions().add(parameterDefinitions.getModel());
    }
    
        
    /**
     * Removes the given ParameterDefinition from the list of referenced parameterDefinitions.
     * The cache will updated
     * @param parameterDefinitions the timespan to add. 
     */
    public void removeParameterDefinitions(ParameterDefinitionBO parameterDefinitions)
    {
        getParameterDefinitions().remove(parameterDefinitions);
        _model.getParameterDefinitions().remove(parameterDefinitions.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced parameterDefinitions.
     */
    public void updateParameterDefinitionsCache()
    {
        _parameterDefinitions = null;
        getParameterDefinitions();
    }

}