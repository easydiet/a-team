package at.easydiet.businessobjects;

import at.easydiet.model.PatientLike;

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
		this(new PatientLike("", 0, ""));
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

    /**       
     * Gets the grade of this instance. 
     * @return the grade currently set for this instance.
     */
    public int getGrade() 
    {
        return _model.getGrade();
    }
    
    /**       
     * Sets the grade of this instance. 
     * @param grade the new grade of this instance.
     */    
    public void setGrade(int grade) 
    {
        _model.setGrade(grade);
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