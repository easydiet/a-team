package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;

import at.easydiet.model.ContactJournal;
import at.easydiet.model.ContactType;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.SystemUser;


public class ContactJournalBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ContactJournalBO.class);

    private ContactJournal _contactJournal;
    
    public ContactJournal getContactJournal()
    {
        return _contactJournal;
    }

    /** 
     * Initializes a new instance of the {@link ContactJournalBO} class. 
     * @param contactJournal
     */
    public ContactJournalBO(ContactJournal contactJournal)
    {
        super();
        _contactJournal = contactJournal;
    }
    
    

    /**
     * @return
     * @see at.easydiet.model.ContactJournal#getDietTreatment()
     */
    public DietTreatment getDietTreatment()
    {
        return _contactJournal.getDietTreatment();
    }

    /**
     * @param dietTreatment
     * @see at.easydiet.model.ContactJournal#setDietTreatment(at.easydiet.model.DietTreatment)
     */
    public void setDietTreatment(DietTreatment dietTreatment)
    {
        _contactJournal.setDietTreatment(dietTreatment);
    }

    /**
     * @return
     * @see at.easydiet.model.ContactJournal#getContactJournalId()
     */
    public long getContactJournalId()
    {
        return _contactJournal.getContactJournalId();
    }

    /**
     * @param contactJournalId
     * @see at.easydiet.model.ContactJournal#setContactJournalId(long)
     */
    public void setContactJournalId(long contactJournalId)
    {
        _contactJournal.setContactJournalId(contactJournalId);
    }

    /**
     * @return
     * @see at.easydiet.model.ContactJournal#getDate()
     */
    public Date getDate()
    {
        return _contactJournal.getDate();
    }

    /**
     * @param date
     * @see at.easydiet.model.ContactJournal#setDate(java.util.Date)
     */
    public void setDate(Date date)
    {
        _contactJournal.setDate(date);
    }

    /**
     * @return
     * @see at.easydiet.model.ContactJournal#getDescription()
     */
    public Clob getDescription()
    {
        return _contactJournal.getDescription();
    }

    /**
     * @param description
     * @see at.easydiet.model.ContactJournal#setDescription(java.sql.Clob)
     */
    public void setDescription(Clob description)
    {
        _contactJournal.setDescription(description);
    }

    /**
     * @return
     * @see at.easydiet.model.ContactJournal#getCreatedBy()
     */
    public SystemUser getCreatedBy()
    {
        return _contactJournal.getCreatedBy();
    }

    /**
     * @param createdBy
     * @see at.easydiet.model.ContactJournal#setCreatedBy(at.easydiet.model.SystemUser)
     */
    public void setCreatedBy(SystemUser createdBy)
    {
        _contactJournal.setCreatedBy(createdBy);
    }

    /**
     * @return
     * @see at.easydiet.model.ContactJournal#getContactType()
     */
    public ContactType getContactType()
    {
        return _contactJournal.getContactType();
    }

    /**
     * @param contactType
     * @see at.easydiet.model.ContactJournal#setContactType(at.easydiet.model.ContactType)
     */
    public void setContactType(ContactType contactType)
    {
        _contactJournal.setContactType(contactType);
    }
    
    
}
