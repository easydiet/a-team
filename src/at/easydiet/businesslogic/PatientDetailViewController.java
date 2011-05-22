package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.UnresolvableObjectException;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.LaborReportBO;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.PatientStateBO;
import at.easydiet.businessobjects.PatientStateTypeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientDAO;
import at.easydiet.view.PatientDetailView;

/**
 * Provides data and actions for the {@link PatientDetailView}.
 */
public class PatientDetailViewController
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(PatientDetailViewController.class);

    /**
     * Stores the current selected patient
     */
    private PatientBO                            _patient;

    /**
     * Stores all the diet treatments provided by the patient
     */
    private List<DietTreatmentBO>                _dietTreatments;

    /**
     * Stores all the patient states provided by the patient
     */
    private List<PatientStateBO>                 _patientStates;

    /**
     * Stores all the labor reports provided by the patient
     */
    private List<LaborReportBO>                  _laborReports;

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static PatientDetailViewController   _singleton;

    /**
     * Returns the instance of the {@link PatientDetailViewController}
     * 
     * @return Instance of {@link PatientDetailViewController}
     */
    public static PatientDetailViewController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new PatientDetailViewController();
        }
        return _singleton;
    }

    /**
     * Initializes a new instance of the {@link PatientDetailViewController}
     * class.
     */
    private PatientDetailViewController()
    {

    }

    /**
     * Get the current opened patient
     * 
     * @return The current opened patient
     */
    public PatientBO getPatient()
    {
        return _patient;
    }

    /**
     * Set the current opened patient
     * 
     * @param patient
     *            The new patient
     */
    public void setPatient(PatientBO patient)
    {
        _patient = patient;
        reloadPatientData();
    }

    /**
     * Get all diet treatments of the current patient
     * 
     * @return List of {@link DietTreatmentBO}s
     */
    public List<DietTreatmentBO> getDietTreatments()
    {
        return _dietTreatments;
    }

    /**
     * Get all patient states of the current patient
     * 
     * @return List of {@link PatientStateBO}s
     */
    public List<PatientStateBO> getPatientStates()
    {
        return _patientStates;
    }

    /**
     * Get all labor reports of the current patient
     * 
     * @return List of {@link LaborReportBO}s
     */
    public List<LaborReportBO> getLaborReports()
    {
        return _laborReports;
    }

    /**
     * Reload the data of the {@link PatientBO} into the properties
     */
    public void reloadPatientData()
    {
        if (_patient == null) return;
        _dietTreatments = _patient.getTreatments();
        _patientStates = _patient.getPatientStates();
        _laborReports = _patient.getLaborReports();
    }

    /**
     * Reload the {@link PatientBO} from the database
     */
    public void refresh()
    {
        try
        {
            PatientDAO dao = DAOFactory.getInstance().getPatientDAO();
            dao.refresh(_patient.getModel());
        }
        catch (UnresolvableObjectException e)
        {
            LOG.error("Couldn't refresh dietplan");
        }
        _patient.updateLikesCache();
        _patient.updateFamilyanamnesisCache();
        _patient.updateLaborReportsCache();
        _patient.updatePatientStatesCache();
        _patient.updateTreatmentsCache();
        reloadPatientData();
    }

    /**
     * Get all {@link PatientStateBO}s with {@link PatientStateTypeBO#ASSIGNMENT}
     * @return List of {@link PatientStateBO}s with {@link PatientStateTypeBO#ASSIGNMENT}
     */
    public List<?> getAllDiagnosis()
    {

        List<PatientStateBO> list = new ArrayList<PatientStateBO>();

        for (PatientStateBO bo : _patientStates)
        {
            if (bo.getType() == PatientStateTypeBO.ASSIGNMENT)
            {
                list.add(bo);
            }
        }

        return list;
    }
}
