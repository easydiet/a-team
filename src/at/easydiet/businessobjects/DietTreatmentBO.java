package at.easydiet.businessobjects;


import java.util.Calendar;
import java.util.Date;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.ContactJournal;
import at.easydiet.model.DietParameter;
import at.easydiet.model.DietPlan;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.DietTreatmentSystemUser;
import at.easydiet.model.NutritionProtocol;
import at.easydiet.model.PatientState;

/**
 * This class encapsules a DietTreatment instance.
 */
public class DietTreatmentBO implements IDietParameterizable
{
	private DietTreatment _model;
	
    /**
     * Initializes a new instance of the {@link DietTreatmentBO} class.
     */
	public DietTreatmentBO()
	{
		// TODO: add default values
		this(new DietTreatment());
	}
	
    /**
     * Initializes a new instance of the {@link DietTreatmentBO} class.
     * @param model the original model object
     */
	public DietTreatmentBO(DietTreatment model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link DietTreatment} object.
	 */
 	public DietTreatment getModel()
	{
		return _model;
	}
 	
 	/**
 	 * Overrides the model stored in the backstore. 
 	 * @param model
 	 */
 	void setModel(DietTreatment model)
    {
        _model = model;
    }
 	
    /**       
     * Gets the dietTreatmentId of this instance. 
     * @return the dietTreatmentId currently set for this instance.
     */
    public long getDietTreatmentId() 
    {
        return _model.getDietTreatmentId();
    }
    
    /**       
     * Sets the dietTreatmentId of this instance. 
     * @param dietTreatmentId the new dietTreatmentId of this instance.
     */    
    public void setDietTreatmentId(long dietTreatmentId) 
    {
        _model.setDietTreatmentId(dietTreatmentId);
    }

    /**       
     * Gets the start of this instance. 
     * @return the start currently set for this instance.
     */
    public Date getStart() 
    {
        return _model.getStart();
    }
    
    /**       
     * Sets the start of this instance. 
     * @param start the new start of this instance.
     */    
    public void setStart(Date start) 
    {
        _model.setStart(start);
    }

    /**       
     * Gets the duration of this instance. 
     * @return the duration currently set for this instance.
     */
    public int getDuration() 
    {
        return _model.getDuration();
    }
    
