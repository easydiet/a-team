package at.easydiet.businesslogic;

import java.util.List;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.PatientBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientDAO;
import at.easydiet.model.Patient;
import at.easydiet.util.StringUtils;

public class DashboardController
{
    public static final org.apache.log4j.Logger LOG       = org.apache.log4j.Logger
                                                                  .getLogger(DashboardController.class);
    private ArrayList<PatientBO>                _patients = null;
    private String                              _patientFilter;

    public DashboardController()
    {
        // String[][] dummyData = {
        // {"1234567890", "Daniel", "Kuschny"},
        // {"1234567895", "Simon", "Moosbrugger"},
        // {"1234567893", "Mathias", "Joschika"},
        // {"1234567894", "Matthias", "Amann"},
        // {"1234567891", "Halkdjfpwokepof", "dfklsdjfklsdjfl"}
        // };
        //
        // for (String[] strings : dummyData)
        // {
        // Patient patient = new Patient();
        // patient.setInsuranceNumber(strings[0]);
        // patient.setForename(strings[1]);
        // patient.setLastname(strings[2]);
        // _patients.add(patient);
        // }
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
