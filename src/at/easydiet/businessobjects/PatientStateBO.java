package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;
import java.util.Set;

import at.easydiet.model.LaborReport;
import at.easydiet.model.PatientState;
import at.easydiet.model.PatientStateType;
import at.easydiet.model.SystemUser;

public class PatientStateBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientStateBO.class);

    private PatientState _patientState;

    /**
     * Gets the patientState.
     * @return the patientState
     */
    public PatientState getPatientState()
    {
        return _patientState;
    }

    /** 
     * Initializes a new instance of the {@link PatientStateBO} class. 
     * @param patientState
     */
    public PatientStateBO(PatientState patientState)
    {
        super();
        _patientState = patientState;
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getPatientStateId()
     */
    public long getPatientStateId()
    {
        return _patientState.getPatientStateId();
    }

    /**
     * @param patientStateId
     * @see at.easydiet.model.PatientState#setPatientStateId(long)
     */
    public void setPatientStateId(long patientStateId)
    {
        _patientState.setPatientStateId(patientStateId);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getDate()
     */
    public Date getDate()
    {
        return _patientState.getDate();
    }

    /**
     * @param date
     * @see at.easydiet.model.PatientState#setDate(java.util.Date)
     */
    public void setDate(Date date)
    {
        _patientState.setDate(date);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getAnamnesis()
     */
    public Clob getAnamnesis()
    {
        return _patientState.getAnamnesis();
    }

    /**
     * @param anamnesis
     * @see at.easydiet.model.PatientState#setAnamnesis(java.sql.Clob)
     */
    public void setAnamnesis(Clob anamnesis)
    {
        _patientState.setAnamnesis(anamnesis);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getWeight()
     */
    public Integer getWeight()
    {
        return _patientState.getWeight();
    }

    /**
     * @param weight
     * @see at.easydiet.model.PatientState#setWeight(java.lang.Integer)
     */
    public void setWeight(Integer weight)
    {
        _patientState.setWeight(weight);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getWeightPercentile()
     */
    public float getWeightPercentile()
    {
        return _patientState.getWeightPercentile();
    }

    /**
     * @param weightPercentile
     * @see at.easydiet.model.PatientState#setWeightPercentile(float)
     */
    public void setWeightPercentile(float weightPercentile)
    {
        _patientState.setWeightPercentile(weightPercentile);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getHeight()
     */
    public Integer getHeight()
    {
        return _patientState.getHeight();
    }

    /**
     * @param height
     * @see at.easydiet.model.PatientState#setHeight(java.lang.Integer)
     */
    public void setHeight(Integer height)
    {
        _patientState.setHeight(height);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getHeightPercentile()
     */
    public float getHeightPercentile()
    {
        return _patientState.getHeightPercentile();
    }

    /**
     * @param heightPercentile
     * @see at.easydiet.model.PatientState#setHeightPercentile(float)
     */
    public void setHeightPercentile(float heightPercentile)
    {
        _patientState.setHeightPercentile(heightPercentile);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getCompliance()
     */
    public int getCompliance()
    {
        return _patientState.getCompliance();
    }

    /**
     * @param compliance
     * @see at.easydiet.model.PatientState#setCompliance(int)
     */
    public void setCompliance(int compliance)
    {
        _patientState.setCompliance(compliance);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getMotivation()
     */
    public int getMotivation()
    {
        return _patientState.getMotivation();
    }

    /**
     * @param motivation
     * @see at.easydiet.model.PatientState#setMotivation(int)
     */
    public void setMotivation(int motivation)
    {
        _patientState.setMotivation(motivation);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getType()
     */
    public PatientStateType getType()
    {
        return _patientState.getType();
    }

    /**
     * @param type
     * @see at.easydiet.model.PatientState#setType(at.easydiet.model.PatientStateType)
     */
    public void setType(PatientStateType type)
    {
        _patientState.setType(type);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getCreator()
     */
    public SystemUser getCreator()
    {
        return _patientState.getCreator();
    }

    /**
     * @param creator
     * @see at.easydiet.model.PatientState#setCreator(at.easydiet.model.SystemUser)
     */
    public void setCreator(SystemUser creator)
    {
        _patientState.setCreator(creator);
    }

    /**
     * @return
     * @see at.easydiet.model.PatientState#getLaborReports()
     */
    public Set<LaborReport> getLaborReports()
    {
        return _patientState.getLaborReports();
    }

    /**
     * @param laborReports
     * @see at.easydiet.model.PatientState#setLaborReports(java.util.Set)
     */
    public void setLaborReports(Set<LaborReport> laborReports)
    {
        _patientState.setLaborReports(laborReports);
    }
    
    
}
