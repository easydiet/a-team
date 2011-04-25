package at.easydiet.businessobjects;


import java.sql.Clob;
import java.util.Date;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.DietParameter;
import at.easydiet.model.LaborReport;

/**
 * This class encapsules a LaborReport instance.
 */
public class LaborReportBO
{
	private LaborReport _model;
	
    /**
     * Initializes a new instance of the {@link LaborReportBO} class.
     */
	public LaborReportBO()
	{
		// TODO: add default values
		this(new LaborReport());
	}
	
    /**
     * Initializes a new instance of the {@link LaborReportBO} class.
     * @param model the original model object
     */
	public LaborReportBO(LaborReport model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link LaborReport} object.
	 */
 	public LaborReport getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the laborReportId of this instance. 
     * @return the laborReportId currently set for this instance.
     */
    public long getLaborReportId() 
    {
        return _model.getLaborReportId();
    }
    
    /**       
     * Sets the laborReportId of this instance. 
     * @param laborReportId the new laborReportId of this instance.
     */    
    public void setLaborReportId(long laborReportId) 
    {
        _model.setLaborReportId(laborReportId);
    }

    /**       
     * Gets the date of this instance. 
     * @return the date currently set for this instance.
     */
    public Date getDate() 
    {
        return _model.getDate();
    }
    
    /**       
     * Sets the date of this instance. 
     * @param date the new date of this instance.
     */    
    public void setDate(Date date) 
    {
        _model.setDate(date);
    }

    /**       
     * Gets the notice of this instance. 
     * @return the notice currently set for this instance.
     */
    public Clob getNotice() 
    {
        return _model.getNotice();
    }
    
    /**       
     * Sets the notice of this instance. 
     * @param notice the new notice of this instance.
     */    
    public void setNotice(Clob notice) 
    {
        _model.setNotice(notice);
    }

	
    private SystemUserBO _creator;
    
    /**
     * Gets the currently referenced Creator of this instance.
     * @return the SystemUser currently referenced in this LaborReport. 
     */
    public SystemUserBO getCreator()
    {
        if(_creator == null)
        {
            _creator = new SystemUserBO(_model.getCreator());
        }
        return _creator;
    }
    
    /**
     * Sets the Creator to be referenced in this instance
     * @param creator the SystemUser to reference in this LaborReport. 
     */
    public void setCreator(SystemUserBO creator)
    {
        _creator = creator;
        _model.setCreator(creator.getModel());
    }

	private List<DietParameterBO> _dietParameters;
	
    /**
     * Gets a list of referenced DietParameters of this instance.
     * This list is cached, use {@link LaborReport#updateDietParametersCache()) to update this cache.
     * @return a cached list of referenced DietParameters wrapped into the correct businessobject. 
     */
    public List<DietParameterBO> getDietParameters()
    {
        if(_dietParameters == null) 
        {
            _dietParameters = new ArrayList<DietParameterBO>();
            for(DietParameter dietParameters : _model.getDietParameters())
            {
                _dietParameters.add(new DietParameterBO(dietParameters));
            }
        }
        return _dietParameters;
    }
	
    /**
     * Adds a new DietParameter to the list of referenced dietParameters.
     * The cache will updated
     * @param dietParameters the DietParameter to add. 
     */
    public void addDietParameters(DietParameterBO dietParameters)
    {
        getDietParameters().add(dietParameters);
        _model.getDietParameters().add(dietParameters.getModel());
    }
    
        
    /**
     * Removes the given DietParameter from the list of referenced dietParameters.
     * The cache will updated
     * @param dietParameters the timespan to add. 
     */
    public void removeDietParameters(DietParameterBO dietParameters)
    {
        getDietParameters().remove(dietParameters);
        _model.getDietParameters().remove(dietParameters.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced dietParameters.
     */
    public void updateDietParametersCache()
    {
        _dietParameters = null;
        getDietParameters();
    }

	
    private PatientBO _patient;
    
    /**
     * Gets the currently referenced Patient of this instance.
     * @return the Patient currently referenced in this LaborReport. 
     */
    public PatientBO getPatient()
    {
        if(_patient == null)
        {
            _patient = new PatientBO(_model.getPatient());
        }
        return _patient;
    }
    
    /**
     * Sets the Patient to be referenced in this instance
     * @param patient the Patient to reference in this LaborReport. 
     */
    public void setPatient(PatientBO patient)
    {
        _patient = patient;
        _model.setPatient(patient.getModel());
    }
}