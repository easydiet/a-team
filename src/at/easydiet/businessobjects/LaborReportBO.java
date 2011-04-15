package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;
import java.util.Set;

import at.easydiet.model.DietParameter;
import at.easydiet.model.LaborReport;
import at.easydiet.model.SystemUser;

public class LaborReportBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(LaborReportBO.class);

    private LaborReport _laborReport;

    /**
     * Gets the laborReport.
     * @return the laborReport
     */
    public LaborReport getLaborReport()
    {
        return _laborReport;
    }

    /** 
     * Initializes a new instance of the {@link LaborReportBO} class. 
     * @param laborReport
     */
    public LaborReportBO(LaborReport laborReport)
    {
        super();
        _laborReport = laborReport;
    }

    /**
     * @return
     * @see at.easydiet.model.LaborReport#getLaborReportId()
     */
    public long getLaborReportId()
    {
        return _laborReport.getLaborReportId();
    }

    /**
     * @param laborReportId
     * @see at.easydiet.model.LaborReport#setLaborReportId(long)
     */
    public void setLaborReportId(long laborReportId)
    {
        _laborReport.setLaborReportId(laborReportId);
    }

    /**
     * @return
     * @see at.easydiet.model.LaborReport#getDate()
     */
    public Date getDate()
    {
        return _laborReport.getDate();
    }

    /**
     * @param date
     * @see at.easydiet.model.LaborReport#setDate(java.util.Date)
     */
    public void setDate(Date date)
    {
        _laborReport.setDate(date);
    }

    /**
     * @return
     * @see at.easydiet.model.LaborReport#getNotice()
     */
    public Clob getNotice()
    {
        return _laborReport.getNotice();
    }

    /**
     * @param notice
     * @see at.easydiet.model.LaborReport#setNotice(java.sql.Clob)
     */
    public void setNotice(Clob notice)
    {
        _laborReport.setNotice(notice);
    }

    /**
     * @return
     * @see at.easydiet.model.LaborReport#getCreator()
     */
    public SystemUser getCreator()
    {
        return _laborReport.getCreator();
    }

    /**
     * @param creator
     * @see at.easydiet.model.LaborReport#setCreator(at.easydiet.model.SystemUser)
     */
    public void setCreator(SystemUser creator)
    {
        _laborReport.setCreator(creator);
    }

    /**
     * @return
     * @see at.easydiet.model.LaborReport#getDietParameters()
     */
    public Set<DietParameter> getDietParameters()
    {
        return _laborReport.getDietParameters();
    }

    /**
     * @param dietParameters
     * @see at.easydiet.model.LaborReport#setDietParameters(java.util.Set)
     */
    public void setDietParameters(Set<DietParameter> dietParameters)
    {
        _laborReport.setDietParameters(dietParameters);
    }
    
    
}
