package at.easydiet.businesslogic;

import java.util.List;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.PatientBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientDAO;
import at.easydiet.model.Patient;
import at.easydiet.util.StringUtils;

/**
 * Provides data and actions for the DashboardView.
 */
public class DashboardViewController
{
    private static final org.apache.log4j.Logger LOG            = org.apache.log4j.Logger
                                                                        .getLogger(DashboardViewController.class);
    private ArrayList<PatientBO>                 _patients      = null;
    private String                               _patientFilter = "";

    // this is a unique instance
    private static DashboardViewController       _singleton;

    /**
     * Returns a globally unique instance of this class. 
     * @return a globally unique instance which gets initiated on the first call.
     */
    public static DashboardViewController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new DashboardViewController();
        }
        return _singleton;
    }

    /**
     * Initializes a new instance of the {@link DashboardViewController} class.
     */
    private DashboardViewController()
    {
        // singleton
    }

    /**
     * Returns a list of all loaded patients. 
     * This collection is cached and filtered by the currently set {@link DashboardViewController#getPatientFilter()}
     * @see DashboardViewController#refreshPatients()
     * @return list of all loaded patients.
     */
    public ArrayList<PatientBO> getPatients()
    {
        if (_patients == null)
        {
            refreshPatients();
        }
        return _patients;
    }

    /**
     * Refreshes the list of loaded patients.
     * This method will use the specified filter to only load specific patients.
     */
    public void refreshPatients()
    {
        LOG.trace("Refreshing Patients");
        PatientDAO patientDao = DAOFactory.getInstance().getPatientDAO();
        List<Patient> patients;

        _patients = new ArrayList<PatientBO>();
        if (StringUtils.isNullOrWhitespaceOnly(_patientFilter))
        {
            LOG.trace("Loading all Patients (no filtering)");
            patients = patientDao.findAll();
        }
        else
        {
            LOG.trace(String.format("Loading filtered Patients (%s)", _patientFilter));
            patients = patientDao.findByQuery(_patientFilter);
        }

        
        LOG.trace("Setup loaded patients");
        for (Patient patient : patients)
        {
            _patients.add(new PatientBO(patient));
        }
    }

    /**
     * Sets the filter string to only load specific patients during load. 
     * This query supports several properties to mention like the InsuranceNumber and the name of a Patient.
     * This method will not trigger any patient reloading.
     * @param searchString the filter string.
     */
    public void setPatientFilter(String searchString)
    {
        _patientFilter = searchString;
    }

    /**
     * Gets the currently loaded filter for loading patients.
     * @return the currently set filter.
     */
    public String getPatientFilter()
    {
        return _patientFilter;
    }
}
