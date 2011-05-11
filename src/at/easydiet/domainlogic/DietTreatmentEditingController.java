package at.easydiet.domainlogic;

import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.HibernateException;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietTreatmentDAO;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.PatientDAO;
import at.easydiet.validation.ParameterTemplateValidator;

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
		setPatient(patient);
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
        validateDietTreatmentParameters();
        validateTime();

    }
    
    private void validateTime() {
		List<Object> collisions = validateCollisions();
		
		for (Object object : collisions)
        {
            if (DietTreatmentBO.class.isAssignableFrom(object.getClass()))
            {
                _errors.add(String
                        .format("Die Diätbehandlung '%s' überschneidet sich mit der Diätbehandlung '%s'",
                                _dietTreatment.getDisplayText(),
                                ((DietTreatmentBO) object).getName()));
            }
        }
	}

	public List<Object> validateCollisions()
    {
        List<Object> collisions = new ArrayList<Object>();
        
        // collision with any other treatments
        List<DietTreatmentBO> treatments = _dietTreatment.getPatient().getTreatments();
        for (DietTreatmentBO other : treatments)
        {
            if (other.equals(_dietTreatment)) continue;
            if (isCollision(_dietTreatment.getStart(), _dietTreatment.getEnd(), other.getStart(), other.getEnd()))
            {
                collisions.add(other);
            }
        }
        
        return collisions;
    }
    
    /**
     * Checks whether two timeranges collide
     * @param currentStart
     * @param currentEnd
     * @param otherStart
     * @param otherEnd
     * @return
     */
    private boolean isCollision(Date currentStart, Date currentEnd, Date otherStart, Date otherEnd)
    {
        return currentStart.compareTo(otherEnd) <= 0 && otherStart.compareTo(currentEnd) <= 0;
    }

	private void validateDietTreatmentParameters() {
		List<IDietParameterizable> conflicts = ParameterTemplateValidator.getInstance().getConflictingComponents();
        for(IDietParameterizable component : conflicts)
        {
        	getErrors().add("Parameterkonflikt in: " + component.getDisplayText());
        }
	}

	private void validateEmptyElements() {
		// TODO: Check if something is missing
		if(_dietTreatment.getName()==null)
		{
			getErrors().add("Kein Name angegeben.");
		}
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
