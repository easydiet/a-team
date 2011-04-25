package at.easydiet.validation;

import java.util.Date;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.TimeSpanBO;

public class TimeSpanValidator {

	public void overlappingTimeSpan(PatientBO currentPatient,
			DietTreatmentBO currentDietTreatment, DietPlanBO currentDietPlan,
			TimeSpanBO timeSpan) throws TimeSpanValidationException {

		// 1. validate for overlapping of given timespan, with any other
		// diettreatments of patient
		for (DietTreatmentBO diettreatment : currentPatient.getTreatmentBOs()) {
			if (diettreatment.getDietTreatmentId() != currentDietTreatment
					.getDietTreatmentId()) {
				if (!correctValidation(diettreatment.getStart(),
						diettreatment.getEnd(), timeSpan.getStart(),
						timeSpan.getEnd())) {
					throw new TimeSpanValidationException(timeSpan,
							currentDietPlan);
				}
			}
		}
		// 2. validate for overlapping of given timspan with any other dietplan
		// in current dietTreatmnet
		for (DietPlanBO dietplan : currentDietTreatment.getDietPlanBOs()) {
			if (dietplan.getDietPlanId() != currentDietPlan.getDietPlanId()) {
				if (!correctValidation(dietplan.getStart(), dietplan.getEnd(),
						timeSpan.getStart(), timeSpan.getEnd())) {
					throw new TimeSpanValidationException(timeSpan,
							currentDietTreatment);
				}
			}
		}
		// 3. validate for overlapping between any two timespans within the
		// current dietPlan
		for (TimeSpanBO toCompare : currentDietPlan.getSortedTimeSpanBOs()) {
			if (toCompare.getTimeSpanId() != timeSpan.getTimeSpanId()) {
				if (!correctValidation(timeSpan.getStart(), timeSpan.getEnd(),
						toCompare.getStart(), toCompare.getEnd())) {
					throw new TimeSpanValidationException(timeSpan, toCompare);
				}
			}
		}
	}

	/**
	 * compares timespans from and checks if they overlap or not
	 * 
	 * @return returns if the timespans overlap
	 */
	public boolean correctValidation(Date tempStart, Date tempEnd,
			Date toCompareStart, Date toCompareEnd) {
		boolean result = false;
		if (!(tempStart.before(toCompareStart) && toCompareStart
				.before(tempEnd))
				&& !(tempStart.before(toCompareEnd) && toCompareEnd
						.before(tempEnd))
				&& !(toCompareStart.before(tempStart) && tempStart
						.before(toCompareEnd))
				&& !(toCompareStart.before(toCompareEnd) && tempEnd
						.before(toCompareEnd))) {
			result = true;
		}
		return result;
	}
}
