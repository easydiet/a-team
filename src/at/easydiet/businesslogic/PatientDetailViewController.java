package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.PatientBO;

public class PatientDetailViewController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientDetailViewController.class);

    private PatientBO                           _patient;
    private ArrayList<DietTreatmentBO>          _dietTreatments;

    public PatientBO getPatient()
    {
        return _patient;
    }

    public void setPatient(PatientBO patient)
    {
        _patient = patient;
        reloadPatientData(patient);
    }

    public ArrayList<DietTreatmentBO> getDietTreatments()
    {
        return _dietTreatments;
    }

    private void reloadPatientData(PatientBO patient)
    {
        if (patient == null) return;
        _dietTreatments = patient.getTreatmentBOs();
    }

    public PatientDetailViewController()
    {

    }

}
