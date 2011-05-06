package at.easydiet.domainlogic;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.CalendarDate;
import org.hibernate.HibernateException;

import at.easydiet.EasyDietApplication;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietPlanDAO;
import at.easydiet.dao.DietTreatmentDAO;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.MealDAO;
import at.easydiet.dao.PatientDAO;
import at.easydiet.util.CollectionUtils;

public class DietTreatmentEditingController {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(DietTreatmentEditingController.class);


	private PatientBO _patient;
	private DietTreatmentBO _dietTreatment;
	private List<String> _errors;

	private static DietTreatmentEditingController _singleton;

	public static DietTreatmentEditingController getInstance() {
		if (_singleton == null) {
			_singleton = new DietTreatmentEditingController();
		}
		return _singleton;
	}

	/**
	 * Initializes a new instance of the {@link DietTreatmentEditingController}
	 * class.
	 */
	private DietTreatmentEditingController() {
		_errors = new ArrayList<String>();
	}


	public void createNew(PatientBO patient) {
		_dietTreatment = new DietTreatmentBO();
		_dietTreatment.setPatient(patient);
	}

	public void refresh()
    {
        refresh(true);
    }

    public void refresh(boolean refreshDietTreatment)
    {
        if (refreshDietTreatment && _dietTreatment != null
                && _dietTreatment.getDietTreatmentId() > 0)
        {
            DietTreatmentDAO dao = DAOFactory.getInstance().getDietTreatmentDAO();
            dao.refresh(_dietTreatment.getModel());
        }

        //TODO: is all refreshed?
    }
    
    public List<String> getErrors()
    {
        return _errors;
    }
    
    public boolean saveDietTreatment()
    {
        validateDietTreatment(true);

        if (getErrors().getLength() > 0) return false;

        SimpleDateFormat formatter = new SimpleDateFormat(
                EasyDietApplication.DATETIME_FORMAT);

        try
        {
            HibernateUtil.currentSession().beginTransaction();
            DietTreatmentDAO dao = DAOFactory.getInstance().getDietTreatmentDAO();
            dao.makePersistent(_dietTreatment.getModel());
            HibernateUtil.currentSession().getTransaction().commit();
            _dietTreatment = null;
            return true;
        }
        catch (HibernateException e)
        {
            LOG.error("Could not save diettreatment", e);
            HibernateUtil.currentSession().getTransaction().rollback();
            return false;
        }
    }

    public void validateDietTreatment()
    {
        validateDietTreatment(false);
    }

    public void validateDietTreatment(boolean checkForEmpty)
    {
        _errors.clear();

        // validate empty elements
        if (checkForEmpty)
        {
            validateEmptyElements();
        }
        
        //TODO: check for everything

    }

	private void validateEmptyElements() {
		// TODO: Check if something is missing
		
	}
	
	public void revertChanges()
    {
        // TODO: Find a good way to revert changes 
        // reload patient
        HibernateUtil.currentSession().evict(_dietTreatment.getModel());
        PatientDAO dao = DAOFactory.getInstance().getPatientDAO();
        _patient.setModel(dao.findById(_patient.getPatientId(), false));
    }
	
	public void setPatient(PatientBO patient)
	{
		_patient = patient;
	}

	public PatientBO getPatient()
	{
		return _patient;
	}

	public DietTreatmentBO getDietTreatment() {
		return _dietTreatment;
	}
}
