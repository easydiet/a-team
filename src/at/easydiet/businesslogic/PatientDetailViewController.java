package at.easydiet.businesslogic;

import org.apache.pivot.collections.List;
import org.hibernate.UnresolvableObjectException;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.LaborReportBO;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.PatientStateBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientDAO;

public class PatientDetailViewController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientDetailViewController.class);

    private PatientBO                           _patient;
    private List<DietTreatmentBO>               _dietTreatments;
    private List<PatientStateBO>                _patientStates;
    private List<LaborReportBO>                 _laborReports;

    private static PatientDetailViewController  _singleton;

    public static PatientDetailViewController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new PatientDetailViewController();
        }
        return _singleton;
    }

    private PatientDetailViewController()
    {

    }

    public PatientBO getPatient()
    {
        return _patient;
    }

    public void setPatient(PatientBO patient)
    {
        _patient = patient;
        reloadPatientData();
    }

    public List<DietTreatmentBO> getDietTreatments()
    {
        return _dietTreatments;
    }

    public List<PatientStateBO> getPatientStates()
    {
        return _patientStates;
    }

    public List<LaborReportBO> getLaborReports()
    {
        return _laborReports;
    }

    public void reloadPatientData()
    {
        if (_patient == null) return;
        _dietTreatments = _patient.getTreatments();
        _patientStates = _patient.getPatientStates();
        _laborReports = _patient.getLaborReports();
    }

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
}
