package at.easydiet.model;

import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Patient
 */
public class Patient  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 4195729659042326768L;
    private long _patientId;
    private String _insuranceNumber;
    private String _forename;
    private String _lastname;
    private String _title;
    private String _street;
    private String _zip;
    private String _place;
    private String _country;
    private Date _birthday;
    private String _job;
    private String _religion;
    private Clob _regime;
    private Clob _notice;
    private Gender _gender;
    private FamilyStatus _familyStatus;
    private Set<Illness> _illnesses = new HashSet<Illness>(0);
    private Set<FamilyAnamnesis> _familyanamnesis = new HashSet<FamilyAnamnesis>(0);
    private Set<PatientState> _patientStates = new HashSet<PatientState>(0);
    private Set<LaborReport> _laborReports = new HashSet<LaborReport>(0);
    private Set<DietTreatment> _treatments = new HashSet<DietTreatment>(0);
    private Set<PatientLike> _likes = new HashSet<PatientLike>(0);

    /**
     * Initializes a new instance of the {@link Patient} class.
     */
    public Patient() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link Patient} class.
     * @param forename the forename to set for this instance
     * @param lastname the lastname to set for this instance
     * @param gender the gender to set for this instance
     */
    public Patient(String forename, String lastname, Gender gender) 
    {
        _forename = forename;
        _lastname = lastname;
        _gender = gender;
    }

    /**
     * Initializes a new instance of the {@link Patient} class.
     * @param insuranceNumber the insuranceNumber to set for this instance
     * @param forename the forename to set for this instance
     * @param lastname the lastname to set for this instance
     * @param title the title to set for this instance
     * @param street the street to set for this instance
     * @param zip the zip to set for this instance
     * @param place the place to set for this instance
     * @param country the country to set for this instance
     * @param birthday the birthday to set for this instance
     * @param job the job to set for this instance
     * @param religion the religion to set for this instance
     * @param regime the regime to set for this instance
     * @param notice the notice to set for this instance
     * @param gender the gender to set for this instance
     * @param familyStatus the familyStatus to set for this instance
     * @param illnesses the illnesses to set for this instance
     * @param familyanamnesis the familyanamnesis to set for this instance
     * @param patientStates the patientStates to set for this instance
     * @param laborReports the laborReports to set for this instance
     * @param treatments the treatments to set for this instance
     * @param likes the likes to set for this instance
     */
    public Patient(String insuranceNumber, String forename, String lastname, String title, String street, String zip, String place, String country, Date birthday, String job, String religion, Clob regime, Clob notice, Gender gender, FamilyStatus familyStatus, Set<Illness> illnesses, Set<FamilyAnamnesis> familyanamnesis, Set<PatientState> patientStates, Set<LaborReport> laborReports, Set<DietTreatment> treatments, Set<PatientLike> likes) 
    {
       _insuranceNumber = insuranceNumber;
       _forename = forename;
       _lastname = lastname;
       _title = title;
       _street = street;
       _zip = zip;
       _place = place;
       _country = country;
       _birthday = birthday;
       _job = job;
       _religion = religion;
       _regime = regime;
       _notice = notice;
       _gender = gender;
       _familyStatus = familyStatus;
       _illnesses = illnesses;
       _familyanamnesis = familyanamnesis;
       _patientStates = patientStates;
       _laborReports = laborReports;
       _treatments = treatments;
       _likes = likes;
    }
   
    /**       
     * Gets the patientId of this instance. 
     * @return the patientId currently set for this instance.
     */
    public long getPatientId() 
    {
        return _patientId;
    }
    
    /**       
     * Sets the patientId of this instance. 
     * @param patientId the new patientId of this instance.
     */    
    public void setPatientId(long patientId) 
    {
        _patientId = patientId;
    }
    
    /**       
     * Gets the insuranceNumber of this instance. 
     * @return the insuranceNumber currently set for this instance.
     */
    public String getInsuranceNumber() 
    {
        return _insuranceNumber;
    }
    
    /**       
     * Sets the insuranceNumber of this instance. 
     * @param insuranceNumber the new insuranceNumber of this instance.
     */    
    public void setInsuranceNumber(String insuranceNumber) 
    {
        _insuranceNumber = insuranceNumber;
    }
    
    /**       
     * Gets the forename of this instance. 
     * @return the forename currently set for this instance.
     */
    public String getForename() 
    {
        return _forename;
    }
    
    /**       
     * Sets the forename of this instance. 
     * @param forename the new forename of this instance.
     */    
    public void setForename(String forename) 
    {
        _forename = forename;
    }
    
    /**       
     * Gets the lastname of this instance. 
     * @return the lastname currently set for this instance.
     */
    public String getLastname() 
    {
        return _lastname;
    }
    
    /**       
     * Sets the lastname of this instance. 
     * @param lastname the new lastname of this instance.
     */    
    public void setLastname(String lastname) 
    {
        _lastname = lastname;
    }
    
    /**       
     * Gets the title of this instance. 
     * @return the title currently set for this instance.
     */
    public String getTitle() 
    {
        return _title;
    }
    
    /**       
     * Sets the title of this instance. 
     * @param title the new title of this instance.
     */    
    public void setTitle(String title) 
    {
        _title = title;
    }
    
    /**       
     * Gets the street of this instance. 
     * @return the street currently set for this instance.
     */
    public String getStreet() 
    {
        return _street;
    }
    
    /**       
     * Sets the street of this instance. 
     * @param street the new street of this instance.
     */    
    public void setStreet(String street) 
    {
        _street = street;
    }
    
    /**       
     * Gets the zip of this instance. 
     * @return the zip currently set for this instance.
     */
    public String getZip() 
    {
        return _zip;
    }
    
    /**       
     * Sets the zip of this instance. 
     * @param zip the new zip of this instance.
     */    
    public void setZip(String zip) 
    {
        _zip = zip;
    }
    
    /**       
     * Gets the place of this instance. 
     * @return the place currently set for this instance.
     */
    public String getPlace() 
    {
        return _place;
    }
    
    /**       
     * Sets the place of this instance. 
     * @param place the new place of this instance.
     */    
    public void setPlace(String place) 
    {
        _place = place;
    }
    
    /**       
     * Gets the country of this instance. 
     * @return the country currently set for this instance.
     */
    public String getCountry() 
    {
        return _country;
    }
    
    /**       
     * Sets the country of this instance. 
     * @param country the new country of this instance.
     */    
    public void setCountry(String country) 
    {
        _country = country;
    }
    
    /**       
     * Gets the birthday of this instance. 
     * @return the birthday currently set for this instance.
     */
    public Date getBirthday() 
    {
        return _birthday;
    }
    
    /**       
     * Sets the birthday of this instance. 
     * @param birthday the new birthday of this instance.
     */    
    public void setBirthday(Date birthday) 
    {
        _birthday = birthday;
    }
    
    /**       
     * Gets the job of this instance. 
     * @return the job currently set for this instance.
     */
    public String getJob() 
    {
        return _job;
    }
    
    /**       
     * Sets the job of this instance. 
     * @param job the new job of this instance.
     */    
    public void setJob(String job) 
    {
        _job = job;
    }
    
    /**       
     * Gets the religion of this instance. 
     * @return the religion currently set for this instance.
     */
    public String getReligion() 
    {
        return _religion;
    }
    
    /**       
     * Sets the religion of this instance. 
     * @param religion the new religion of this instance.
     */    
    public void setReligion(String religion) 
    {
        _religion = religion;
    }
    
    /**       
     * Gets the regime of this instance. 
     * @return the regime currently set for this instance.
     */
    public Clob getRegime() 
    {
        return _regime;
    }
    
    /**       
     * Sets the regime of this instance. 
     * @param regime the new regime of this instance.
     */    
    public void setRegime(Clob regime) 
    {
        _regime = regime;
    }
    
    /**       
     * Gets the notice of this instance. 
     * @return the notice currently set for this instance.
     */
    public Clob getNotice() 
    {
        return _notice;
    }
    
    /**       
     * Sets the notice of this instance. 
     * @param notice the new notice of this instance.
     */    
    public void setNotice(Clob notice) 
    {
        _notice = notice;
    }
    
    /**       
     * Gets the gender of this instance. 
     * @return the gender currently set for this instance.
     */
    public Gender getGender() 
    {
        return _gender;
    }
    
    /**       
     * Sets the gender of this instance. 
     * @param gender the new gender of this instance.
     */    
    public void setGender(Gender gender) 
    {
        _gender = gender;
    }
    
    /**       
     * Gets the familyStatus of this instance. 
     * @return the familyStatus currently set for this instance.
     */
    public FamilyStatus getFamilyStatus() 
    {
        return _familyStatus;
    }
    
    /**       
     * Sets the familyStatus of this instance. 
     * @param familyStatus the new familyStatus of this instance.
     */    
    public void setFamilyStatus(FamilyStatus familyStatus) 
    {
        _familyStatus = familyStatus;
    }
    
    /**       
     * Gets the illnesses of this instance. 
     * @return the illnesses currently set for this instance.
     */
    public Set<Illness> getIllnesses() 
    {
        return _illnesses;
    }
    
    /**       
     * Sets the illnesses of this instance. 
     * @param illnesses the new illnesses of this instance.
     */    
    public void setIllnesses(Set<Illness> illnesses) 
    {
        _illnesses = illnesses;
    }
    
    /**       
     * Gets the familyanamnesis of this instance. 
     * @return the familyanamnesis currently set for this instance.
     */
    public Set<FamilyAnamnesis> getFamilyanamnesis() 
    {
        return _familyanamnesis;
    }
    
    /**       
     * Sets the familyanamnesis of this instance. 
     * @param familyanamnesis the new familyanamnesis of this instance.
     */    
    public void setFamilyanamnesis(Set<FamilyAnamnesis> familyanamnesis) 
    {
        _familyanamnesis = familyanamnesis;
    }
    
    /**       
     * Gets the patientStates of this instance. 
     * @return the patientStates currently set for this instance.
     */
    public Set<PatientState> getPatientStates() 
    {
        return _patientStates;
    }
    
    /**       
     * Sets the patientStates of this instance. 
     * @param patientStates the new patientStates of this instance.
     */    
    public void setPatientStates(Set<PatientState> patientStates) 
    {
        _patientStates = patientStates;
    }
    
    /**       
     * Gets the laborReports of this instance. 
     * @return the laborReports currently set for this instance.
     */
    public Set<LaborReport> getLaborReports() 
    {
        return _laborReports;
    }
    
    /**       
     * Sets the laborReports of this instance. 
     * @param laborReports the new laborReports of this instance.
     */    
    public void setLaborReports(Set<LaborReport> laborReports) 
    {
        _laborReports = laborReports;
    }
    
    /**       
     * Gets the treatments of this instance. 
     * @return the treatments currently set for this instance.
     */
    public Set<DietTreatment> getTreatments() 
    {
        return _treatments;
    }
    
    /**       
     * Sets the treatments of this instance. 
     * @param treatments the new treatments of this instance.
     */    
    public void setTreatments(Set<DietTreatment> treatments) 
    {
        _treatments = treatments;
    }
    
    /**       
     * Gets the likes of this instance. 
     * @return the likes currently set for this instance.
     */
    public Set<PatientLike> getLikes() 
    {
        return _likes;
    }
    
    /**       
     * Sets the likes of this instance. 
     * @param likes the new likes of this instance.
     */    
    public void setLikes(Set<PatientLike> likes) 
    {
        _likes = likes;
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
