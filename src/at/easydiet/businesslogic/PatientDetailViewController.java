package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.LaborReportBO;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.PatientStateBO;

public class PatientDetailViewController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientDetailViewController.class);

    private PatientBO                           _patient;
    private ArrayList<DietTreatmentBO>          _dietTreatments;
    private ArrayList<PatientStateBO>           _patientStates;
    private ArrayList<LaborReportBO>            _laborReports;

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

    public ArrayList<DietTreatmentBO> getDietTreatments()
    {
        return _dietTreatments;
    }

    public ArrayList<PatientStateBO> getPatientStates()
    {
        return _patientStates;
    }

    public ArrayList<LaborReportBO> getLaborReports()
    {
        return _laborReports;
    }

    public void reloadPatientData()
    {
        if (_patient == null) return;
        _dietTreatments = _patient.getTreatmentBOs();
        _patientStates = _patient.getPatientStateBOs();
        _laborReports = _patient.getLaborReportBOs();
    }
}
