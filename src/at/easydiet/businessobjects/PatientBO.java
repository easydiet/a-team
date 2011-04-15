package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;
import java.util.Set;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.DietTreatment;
import at.easydiet.model.FamilyAnamnesis;
import at.easydiet.model.Gender;
import at.easydiet.model.LaborReport;
import at.easydiet.model.Patient;
import at.easydiet.model.PatientState;
import at.easydiet.model.Recipe;

public class PatientBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientBO.class);

    private Patient                             _patient;

    /**
     * Initializes a new instance of the {@link PatientBO} class.
     * @param patient
     */
    public PatientBO(Patient patient)
    {
        super();
        _patient = patient;
    }

    /**
     * Gets the patient.
     * @return the patient
     */
    public Patient getPatient()
    {
        return _patient;
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getPatientId()
     */
    public long getPatientId()
    {
        return _patient.getPatientId();
    }

    /**
     * @param patientId
     * @see at.easydiet.model.Patient#setPatientId(long)
     */
    public void setPatientId(long patientId)
    {
        _patient.setPatientId(patientId);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getInsuranceNumber()
     */
    public String getInsuranceNumber()
    {
        return _patient.getInsuranceNumber();
    }

    /**
     * @param insuranceNumber
     * @see at.easydiet.model.Patient#setInsuranceNumber(java.lang.String)
     */
    public void setInsuranceNumber(String insuranceNumber)
    {
        _patient.setInsuranceNumber(insuranceNumber);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getForename()
     */
    public String getForename()
    {
        return _patient.getForename();
    }

    /**
     * @param forename
     * @see at.easydiet.model.Patient#setForename(java.lang.String)
     */
    public void setForename(String forename)
    {
        _patient.setForename(forename);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getLastname()
     */
    public String getLastname()
    {
        return _patient.getLastname();
    }

    /**
     * @param lastname
     * @see at.easydiet.model.Patient#setLastname(java.lang.String)
     */
    public void setLastname(String lastname)
    {
        _patient.setLastname(lastname);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getTitle()
     */
    public String getTitle()
    {
        return _patient.getTitle();
    }

    /**
     * @param title
     * @see at.easydiet.model.Patient#setTitle(java.lang.String)
     */
    public void setTitle(String title)
    {
        _patient.setTitle(title);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getStreet()
     */
    public String getStreet()
    {
        return _patient.getStreet();
    }

    /**
     * @param street
     * @see at.easydiet.model.Patient#setStreet(java.lang.String)
     */
    public void setStreet(String street)
    {
        _patient.setStreet(street);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getZip()
     */
    public String getZip()
    {
        return _patient.getZip();
    }

    /**
     * @param zip
     * @see at.easydiet.model.Patient#setZip(java.lang.String)
     */
    public void setZip(String zip)
    {
        _patient.setZip(zip);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getPlace()
     */
    public String getPlace()
    {
        return _patient.getPlace();
    }

    /**
     * @param place
     * @see at.easydiet.model.Patient#setPlace(java.lang.String)
     */
    public void setPlace(String place)
    {
        _patient.setPlace(place);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getCountry()
     */
    public String getCountry()
    {
        return _patient.getCountry();
    }

    /**
     * @param country
     * @see at.easydiet.model.Patient#setCountry(java.lang.String)
     */
    public void setCountry(String country)
    {
        _patient.setCountry(country);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getBirthday()
     */
    public Date getBirthday()
    {
        return _patient.getBirthday();
    }

    /**
     * @param birthday
     * @see at.easydiet.model.Patient#setBirthday(java.util.Date)
     */
    public void setBirthday(Date birthday)
    {
        _patient.setBirthday(birthday);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getJob()
     */
    public String getJob()
    {
        return _patient.getJob();
    }

    /**
     * @param job
     * @see at.easydiet.model.Patient#setJob(java.lang.String)
     */
    public void setJob(String job)
    {
        _patient.setJob(job);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getReligion()
     */
    public String getReligion()
    {
        return _patient.getReligion();
    }

    /**
     * @param religion
     * @see at.easydiet.model.Patient#setReligion(java.lang.String)
     */
    public void setReligion(String religion)
    {
        _patient.setReligion(religion);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getRegime()
     */
    public String getRegime()
    {
        return _patient.getRegime();
    }

    /**
     * @param regime
     * @see at.easydiet.model.Patient#setRegime(java.lang.String)
     */
    public void setRegime(String regime)
    {
        _patient.setRegime(regime);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getNotice()
     */
    public Clob getNotice()
    {
        return _patient.getNotice();
    }

    /**
     * @param notice
     * @see at.easydiet.model.Patient#setNotice(java.sql.Clob)
     */
    public void setNotice(Clob notice)
    {
        _patient.setNotice(notice);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getGender()
     */
    public Gender getGender()
    {
        return _patient.getGender();
    }

    /**
     * @param gender
     * @see at.easydiet.model.Patient#setGender(at.easydiet.model.Gender)
     */
    public void setGender(Gender gender)
    {
        _patient.setGender(gender);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getIllnesses()
     */
    public Set<String> getIllnesses()
    {
        return _patient.getIllnesses();
    }

    /**
     * @param illnesses
     * @see at.easydiet.model.Patient#setIllnesses(java.util.Set)
     */
    public void setIllnesses(Set<String> illnesses)
    {
        _patient.setIllnesses(illnesses);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getFamilyanamnesis()
     */
    public Set<FamilyAnamnesis> getFamilyanamnesis()
    {
        return _patient.getFamilyanamnesis();
    }

    /**
     * @param familyanamnesis
     * @see at.easydiet.model.Patient#setFamilyanamnesis(java.util.Set)
     */
    public void setFamilyanamnesis(Set<FamilyAnamnesis> familyanamnesis)
    {
        _patient.setFamilyanamnesis(familyanamnesis);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getPatientStates()
     */
    public Set<PatientState> getPatientStates()
    {
        return _patient.getPatientStates();
    }

    /**
     * @param patientStates
     * @see at.easydiet.model.Patient#setPatientStates(java.util.Set)
     */
    public void setPatientStates(Set<PatientState> patientStates)
    {
        _patient.setPatientStates(patientStates);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getLaborReports()
     */
    public Set<LaborReport> getLaborReports()
    {
        return _patient.getLaborReports();
    }

    /**
     * @param laborReports
     * @see at.easydiet.model.Patient#setLaborReports(java.util.Set)
     */
    public void setLaborReports(Set<LaborReport> laborReports)
    {
        _patient.setLaborReports(laborReports);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getTreatments()
     */
    public Set<DietTreatment> getTreatments()
    {
        return _patient.getTreatments();
    }
    
    /**
     * @return a new list of {@link DietTreatmentBO} according to {@link PatientBO#getTreatments()}
     * @see at.easydiet.model.Patient#getTreatments()
     */
    public ArrayList<DietTreatmentBO> getTreatmentBOs()
    {
        ArrayList<DietTreatmentBO> bos = new ArrayList<DietTreatmentBO>();
        for (DietTreatment treatment : getTreatments())
        {
            bos.add(new DietTreatmentBO(treatment));
        }
        return bos;
    }

    /**
     * @param treatments
     * @see at.easydiet.model.Patient#setTreatments(java.util.Set)
     */
    public void setTreatments(Set<DietTreatment> treatments)
    {
        _patient.setTreatments(treatments);
    }

    /**
     * @return
     * @see at.easydiet.model.Patient#getDisfavors()
     */
    public Set<Recipe> getDisfavors()
    {
        return _patient.getDisfavors();
    }

    /**
     * @param disfavors
     * @see at.easydiet.model.Patient#setDisfavors(java.util.Set)
     */
    public void setDisfavors(Set<Recipe> disfavors)
    {
        _patient.setDisfavors(disfavors);
    }

}
