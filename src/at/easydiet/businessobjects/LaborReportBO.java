package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.LaborParameter;
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

	private List<LaborParameterBO> _laborParameters;
	
    /**
     * Gets a list of referenced LaborParameters of this instance.
     * This list is cached, use {@link LaborReport#updateLaborParametersCache()) to update this cache.
     * @return a cached list of referenced LaborParameters wrapped into the correct businessobject. 
     */
    public List<LaborParameterBO> getLaborParameters()
    {
        if(_laborParameters == null) 
        {
            _laborParameters = new ArrayList<LaborParameterBO>();
            for(LaborParameter laborParameters : _model.getLaborParameters())
            {
                _laborParameters.add(new LaborParameterBO(laborParameters));
            }
        }
        return _laborParameters;
    }
	
    /**
     * Adds a new LaborParameter to the list of referenced laborParameters.
     * The cache will updated
     * @param laborParameters the LaborParameter to add. 
     */
    public void addLaborParameters(LaborParameterBO laborParameters)
    {
        getLaborParameters().add(laborParameters);
        _model.getLaborParameters().add(laborParameters.getModel());
    }
    
        
    /**
     * Removes the given LaborParameter from the list of referenced laborParameters.
     * The cache will updated
     * @param laborParameters the timespan to add. 
     */
    public void removeLaborParameters(LaborParameterBO laborParameters)
    {
        getLaborParameters().remove(laborParameters);
        _model.getLaborParameters().remove(laborParameters.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced laborParameters.
     */
    public void updateLaborParametersCache()
    {
        _laborParameters = null;
        getLaborParameters();
    }

	
    private LaborReportTypeBO _laborReportType;
    
    /**
     * Gets the currently referenced LaborReportType of this instance.
     * @return the LaborReportType currently referenced in this LaborReport. 
     */
    public LaborReportTypeBO getLaborReportType()
    {
        if(_laborReportType == null)
        {
            _laborReportType = new LaborReportTypeBO(_model.getLaborReportType());
        }
        return _laborReportType;
    }
    
    /**
     * Sets the LaborReportType to be referenced in this instance
     * @param laborReportType the LaborReportType to reference in this LaborReport. 
     */
    public void setLaborReportType(LaborReportTypeBO laborReportType)
    {
        _laborReportType = laborReportType;
        _model.setLaborReportType(laborReportType.getModel());
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