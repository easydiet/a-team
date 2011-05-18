package at.easydiet.domainlogic;

import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.HibernateException;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.DietTreatmentSystemUserBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.PatientStateBO;
import at.easydiet.businessobjects.SystemUserFunctionBO;
import at.easydiet.businessobjects.TreatmentStateBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietTreatmentDAO;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.PatientDAO;
import at.easydiet.validation.ParameterTemplateValidator;

public class DietTreatmentEditingController {
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(DietTreatmentEditingController.class);

	// set default state
	private TreatmentStateBO _defaultState = TreatmentStateBO.PLANNING;

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

	/**
	 * Create a new dietTreatment
	 * 
	 * @param patient
	 *            for this patient
	 */
	public void createNew(PatientBO patient) {
		_dietTreatment = new DietTreatmentBO();
		_dietTreatment.setPatient(patient);
		setPatient(patient);
	}

	public void refresh() {
		refresh(true);
	}

	public void refresh(boolean refreshDietTreatment) {
		if (refreshDietTreatment && _dietTreatment != null
				&& _dietTreatment.getDietTreatmentId() > 0) {
			DietTreatmentDAO dao = DAOFactory.getInstance()
					.getDietTreatmentDAO();
			dao.refresh(_dietTreatment.getModel());
		}

		// TODO: is all refreshed?
	}

	/**
	 * List of all occured errors
	 * 
	 * @return list of strings
	 */
	public List<String> getErrors() {
		return _errors;
	}

	/**
	 * Validate and save the treatment
	 * 
	 * @return true if save was possible
	 */
	public boolean saveDietTreatment() {
		//check for empty elements
		validateDietTreatment(true);

		if (getErrors().getLength() > 0)
			return false;

		try {
			// set default treatment state
			_dietTreatment.setTreatmentState(_defaultState);

			HibernateUtil.currentSession().beginTransaction();
			DietTreatmentDAO dao = DAOFactory.getInstance()
					.getDietTreatmentDAO();
			dao.makePersistent(_dietTreatment.getModel());
			HibernateUtil.currentSession().getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			LOG.error("Could not save diettreatment", e);
			HibernateUtil.currentSession().getTransaction().rollback();
			return false;
		}
	}

	/**
	 * Validate this treatment
	 */
	public void validateDietTreatment() {
		validateDietTreatment(false);
	}

	/**
	 * Validate this treatment
	 * @param checkForEmpty also check for empty elements
	 */
	public void validateDietTreatment(boolean checkForEmpty) {
		_errors.clear();

		// validate empty elements
		if (checkForEmpty) {
			validateEmptyElements();
		}

		// TODO: check for everything
		validateDietTreatmentParameters();
		validateTime();

	}

	/**
	 * Checks whether there is a collision with another diet treatment or not and writes them into the error list and box
	 */
	private void validateTime() {
		List<Object> collisions = validateCollisions();

		for (Object object : collisions) {
			if (DietTreatmentBO.class.isAssignableFrom(object.getClass())) {
				_errors.add(String
						.format("Die Diätbehandlung '%s' überschneidet sich mit der Diätbehandlung '%s'",
								_dietTreatment.getDisplayText(),
								((DietTreatmentBO) object).getName()));
			}
		}
	}

	/**
	 * Checks whether there is a collision with another diet treatment
	 * @return A list of colliding elements
	 */
	public List<Object> validateCollisions() {
		List<Object> collisions = new ArrayList<Object>();

		// collision with any other treatments
		List<DietTreatmentBO> treatments = _dietTreatment.getPatient()
				.getTreatments();
		for (DietTreatmentBO other : treatments) {
			if (other.equals(_dietTreatment))
				continue;
			if (isCollision(_dietTreatment.getStart(), _dietTreatment.getEnd(),
					other.getStart(), other.getEnd())) {
				collisions.add(other);
			}
		}

		return collisions;
	}

	/**
	 * Checks whether two time spans collide
	 * 
	 * @param currentStart
	 * @param currentEnd
	 * @param otherStart
	 * @param otherEnd
	 * @return
	 */
	private boolean isCollision(Date currentStart, Date currentEnd,
			Date otherStart, Date otherEnd) {
		return currentStart.compareTo(otherEnd) <= 0
				&& otherStart.compareTo(currentEnd) <= 0;
	}

	/**
	 * Validates parameters and writes them to the error box
	 */
	private void validateDietTreatmentParameters() {
		List<IDietParameterizable> conflicts = ParameterTemplateValidator
				.getInstance().getConflictingComponents();
		for (IDietParameterizable component : conflicts) {
			getErrors().add(
					"Parameterkonflikt in: " + component.getDisplayText());
		}
	}

	/**
	 * Checks for empty elements
	 */
	private void validateEmptyElements() {
		// Check if something is missing
		if (_dietTreatment.getName().length() < 1) {
			getErrors().add("Kein Name angegeben.");
		}

		// TODO: check that at least one user is TREATING!
		if (_dietTreatment.getSystemUsers().isEmpty()) {
			getErrors().add("Kein verantwortlicher User angegeben.");
		}

		if (_dietTreatment.getPatientStates().isEmpty()) {
			getErrors().add("Keine Zuweisungsdiagnose angegeben.");
		}
	}

	/**
	 * Set the element back to it's old state
	 */
	public void revertChanges() {
		// TODO: Find a good way to revert changes
		// reload patient
		HibernateUtil.currentSession().evict(_dietTreatment.getModel());
		PatientDAO dao = DAOFactory.getInstance().getPatientDAO();
		_patient.setModel(dao.findById(_patient.getPatientId(), false));
	}

	public void setPatient(PatientBO patient) {
		_patient = patient;
	}

	public PatientBO getPatient() {
		return _patient;
	}

	public DietTreatmentBO getDietTreatment() {
		return _dietTreatment;
	}

	/**
	 * Gets a list of all patientstates
	 * @return list of patientstates
	 */
	public List<?> getAllPatientState() {
		return _patient.getPatientStates();
	}

	/**
	 * remove a systemuser from the diettreatment
	 * @param systemUser the user to remove
	 */
	public void removeSystemUser(DietTreatmentSystemUserBO systemUser) {
		_dietTreatment.removeSystemUsers(systemUser);

	}

	/**
	 * Add a new systemuser template to the diettreatment
	 */
	public void addNewSystemUser() {

		DietTreatmentSystemUserBO newUser = new DietTreatmentSystemUserBO();
		// default user
		newUser.setSystemUser(SystemUserController.getInstance()
				.getCurrentUser());
		// default function
		newUser.setFunction(SystemUserFunctionBO.TREATING_ASSISTANT);

		_dietTreatment.addSystemUsers(newUser);
	}

	/**
	 * change the selected assignment diagnosis
	 * @param patientState
	 */
	public void changeAssignment(PatientStateBO patientState) {
		// TODO: make it clear what a patientstate is

		// remove old selection
		for (PatientStateBO bo : _dietTreatment.getPatientStates()) {
			_dietTreatment.removePatientStates(bo);
		}
		_dietTreatment.addPatientStates(patientState);
	}
}
