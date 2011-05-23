package at.easydiet.businessobjects;

import at.easydiet.model.PatientLike;
import at.easydiet.model.PatientLikeGrade;

/**
 * This class encapsules a PatientLike instance.
 */
public class PatientLikeBO 
{
	private PatientLike _model;
	
    /**
     * Initializes a new instance of the {@link PatientLikeBO} class.
     */
	public PatientLikeBO()
	{
        this(new PatientLike(null, "", new PatientLikeGrade(), ""));
	}
	
    /**
     * Initializes a new instance of the {@link PatientLikeBO} class.
     * @param model the original model object
     */
	public PatientLikeBO(PatientLike model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link PatientLike} object.
	 */
 	public PatientLike getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the patientLikeId of this instance. 
     * @return the patientLikeId currently set for this instance.
     */
    public long getPatientLikeId() 
    {
        return _model.getPatientLikeId();
    }
    
    /**       
     * Sets the patientLikeId of this instance. 
     * @param patientLikeId the new patientLikeId of this instance.
     */    
    public void setPatientLikeId(long patientLikeId) 
    {
        _model.setPatientLikeId(patientLikeId);
    }

	
    private PatientBO _patient;
    
    /**
     * Gets the currently referenced Patient of this instance.
     * @return the Patient currently referenced in this PatientLike. 
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
     * @param patient the Patient to reference in this PatientLike. 
     */
    public void setPatient(PatientBO patient)
    {
        _patient = patient;
        _model.setPatient(patient.getModel());
    }
    /**       
     * Gets the blsPattern of this instance. 
     * @return the blsPattern currently set for this instance.
     */
    public String getBlsPattern() 
    {
        return _model.getBlsPattern();
    }
    
    /**       
     * Sets the blsPattern of this instance. 
     * @param blsPattern the new blsPattern of this instance.
     */    
    public void setBlsPattern(String blsPattern) 
    {
        _model.setBlsPattern(blsPattern);
    }

	
    private PatientLikeGradeBO _grade;
    
    /**
     * Gets the currently referenced Grade of this instance.
     * @return the PatientLikeGrade currently referenced in this PatientLike. 
     */
    public PatientLikeGradeBO getGrade()
    {
        if(_grade == null)
        {
            _grade = new PatientLikeGradeBO(_model.getGrade());
        }
        return _grade;
    }
    
    /**
     * Sets the Grade to be referenced in this instance
     * @param grade the PatientLikeGrade to reference in this PatientLike. 
     */
    public void setGrade(PatientLikeGradeBO grade)
    {
        _grade = grade;
        _model.setGrade(grade.getModel());
    }
    /**       
     * Gets the notice of this instance. 
     * @return the notice currently set for this instance.
     */
    public String getNotice() 
    {
        return _model.getNotice();
    }
    
    /**       
     * Sets the notice of this instance. 
     * @param notice the new notice of this instance.
     */    
    public void setNotice(String notice) 
    {
        _model.setNotice(notice);
    }

}