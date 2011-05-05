package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;
import java.util.Set;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.DietTreatment;
import at.easydiet.model.FamilyAnamnesis;
import at.easydiet.model.LaborReport;
import at.easydiet.model.Patient;
import at.easydiet.model.PatientLike;
import at.easydiet.model.PatientState;

/**
 * This class encapsules a Patient instance.
 */
public class PatientBO
{
    private Patient _model;

    /**
     * Initializes a new instance of the {@link PatientBO} class.
     */
    public PatientBO()
    {
        // TODO: add default values
        this(new Patient());
    }

    /**
     * Initializes a new instance of the {@link PatientBO} class.
     * 
     * @param model the original model object
     */
    public PatientBO(Patient model)
    {
        _model = model;
    }

    /**
     * Gets the original model object used as object store for this
     * BusinessObject.
     * 
     * @return the original {@link Patient} object.
     */
    public Patient getModel()
    {
        return _model;
    }

    /**
     * Gets the patientId of this instance.
     * 
     * @return the patientId currently set for this instance.
     */
    public long getPatientId()
    {
        return _model.getPatientId();
    }

    /**
     * Sets the patientId of this instance.
     * 
     * @param patientId the new patientId of this instance.
     */
    public void setPatientId(long patientId)
    {
        _model.setPatientId(patientId);
    }

    /**
     * Gets the insuranceNumber of this instance.
     * 
     * @return the insuranceNumber currently set for this instance.
     */
    public String getInsuranceNumber()
    {
        return _model.getInsuranceNumber();
    }

    /**
     * Sets the insuranceNumber of this instance.
     * 
     * @param insuranceNumber the new insuranceNumber of this instance.
     */
    public void setInsuranceNumber(String insuranceNumber)
    {
        _model.setInsuranceNumber(insuranceNumber);
    }

    /**
     * Gets the forename of this instance.
     * 
     * @return the forename currently set for this instance.
     */
    public String getForename()
    {
        return _model.getForename();
    }

    /**
     * Sets the forename of this instance.
     * 
     * @param forename the new forename of this instance.
     */
    public void setForename(String forename)
    {
        _model.setForename(forename);
    }

    /**
     * Gets the lastname of this instance.
     * 
     * @return the lastname currently set for this instance.
     */
    public String getLastname()
    {
        return _model.getLastname();
    }

    /**
     * Sets the lastname of this instance.
     * 
     * @param lastname the new lastname of this instance.
     */
    public void setLastname(String lastname)
    {
        _model.setLastname(lastname);
    }

    /**
     * Gets the title of this instance.
     * 
     * @return the title currently set for this instance.
     */
    public String getTitle()
    {
        return _model.getTitle();
    }

    /**
     * Sets the title of this instance.
     * 
     * @param title the new title of this instance.
     */
    public void setTitle(String title)
    {
        _model.setTitle(title);
    }

    /**
     * Gets the street of this instance.
     * 
     * @return the street currently set for this instance.
     */
    public String getStreet()
    {
        return _model.getStreet();
    }

    /**
     * Sets the street of this instance.
     * 
     * @param street the new street of this instance.
     */
    public void setStreet(String street)
    {
        _model.setStreet(street);
    }

    /**
     * Gets the zip of this instance.
     * 
     * @return the zip currently set for this instance.
     */
    public String getZip()
    {
        return _model.getZip();
    }

    /**
     * Sets the zip of this instance.
     * 
     * @param zip the new zip of this instance.
     */
    public void setZip(String zip)
    {
        _model.setZip(zip);
    }

    /**
     * Gets the place of this instance.
     * 
     * @return the place currently set for this instance.
     */
    public String getPlace()
    {
        return _model.getPlace();
    }

    /**
     * Sets the place of this instance.
     * 
     * @param place the new place of this instance.
     */
    public void setPlace(String place)
    {
        _model.setPlace(place);
    }

    /**
     * Gets the country of this instance.
     * 
     * @return the country currently set for this instance.
     */
    public String getCountry()
    {
        return _model.getCountry();
    }

    /**
     * Sets the country of this instance.
     * 
     * @param country the new country of this instance.
     */
    public void setCountry(String country)
    {
        _model.setCountry(country);
    }

    /**
     * Gets the birthday of this instance.
     * 
     * @return the birthday currently set for this instance.
     */
    public Date getBirthday()
    {
        return _model.getBirthday();
    }

    /**
     * Sets the birthday of this instance.
     * 
     * @param birthday the new birthday of this instance.
     */
    public void setBirthday(Date birthday)
    {
        _model.setBirthday(birthday);
    }

    /**
     * Gets the job of this instance.
     * 
     * @return the job currently set for this instance.
     */
    public String getJob()
    {
        return _model.getJob();
    }

