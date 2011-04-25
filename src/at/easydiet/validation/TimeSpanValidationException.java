package at.easydiet.validation;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.model.DietPlan;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.TimeSpan;

public class TimeSpanValidationException extends Exception {
	public static enum TimeSpanValidationExceptionType
	{
		OTHER_TIMESPAN,
		DIETTREATMENT,
		DIETPLAN
	}
	private TimeSpanBO _timespan = null;
	private TimeSpanBO _timespan2 = null;
	private DietTreatmentBO _diettreatment = null;
	private DietPlanBO _dietplan = null;
	private TimeSpanValidationExceptionType _type;
	
	
	
	public TimeSpanValidationExceptionType getType() {
		return _type;
	}

	public TimeSpanBO getTimespan() {
		return _timespan;
	}

	public void setTimespan(TimeSpanBO timespan) {
		_timespan = timespan;
	}

	public TimeSpanBO getTimespan2() {
		return _timespan2;
	}

	public void setTimespan2(TimeSpanBO timespan2) {
		_timespan2 = timespan2;
	}

	public DietTreatmentBO getDiettreatment() {
		return _diettreatment;
	}

	public void setDiettreatment(DietTreatmentBO diettreatment) {
		_diettreatment = diettreatment;
	}

	public DietPlanBO getDietplan() {
		return _dietplan;
	}

	public void setDietplan(DietPlanBO dietplan) {
		_dietplan = dietplan;
	}

	public TimeSpanValidationException(TimeSpanBO timespan, TimeSpanBO timespan2){
		_timespan = timespan;
		_timespan2 = timespan2;
		_type = TimeSpanValidationExceptionType.OTHER_TIMESPAN;
	}
	
	public TimeSpanValidationException(TimeSpanBO timespan, DietTreatmentBO diettreatment){
		_timespan = timespan;
		_diettreatment = diettreatment;
		_type = TimeSpanValidationExceptionType.DIETTREATMENT;
	}
	
	public TimeSpanValidationException(TimeSpanBO timespan, DietPlanBO dietplan){
		_timespan = timespan;
		_dietplan = dietplan;
		_type = TimeSpanValidationExceptionType.DIETPLAN;
	}
}
