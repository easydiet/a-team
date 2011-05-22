package at.easydiet.businessobjects;


import java.sql.Clob;
import java.util.Date;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.DietTreatment;
import at.easydiet.model.LaborReport;
import at.easydiet.model.PatientState;

/**
 * This class encapsules a PatientState instance.
 */
public class PatientStateBO 
{
	private PatientState _model;
	
    /**
     * Initializes a new instance of the {@link PatientStateBO} class.
     */
	public PatientStateBO()
	{
		// TODO: add default values
		this(new PatientState());
	}
	
    /**
     * Initializes a new instance of the {@link PatientStateBO} class.
     * @param model the original model object
     */
	public PatientStateBO(PatientState model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link PatientState} object.
	 */
 	public PatientState getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the patientStateId of this instance. 
     * @return the patientStateId currently set for this instance.
     */
    public long getPatientStateId() 
    {
        return _model.getPatientStateId();
    }
    
    /**       
     * Sets the patientStateId of this instance. 
     * @param patientStateId the new patientStateId of this instance.
     */    
    public void setPatientStateId(long patientStateId) 
    {
        _model.setPatientStateId(patientStateId);
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
     * Gets the anamnesis of this instance. 
     * @return the anamnesis currently set for this instance.
     */
    public Clob getAnamnesis() 
    {
        return _model.getAnamnesis();
    }
    
    /**       
     * Sets the anamnesis of this instance. 
     * @param anamnesis the new anamnesis of this instance.
     */    
    public void setAnamnesis(Clob anamnesis) 
    {
        _model.setAnamnesis(anamnesis);
    }

    /**       
     * Gets the weight of this instance. 
     * @return the weight currently set for this instance.
     */
    public int getWeight() 
    {
        return _model.getWeight();
    }
    
    /**       
     * Sets the weight of this instance. 
     * @param weight the new weight of this instance.
     */    
    public void setWeight(int weight) 
    {
        _model.setWeight(weight);
    }

    /**       
     * Gets the weightPercentile of this instance. 
     * @return the weightPercentile currently set for this instance.
     */
    public float getWeightPercentile() 
    {
        return _model.getWeightPercentile();
    }
    
    /**       
     * Sets the weightPercentile of this instance. 
     * @param weightPercentile the new weightPercentile of this instance.
     */    
    public void setWeightPercentile(float weightPercentile) 
    {
        _model.setWeightPercentile(weightPercentile);
    }

    /**       
     * Gets the height of this instance. 
     * @return the height currently set for this instance.
     */
    public int getHeight() 
    {
        return _model.getHeight();
    }
    
    /**       
     * Sets the height of this instance. 
     * @param height the new height of this instance.
     */    
    public void setHeight(int height) 
    {
        _model.setHeight(height);
    }

    /**       
     * Gets the heightPercentile of this instance. 
     * @return the heightPercentile currently set for this instance.
     */
    public float getHeightPercentile() 
    {
        return _model.getHeightPercentile();
    }
    
    /**       
     * Sets the heightPercentile of this instance. 
     * @param heightPercentile the new heightPercentile of this instance.
     */    
    public void setHeightPercentile(float heightPercentile) 
    {
        _model.setHeightPercentile(heightPercentile);
    }

    /**       
     * Gets the compliance of this instance. 
     * @return the compliance currently set for this instance.
     */
    public int getCompliance() 
    {
        return _model.getCompliance();
    }
    
    /**       
     * Sets the compliance of this instance. 
     * @param compliance the new compliance of this instance.
     */    
    public void setCompliance(int compliance) 
    {
        _model.setCompliance(compliance);
    }

    /**       
     * Gets the motivation of this instance. 
     * @return the motivation currently set for this instance.
     */
    public int getMotivation() 
    {
        return _model.getMotivation();
    }
    
    /**       
     * Sets the motivation of this instance. 
     * @param motivation the new motivation of this instance.
     */    
    public void setMotivation(int motivation) 
    {
        _model.setMotivation(motivation);
    }

	
    private PatientStateTypeBO _type;
    
    /**
     * Gets the currently referenced Type of this instance.
     * @return the PatientStateType currently referenced in this PatientState. 
     */
    public PatientStateTypeBO getType()
    {
        if(_type == null)
        {
            _type = PatientStateTypeBO.getForModel(_model.getType());
        }
        return _type;
    }
    
    /**
     * Sets the Type to be referenced in this instance
     * @param type the PatientStateType to reference in this PatientState. 
     */
    public void setType(PatientStateTypeBO type)
    {
        _type = type;
        _model.setType(type.getModel());
    }
	
    private SystemUserBO _creator;
    
    /**
     * Gets the currently referenced Creator of this instance.
     * @return the SystemUser currently referenced in this PatientState. 
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
     * @param creator the SystemUser to reference in this PatientState. 
     */
    public void setCreator(SystemUserBO creator)
    {
        _creator = creator;
        _model.setCreator(creator.getModel());
    }

	private List<LaborReportBO> _laborReports;
	
    /**
     * Gets a list of referenced LaborReports of this instance.
     * This list is cached, use {@link PatientState#updateLaborReportsCache()) to update this cache.
     * @return a cached list of referenced LaborReports wrapped into the correct businessobject. 
     */
    public List<LaborReportBO> getLaborReports()
    {
        if(_laborReports == null) 
        {
            _laborReports = new ArrayList<LaborReportBO>();
            for(LaborReport laborReports : _model.getLaborReports())
            {
                _laborReports.add(new LaborReportBO(laborReports));
            }
        }
        return _laborReports;
    }
	
    /**
     * Adds a new LaborReport to the list of referenced laborReports.
     * The cache will updated
     * @param laborReports the LaborReport to add. 
     */
    public void addLaborReports(LaborReportBO laborReports)
    {
        getLaborReports().add(laborReports);
        _model.getLaborReports().add(laborReports.getModel());
    }
    
        
    /**
     * Removes the given LaborReport from the list of referenced laborReports.
     * The cache will updated
     * @param laborReports the timespan to add. 
     */
    public void removeLaborReports(LaborReportBO laborReports)
    {
        getLaborReports().remove(laborReports);
        _model.getLaborReports().remove(laborReports.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced laborReports.
     */
    public void updateLaborReportsCache()
    {
        _laborReports = null;
        getLaborReports();
    }


	private List<DietTreatmentBO> _dietTreatments;
	
    /**
     * Gets a list of referenced DietTreatments of this instance.
     * This list is cached, use {@link PatientState#updateDietTreatmentsCache()) to update this cache.
     * @return a cached list of referenced DietTreatments wrapped into the correct businessobject. 
     */
    public List<DietTreatmentBO> getDietTreatments()
    {
        if(_dietTreatments == null) 
        {
            _dietTreatments = new ArrayList<DietTreatmentBO>();
            for(DietTreatment dietTreatments : _model.getDietTreatments())
            {
                _dietTreatments.add(new DietTreatmentBO(dietTreatments));
            }
        }
        return _dietTreatments;
    }
	
    /**
     * Adds a new DietTreatment to the list of referenced dietTreatments.
     * The cache will updated
     * @param dietTreatments the DietTreatment to add. 
     */
    public void addDietTreatments(DietTreatmentBO dietTreatments)
    {
        getDietTreatments().add(dietTreatments);
        _model.getDietTreatments().add(dietTreatments.getModel());
    }
    
        
    /**
     * Removes the given DietTreatment from the list of referenced dietTreatments.
     * The cache will updated
     * @param dietTreatments the timespan to add. 
     */
    public void removeDietTreatments(DietTreatmentBO dietTreatments)
    {
        getDietTreatments().remove(dietTreatments);
        _model.getDietTreatments().remove(dietTreatments.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced dietTreatments.
     */
    public void updateDietTreatmentsCache()
    {
        _dietTreatments = null;
        getDietTreatments();
    }

	
    private PatientBO _patient;
    
    /**
     * Gets the currently referenced Patient of this instance.
     * @return the Patient currently referenced in this PatientState. 
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
     * @param patient the Patient to reference in this PatientState. 
     */
    public void setPatient(PatientBO patient)
    {
        _patient = patient;
        _model.setPatient(patient.getModel());
    }
}