    /**       
     * Sets the duration of this instance. 
     * @param duration the new duration of this instance.
     */    
    public void setDuration(int duration) 
    {
        _model.setDuration(duration);
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


	private List<NutritionProtocolBO> _nutritionProtocols;
	
    /**
     * Gets a list of referenced NutritionProtocols of this instance.
     * This list is cached, use {@link DietTreatment#updateNutritionProtocolsCache()) to update this cache.
     * @return a cached list of referenced NutritionProtocols wrapped into the correct businessobject. 
     */
    public List<NutritionProtocolBO> getNutritionProtocols()
    {
        if(_nutritionProtocols == null) 
        {
            _nutritionProtocols = new ArrayList<NutritionProtocolBO>();
            for(NutritionProtocol nutritionProtocols : _model.getNutritionProtocols())
            {
                _nutritionProtocols.add(new NutritionProtocolBO(nutritionProtocols));
            }
        }
        return _nutritionProtocols;
    }
	
    /**
     * Adds a new NutritionProtocol to the list of referenced nutritionProtocols.
     * The cache will updated
     * @param nutritionProtocols the NutritionProtocol to add. 
     */
    public void addNutritionProtocols(NutritionProtocolBO nutritionProtocols)
    {
        getNutritionProtocols().add(nutritionProtocols);
        _model.getNutritionProtocols().add(nutritionProtocols.getModel());
    }
    
        
    /**
     * Removes the given NutritionProtocol from the list of referenced nutritionProtocols.
     * The cache will updated
     * @param nutritionProtocols the timespan to add. 
     */
    public void removeNutritionProtocols(NutritionProtocolBO nutritionProtocols)
    {
        getNutritionProtocols().remove(nutritionProtocols);
        _model.getNutritionProtocols().remove(nutritionProtocols.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced nutritionProtocols.
     */
    public void updateNutritionProtocolsCache()
    {
        _nutritionProtocols = null;
        getNutritionProtocols();
    }


	private List<DietPlanBO> _dietPlans;
	
    /**
     * Gets a list of referenced DietPlans of this instance.
     * This list is cached, use {@link DietTreatment#updateDietPlansCache()) to update this cache.
     * @return a cached list of referenced DietPlans wrapped into the correct businessobject. 
     */
    public List<DietPlanBO> getDietPlans()
    {
        if(_dietPlans == null) 
        {
            _dietPlans = new ArrayList<DietPlanBO>();
            for(DietPlan dietPlans : _model.getDietPlans())
            {
                _dietPlans.add(new DietPlanBO(dietPlans));
            }
        }
        return _dietPlans;
    }
	
    /**
     * Adds a new DietPlan to the list of referenced dietPlans.
     * The cache will updated
     * @param dietPlans the DietPlan to add. 
     */
    public void addDietPlans(DietPlanBO dietPlans)
    {
        dietPlans.setDietTreatment(this);
        getDietPlans().add(dietPlans);
        _model.getDietPlans().add(dietPlans.getModel());
    }
    
        
    /**
     * Removes the given DietPlan from the list of referenced dietPlans.
     * The cache will updated
     * @param dietPlans the timespan to add. 
     */
    public void removeDietPlans(DietPlanBO dietPlans)
    {
        getDietPlans().remove(dietPlans);
        _model.getDietPlans().remove(dietPlans.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced dietPlans.
     */
    public void updateDietPlansCache()
    {
        _dietPlans = null;
        getDietPlans();
    }


	private List<DietParameterTemplateBO> _dietParameters;
	
    /**
     * Gets a list of referenced DietParameters of this instance.
     * This list is cached, use {@link DietTreatment#updateDietParametersCache()) to update this cache.
     * @return a cached list of referenced DietParameters wrapped into the correct businessobject. 
     */
    public List<DietParameterTemplateBO> getDietParameters()
    {
        if(_dietParameters == null) 
        {
            _dietParameters = new ArrayList<DietParameterTemplateBO>();
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
    public void addDietParameters(DietParameterTemplateBO dietParameters)
    {
        getDietParameters().add(dietParameters);
        _model.getDietParameters().add((DietParameter) dietParameters.getModel());
    }
    
        
    /**
     * Removes the given DietParameter from the list of referenced dietParameters.
     * The cache will updated
     * @param dietParameters the timespan to add. 
     */
    public void removeDietParameters(DietParameterTemplateBO dietParameters)
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


	private List<PatientStateBO> _patientStates;
	
    /**
     * Gets a list of referenced PatientStates of this instance.
     * This list is cached, use {@link DietTreatment#updatePatientStatesCache()) to update this cache.
     * @return a cached list of referenced PatientStates wrapped into the correct businessobject. 
     */
    public List<PatientStateBO> getPatientStates()
    {
        if(_patientStates == null) 
        {
            _patientStates = new ArrayList<PatientStateBO>();
            for(PatientState patientStates : _model.getPatientStates())
            {
                _patientStates.add(new PatientStateBO(patientStates));
            }
        }
        return _patientStates;
    }
	
    /**
     * Adds a new PatientState to the list of referenced patientStates.
     * The cache will updated
     * @param patientStates the PatientState to add. 
     */
    public void addPatientStates(PatientStateBO patientStates)
    {
        getPatientStates().add(patientStates);
        _model.getPatientStates().add(patientStates.getModel());
    }
    
        
    /**
     * Removes the given PatientState from the list of referenced patientStates.
     * The cache will updated
     * @param patientStates the timespan to add. 
     */
    public void removePatientStates(PatientStateBO patientStates)
    {
        getPatientStates().remove(patientStates);
        _model.getPatientStates().remove(patientStates.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced patientStates.
     */
    public void updatePatientStatesCache()
    {
        _patientStates = null;
        getPatientStates();
    }


	private List<DietTreatmentSystemUserBO> _systemUsers;
	
    /**
     * Gets a list of referenced SystemUsers of this instance.
     * This list is cached, use {@link DietTreatment#updateSystemUsersCache()) to update this cache.
     * @return a cached list of referenced SystemUsers wrapped into the correct businessobject. 
     */
    public List<DietTreatmentSystemUserBO> getSystemUsers()
    {
        if(_systemUsers == null) 
        {
            _systemUsers = new ArrayList<DietTreatmentSystemUserBO>();
            for(DietTreatmentSystemUser systemUsers : _model.getSystemUsers())
            {
                _systemUsers.add(new DietTreatmentSystemUserBO(systemUsers));
            }
        }
        return _systemUsers;
    }
	
    /**
     * Adds a new DietTreatmentSystemUser to the list of referenced systemUsers.
     * The cache will updated
     * @param systemUsers the DietTreatmentSystemUser to add. 
     */
    public void addSystemUsers(DietTreatmentSystemUserBO systemUsers)
    {
        getSystemUsers().add(systemUsers);
        _model.getSystemUsers().add(systemUsers.getModel());
    }
    
        
    /**
     * Removes the given DietTreatmentSystemUser from the list of referenced systemUsers.
     * The cache will updated
     * @param systemUsers the timespan to add. 
     */
    public void removeSystemUsers(DietTreatmentSystemUserBO systemUsers)
    {
        getSystemUsers().remove(systemUsers);
        _model.getSystemUsers().remove(systemUsers.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced systemUsers.
     */
    public void updateSystemUsersCache()
    {
        _systemUsers = null;
        getSystemUsers();
    }


	private List<ContactJournalBO> _contactJournals;
	
    /**
     * Gets a list of referenced ContactJournals of this instance.
     * This list is cached, use {@link DietTreatment#updateContactJournalsCache()) to update this cache.
     * @return a cached list of referenced ContactJournals wrapped into the correct businessobject. 
     */
    public List<ContactJournalBO> getContactJournals()
    {
        if(_contactJournals == null) 
        {
            _contactJournals = new ArrayList<ContactJournalBO>();
            for(ContactJournal contactJournals : _model.getContactJournals())
            {
                _contactJournals.add(new ContactJournalBO(contactJournals));
            }
        }
        return _contactJournals;
    }
	
    /**
     * Adds a new ContactJournal to the list of referenced contactJournals.
     * The cache will updated
     * @param contactJournals the ContactJournal to add. 
     */
    public void addContactJournals(ContactJournalBO contactJournals)
    {
        getContactJournals().add(contactJournals);
        _model.getContactJournals().add(contactJournals.getModel());
    }
    
        
    /**
     * Removes the given ContactJournal from the list of referenced contactJournals.
     * The cache will updated
     * @param contactJournals the timespan to add. 
     */
    public void removeContactJournals(ContactJournalBO contactJournals)
    {
        getContactJournals().remove(contactJournals);
        _model.getContactJournals().remove(contactJournals.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced contactJournals.
     */
    public void updateContactJournalsCache()
    {
        _contactJournals = null;
        getContactJournals();
    }

	
    private TreatmentStateBO _treatmentState;
    
    /**
     * Gets the currently referenced TreatmentState of this instance.
     * @return the TreatmentState currently referenced in this DietTreatment. 
     */
    public TreatmentStateBO getTreatmentState()
    {
        if(_treatmentState == null)
        {
            _treatmentState = TreatmentStateBO.getForModel(_model.getTreatmentState());
        }
        return _treatmentState;
    }
    
    /**
     * Sets the TreatmentState to be referenced in this instance
     * @param treatmentState the TreatmentState to reference in this DietTreatment. 
     */
    public void setTreatmentState(TreatmentStateBO treatmentState)
    {
        _treatmentState = treatmentState;
        _model.setTreatmentState(treatmentState.getModel());
    }
	
    private PatientBO _patient;
    
    /**
     * Gets the currently referenced Patient of this instance.
     * @return the Patient currently referenced in this DietTreatment. 
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
     * @param patient the Patient to reference in this DietTreatment. 
     */
    public void setPatient(PatientBO patient)
    {
        _patient = patient;
        _model.setPatient(patient.getModel());
    }

    public String getDisplayText()
    {
        return getName();
    }
    
    /**
     * Returns the end date of the diet.
     * @return
     */
    public Date getEnd()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStart());
        calendar.add(Calendar.DATE, getDuration());
        return calendar.getTime();
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (_model.getDietTreatmentId() ^ (_model.getDietTreatmentId() >>> 32));
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof DietTreatmentBO)) return false;
        DietTreatmentBO other = (DietTreatmentBO) obj;
        if (_model.getDietTreatmentId() != other._model.getDietTreatmentId()) return false;
        return true;
    }

}