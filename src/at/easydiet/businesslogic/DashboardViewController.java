package at.easydiet.businesslogic;

import java.util.List;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.PatientBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientDAO;
import at.easydiet.model.Patient;
import at.easydiet.util.StringUtils;

public class DashboardViewController
{
    public static final org.apache.log4j.Logger LOG       = org.apache.log4j.Logger
                                                                  .getLogger(DashboardViewController.class);
    private ArrayList<PatientBO>                _patients = null;
    private String                              _patientFilter;

    public DashboardViewController()
    {
       
    }

    public ArrayList<PatientBO> getPatients()
    {
        if (_patients == null)
        {
            refreshPatients();
        }
        return _patients;
    }

    public void refreshPatients()
    {
        PatientDAO patientDao = DAOFactory.getInstance().getPatientDAO();
        List<Patient> patients;

        _patients = new ArrayList<PatientBO>();
        if (StringUtils.isNullOrWhitespaceOnly(_patientFilter))
        {
            patients = patientDao.findAll();
        }
        else
        {
            patients = patientDao.findPatientsByQuery(_patientFilter);
        }

        for (Patient patient : patients)
        {
            _patients.add(new PatientBO(patient));
        }
    }

    public void setPatientFilter(String searchString)
    {
        _patientFilter = searchString;
    }
}
