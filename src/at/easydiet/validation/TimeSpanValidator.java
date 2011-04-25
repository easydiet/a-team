package at.easydiet.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietPlanDAO;
import at.easydiet.dao.DietTreatmentDAO;
import at.easydiet.dao.PatientDAO;
import at.easydiet.dao.TimeSpanDAO;
import at.easydiet.model.DietPlan;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.Patient;
import at.easydiet.model.TimeSpan;

public class TimeSpanValidator {
	private Set<TimeSpanBO> _source = new HashSet<TimeSpanBO>();

	/*public TimeSpanValidator(Patient patient) {
		for (TimeSpan timespan : DAOFactory.getInstance().getTimeSpanDAO().findByPatient(patient)) {
			_source.add(new TimeSpanBO(timespan));
		}
	}*/

	public void overlappingTimeSpan(PatientBO currentPatient,
			DietTreatmentBO currentDietTreatment, DietPlanBO currentDietPlan,
			TimeSpanBO timeSpan) throws TimeSpanValidationException {

		// 1. validate for overlapping of given timespan, with any other diettreatments of patient
		for (DietTreatmentBO diettreatment : currentPatient.getTreatmentBOs()) {
			if (diettreatment.getDietTreatmentId() != currentDietTreatment.getDietTreatmentId()) {
				if (!correctValidation(diettreatment.getStart(),
						diettreatment.getEnd(), timeSpan.getStart(),
						timeSpan.getEnd())) {
					throw new TimeSpanValidationException(timeSpan,
							currentDietPlan);
				}
			}
		}
		// 2. validate for overlapping of given timspan with any other dietplan in current dietTreatmnet
		for (DietPlanBO dietplan : currentDietTreatment.getDietPlanBOs()) {
			if (dietplan.getDietPlanId() !=currentDietPlan.getDietPlanId()) {
				if (!correctValidation(dietplan.getStart(), dietplan.getEnd(),
						timeSpan.getStart(), timeSpan.getEnd())) {
					throw new TimeSpanValidationException(timeSpan,
							currentDietTreatment);
				}
			}
		}
		// 3. validate for overlapping between any two timespans within the current dietPlan
		//dietplan Bo: add getTimeSpanBOs()
		for (TimeSpanBO toCompare : currentDietPlan.getSortedTimeSpanBOs()) {
			if (toCompare.getTimeSpanId()!=timeSpan.getTimeSpanId()) {
				if (!correctValidation(timeSpan.getStart(), timeSpan.getEnd(),
						toCompare.getStart(), toCompare.getEnd())) {
					throw new TimeSpanValidationException(timeSpan, toCompare);
				}
			}
		}
	}

	/**
	 * compares all timespans from a patient and checks if they overlap or not
	 * 
	 * @return returns whether the timespans of all dietarytreatments overlap
	 */
	public boolean correctValidation(Date tempStart, Date tempEnd,
			Date toCompareStart, Date toCompareEnd) {
		boolean result = false;
		if (!(tempStart.before(toCompareStart) && toCompareStart.before(tempEnd))
				&& !(tempStart.before(toCompareEnd) && toCompareEnd.before(tempEnd))
				&& !(toCompareStart.before(tempStart) && tempStart.before(toCompareEnd))
				&& !(toCompareStart.before(toCompareEnd) && tempEnd.before(toCompareEnd))) {
			result = true;
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PatientDAO p = DAOFactory.getInstance().getPatientDAO();
		DietTreatmentDAO treat = DAOFactory.getInstance().getDietTreatmentDAO();
		DietPlanDAO plan = DAOFactory.getInstance().getDietPlanDAO();
		TimeSpanDAO span = DAOFactory.getInstance().getTimeSpanDAO();
		ArrayList<Patient> patients = (ArrayList<Patient>) p.findByData("Johann", null, null);
		DietTreatment t = treat.findById(1l, false);
		DietPlan d = plan.findById(1l, false);
		TimeSpan s = span.findById(2l, false);

		DietPlanBO planBo = new DietPlanBO(d);
		DietTreatmentBO treatBo = new DietTreatmentBO(t);
		TimeSpanBO spanbo = new TimeSpanBO(s);
		System.out.println(planBo.getStart() + " | " + planBo.getEnd());
		PatientBO patientbo = new PatientBO(patients.get(0));

		TimeSpanValidator validator = new TimeSpanValidator();
		//System.out.println(validator.correctValidation(spanbo.getStart(), spanbo.getEnd(), 
			//	planBo.getStart(), planBo.getEnd()));

		
			try {
				validator.overlappingTimeSpan(patientbo, treatBo, planBo, spanbo);
			} catch (TimeSpanValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
}
