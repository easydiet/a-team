package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;

import at.easydiet.model.ContactJournal;

/**
 * This class encapsules a ContactJournal instance.
 */
public class ContactJournalBO 
{
	private ContactJournal _model;
	
    /**
     * Initializes a new instance of the {@link ContactJournalBO} class.
     */
	public ContactJournalBO()
	{
		// TODO: add default values
		this(new ContactJournal());
	}
	
    /**
     * Initializes a new instance of the {@link ContactJournalBO} class.
     * @param model the original model object
     */
	public ContactJournalBO(ContactJournal model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link ContactJournal} object.
	 */
 	public ContactJournal getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the contactJournalId of this instance. 
     * @return the contactJournalId currently set for this instance.
     */
    public long getContactJournalId() 
    {
        return _model.getContactJournalId();
    }
    
    /**       
     * Sets the contactJournalId of this instance. 
     * @param contactJournalId the new contactJournalId of this instance.
     */    
    public void setContactJournalId(long contactJournalId) 
    {
        _model.setContactJournalId(contactJournalId);
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
     * Gets the description of this instance. 
     * @return the description currently set for this instance.
     */
    public Clob getDescription() 
    {
        return _model.getDescription();
    }
    
    /**       
     * Sets the description of this instance. 
     * @param description the new description of this instance.
     */    
    public void setDescription(Clob description) 
    {
        _model.setDescription(description);
    }

	
    private SystemUserBO _createdBy;
    
    /**
     * Gets the currently referenced CreatedBy of this instance.
     * @return the SystemUser currently referenced in this ContactJournal. 
     */
    public SystemUserBO getCreatedBy()
    {
        if(_createdBy == null)
        {
            _createdBy = new SystemUserBO(_model.getCreatedBy());
        }
        return _createdBy;
    }
    
    /**
     * Sets the CreatedBy to be referenced in this instance
     * @param createdBy the SystemUser to reference in this ContactJournal. 
     */
    public void setCreatedBy(SystemUserBO createdBy)
    {
        _createdBy = createdBy;
        _model.setCreatedBy(createdBy.getModel());
    }
	
    private ContactTypeBO _contactType;
    
    /**
     * Gets the currently referenced ContactType of this instance.
     * @return the ContactType currently referenced in this ContactJournal. 
     */
    public ContactTypeBO getContactType()
    {
        if(_contactType == null)
        {
            _contactType = ContactTypeBO.getForModel(_model.getContactType());
        }
        return _contactType;
    }
    
    /**
     * Sets the ContactType to be referenced in this instance
     * @param contactType the ContactType to reference in this ContactJournal. 
     */
    public void setContactType(ContactTypeBO contactType)
    {
        _contactType = contactType;
        _model.setContactType(contactType.getModel());
    }
	
    private DietTreatmentBO _dietTreatment;
    
    /**
     * Gets the currently referenced DietTreatment of this instance.
     * @return the DietTreatment currently referenced in this ContactJournal. 
     */
    public DietTreatmentBO getDietTreatment()
    {
        if(_dietTreatment == null)
        {
            _dietTreatment = new DietTreatmentBO(_model.getDietTreatment());
        }
        return _dietTreatment;
    }
    
    /**
     * Sets the DietTreatment to be referenced in this instance
     * @param dietTreatment the DietTreatment to reference in this ContactJournal. 
     */
    public void setDietTreatment(DietTreatmentBO dietTreatment)
    {
        _dietTreatment = dietTreatment;
        _model.setDietTreatment(dietTreatment.getModel());
    }
}