    /**
     * Sets the job of this instance.
     * 
     * @param job the new job of this instance.
     */
    public void setJob(String job)
    {
        _model.setJob(job);
    }

    /**
     * Gets the religion of this instance.
     * 
     * @return the religion currently set for this instance.
     */
    public String getReligion()
    {
        return _model.getReligion();
    }

    /**
     * Sets the religion of this instance.
     * 
     * @param religion the new religion of this instance.
     */
    public void setReligion(String religion)
    {
        _model.setReligion(religion);
    }

    /**
     * Gets the regime of this instance.
     * 
     * @return the regime currently set for this instance.
     */
    public String getRegime()
    {
        return _model.getRegime();
    }

    /**
     * Sets the regime of this instance.
     * 
     * @param regime the new regime of this instance.
     */
    public void setRegime(String regime)
    {
        _model.setRegime(regime);
    }

    /**
     * Gets the notice of this instance.
     * 
     * @return the notice currently set for this instance.
     */
    public Clob getNotice()
    {
        return _model.getNotice();
    }

    /**
     * Sets the notice of this instance.
     * 
     * @param notice the new notice of this instance.
     */
    public void setNotice(Clob notice)
    {
        _model.setNotice(notice);
    }

    private GenderBO _gender;

    /**
     * Gets the currently referenced Gender of this instance.
     * 
     * @return the Gender currently referenced in this Patient.
     */
    public GenderBO getGender()
    {
        if (_gender == null)
        {
            _gender = GenderBO.getForModel(_model.getGender());
        }
        return _gender;
    }

    /**
     * Sets the Gender to be referenced in this instance
     * 
     * @param gender the Gender to reference in this Patient.
     */
    public void setGender(GenderBO gender)
    {
        _gender = gender;
        _model.setGender(gender.getModel());
    }

    /**
     * Gets the illnesses of this instance.
     * 
     * @return the illnesses currently set for this instance.
     */
    public Set<String> getIllnesses()
    {
        return _model.getIllnesses();
    }

    /**
     * Sets the illnesses of this instance.
     * 
     * @param illnesses the new illnesses of this instance.
     */
    public void setIllnesses(Set<String> illnesses)
    {
        _model.setIllnesses(illnesses);
    }

    private List<FamilyAnamnesisBO> _familyanamnesis;

/**
     * Gets a list of referenced Familyanamnesis of this instance.
     * This list is cached, use {@link Patient#updateFamilyanamnesisCache()) to update this cache.
     * @return a cached list of referenced Familyanamnesis wrapped into the correct businessobject. 
     */
    public List<FamilyAnamnesisBO> getFamilyanamnesis()
    {
        if (_familyanamnesis == null)
        {
            _familyanamnesis = new ArrayList<FamilyAnamnesisBO>();
            for (FamilyAnamnesis familyanamnesis : _model.getFamilyanamnesis())
            {
                _familyanamnesis.add(new FamilyAnamnesisBO(familyanamnesis));
            }
        }
        return _familyanamnesis;
    }

    /**
     * Adds a new FamilyAnamnesis to the list of referenced familyanamnesis. The
     * cache will updated
     * 
     * @param familyanamnesis the FamilyAnamnesis to add.
     */
    public void addFamilyanamnesis(FamilyAnamnesisBO familyanamnesis)
    {
        getFamilyanamnesis().add(familyanamnesis);
        _model.getFamilyanamnesis().add(familyanamnesis.getModel());
    }

    /**
     * Removes the given FamilyAnamnesis from the list of referenced
     * familyanamnesis. The cache will updated
     * 
     * @param familyanamnesis the timespan to add.
     */
    public void removeFamilyanamnesis(FamilyAnamnesisBO familyanamnesis)
    {
        getFamilyanamnesis().remove(familyanamnesis);
        _model.getFamilyanamnesis().remove(familyanamnesis.getModel());
    }

    /**
     * Rebuilds the cache for referenced familyanamnesis.
     */
    public void updateFamilyanamnesisCache()
    {
        if(_familyanamnesis == null) return;
        _familyanamnesis = null;
        getFamilyanamnesis();
    }

    private List<PatientStateBO> _patientStates;

/**
     * Gets a list of referenced PatientStates of this instance.
     * This list is cached, use {@link Patient#updatePatientStatesCache()) to update this cache.
     * @return a cached list of referenced PatientStates wrapped into the correct businessobject. 
     */
    public List<PatientStateBO> getPatientStates()
    {
        if (_patientStates == null)
        {
            _patientStates = new ArrayList<PatientStateBO>();
            for (PatientState patientStates : _model.getPatientStates())
            {
                _patientStates.add(new PatientStateBO(patientStates));
            }
        }
        return _patientStates;
    }

