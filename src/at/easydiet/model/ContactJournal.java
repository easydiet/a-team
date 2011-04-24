package at.easydiet.model;

import java.sql.Clob;
import java.util.Date;

/**
 * Represents a ContactJournal
 */
public class ContactJournal  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 4121628204567852781L;
    private long _contactJournalId;
    private Date _date;
    private Clob _description;
    private SystemUser _createdBy;
    private ContactType _contactType;
    private DietTreatment _dietTreatment;

    /**
     * Initializes a new instance of the {@link ContactJournal} class.
     */
    public ContactJournal() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link ContactJournal} class.
     * @param date the date to set for this instance
     * @param createdBy the createdBy to set for this instance
     * @param contactType the contactType to set for this instance
     * @param dietTreatment the dietTreatment to set for this instance
     */
    public ContactJournal(Date date, SystemUser createdBy, ContactType contactType, DietTreatment dietTreatment) 
    {
        _date = date;
        _createdBy = createdBy;
        _contactType = contactType;
        _dietTreatment = dietTreatment;
    }

    /**
     * Initializes a new instance of the {@link ContactJournal} class.
     * @param date the date to set for this instance
     * @param description the description to set for this instance
     * @param createdBy the createdBy to set for this instance
     * @param contactType the contactType to set for this instance
     * @param dietTreatment the dietTreatment to set for this instance
     */
    public ContactJournal(Date date, Clob description, SystemUser createdBy, ContactType contactType, DietTreatment dietTreatment) 
    {
       _date = date;
       _description = description;
       _createdBy = createdBy;
       _contactType = contactType;
       _dietTreatment = dietTreatment;
    }
   
    /**       
     * Gets the contactJournalId of this instance. 
     * @return the contactJournalId currently set for this instance.
     */
    public long getContactJournalId() 
    {
        return _contactJournalId;
    }
    
    /**       
     * Sets the contactJournalId of this instance. 
     * @param contactJournalId the new contactJournalId of this instance.
     */    
    public void setContactJournalId(long contactJournalId) 
    {
        _contactJournalId = contactJournalId;
    }
    
    /**       
     * Gets the date of this instance. 
     * @return the date currently set for this instance.
     */
    public Date getDate() 
    {
        return _date;
    }
    
    /**       
     * Sets the date of this instance. 
     * @param date the new date of this instance.
     */    
    public void setDate(Date date) 
    {
        _date = date;
    }
    
    /**       
     * Gets the description of this instance. 
     * @return the description currently set for this instance.
     */
    public Clob getDescription() 
    {
        return _description;
    }
    
    /**       
     * Sets the description of this instance. 
     * @param description the new description of this instance.
     */    
    public void setDescription(Clob description) 
    {
        _description = description;
    }
    
    /**       
     * Gets the createdBy of this instance. 
     * @return the createdBy currently set for this instance.
     */
    public SystemUser getCreatedBy() 
    {
        return _createdBy;
    }
    
    /**       
     * Sets the createdBy of this instance. 
     * @param createdBy the new createdBy of this instance.
     */    
    public void setCreatedBy(SystemUser createdBy) 
    {
        _createdBy = createdBy;
    }
    
    /**       
     * Gets the contactType of this instance. 
     * @return the contactType currently set for this instance.
     */
    public ContactType getContactType() 
    {
        return _contactType;
    }
    
    /**       
     * Sets the contactType of this instance. 
     * @param contactType the new contactType of this instance.
     */    
    public void setContactType(ContactType contactType) 
    {
        _contactType = contactType;
    }
    
    /**       
     * Gets the dietTreatment of this instance. 
     * @return the dietTreatment currently set for this instance.
     */
    public DietTreatment getDietTreatment() 
    {
        return _dietTreatment;
    }
    
    /**       
     * Sets the dietTreatment of this instance. 
     * @param dietTreatment the new dietTreatment of this instance.
     */    
    public void setDietTreatment(DietTreatment dietTreatment) 
    {
        _dietTreatment = dietTreatment;
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