    /**
     * Adds a new PatientState to the list of referenced patientStates. The
     * cache will updated
     * 
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
     * 
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
        if(_patientStates == null) return;
        _patientStates = null;
        getPatientStates();
    }

    private List<LaborReportBO> _laborReports;

/**
     * Gets a list of referenced LaborReports of this instance.
     * This list is cached, use {@link Patient#updateLaborReportsCache()) to update this cache.
     * @return a cached list of referenced LaborReports wrapped into the correct businessobject. 
     */
    public List<LaborReportBO> getLaborReports()
    {
        if (_laborReports == null)
        {
            _laborReports = new ArrayList<LaborReportBO>();
            for (LaborReport laborReports : _model.getLaborReports())
            {
                _laborReports.add(new LaborReportBO(laborReports));
            }
        }
        return _laborReports;
    }

    /**
     * Adds a new LaborReport to the list of referenced laborReports. The cache
     * will updated
     * 
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
     * 
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
        if(_laborReports == null) return;
        _laborReports = null;
        getLaborReports();
    }

    private List<DietTreatmentBO> _treatments;

/**
     * Gets a list of referenced Treatments of this instance.
     * This list is cached, use {@link Patient#updateTreatmentsCache()) to update this cache.
     * @return a cached list of referenced Treatments wrapped into the correct businessobject. 
     */
    public List<DietTreatmentBO> getTreatments()
    {
        if (_treatments == null)
        {
            _treatments = new ArrayList<DietTreatmentBO>();
            for (DietTreatment treatments : _model.getTreatments())
            {
                _treatments.add(new DietTreatmentBO(treatments));
            }
        }
        return _treatments;
    }

    /**
     * Adds a new DietTreatment to the list of referenced treatments. The cache
     * will updated
     * 
     * @param treatments the DietTreatment to add.
     */
    public void addTreatments(DietTreatmentBO treatments)
    {
        getTreatments().add(treatments);
        _model.getTreatments().add(treatments.getModel());
    }

    /**
     * Removes the given DietTreatment from the list of referenced treatments.
     * The cache will updated
     * 
     * @param treatments the timespan to add.
     */
    public void removeTreatments(DietTreatmentBO treatments)
    {
        getTreatments().remove(treatments);
        _model.getTreatments().remove(treatments.getModel());
    }

    /**
     * Rebuilds the cache for referenced treatments.
     */
    public void updateTreatmentsCache()
    {
        if(_treatments == null) return;
       _treatments = null;
        getTreatments();
    }

    private List<PatientLikeBO> _likes;
    
    /**
     * Gets a list of referenced Likes of this instance.
     * This list is cached, use {@link Patient#updateLikesCache()) to update this cache.
     * @return a cached list of referenced Likes wrapped into the correct businessobject. 
     */
    public List<PatientLikeBO> getLikes()
    {
        if(_likes == null) 
        {
            _likes = new ArrayList<PatientLikeBO>();
            for(PatientLike likes : _model.getLikes())
            {
                _likes.add(new PatientLikeBO(likes));
            }
        }
        return _likes;
    }
    
    /**
     * Adds a new PatientLike to the list of referenced likes.
     * The cache will updated
     * @param likes the PatientLike to add. 
     */
    public void addLikes(PatientLikeBO likes)
    {
        likes.setPatient(this);
        getLikes().add(likes);
        _model.getLikes().add(likes.getModel());
    }
    
        
    /**
     * Removes the given PatientLike from the list of referenced likes.
     * The cache will updated
     * @param likes the timespan to add. 
     */
    public void removeLikes(PatientLikeBO likes)
    {
        getLikes().remove(likes);
        _model.getLikes().remove(likes.getModel());
    }
    
    /**
     * Rebuilds the cache for referenced likes.
     */
    public void updateLikesCache()
    {
        if(_likes == null) return;
        _likes = null;
        getLikes();
    }
    
    public String getDisplayName()
    {
        return String.format("%s %s (SvNr. %s)", getForename(), getLastname(),
                getInsuranceNumber());
    }

    public ArrayList<DietTreatmentBO> getTreatmentBOs()
    {
        ArrayList<DietTreatmentBO> source = new ArrayList<DietTreatmentBO>();
        for (DietTreatment treatment : _model.getTreatments())
        {
            source.add(new DietTreatmentBO(treatment));
        }
        return source;
    }

    public void setModel(Patient model)
    {
        _model = model;
        updateFamilyanamnesisCache();
        updateLaborReportsCache();
        updateLikesCache();
        updatePatientStatesCache();
        updateTreatmentsCache();

    }

}