package at.easydiet.view;

import org.apache.pivot.util.CalendarDate;

import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.domainlogic.DietTreatmentEditingController;

public class CreateDietTreatmentView extends DietTreatmentManagementView {
	@Override
	public void onLoad() {
		_saved = false;
		
		DietTreatmentEditingController.getInstance().createNew(
                PatientDetailViewController.getInstance()
                        .getPatient());
		
		PatientDetailViewController.getInstance().refresh();
        DietTreatmentEditingController.getInstance().refresh();

        _parameterTableViewTemplate
                .setParameterProvider(DietTreatmentEditingController.getInstance()
                        .getDietTreatment());
        
        _patientState.setTableData(DietTreatmentEditingController.getInstance()
				.getAllPatientState());
        
		_systemUserTable.setTableData(getDietTreatment().getSystemUsers());
		
		//update timespan
		CalendarDate start = _startDateButton.getSelectedDate();
		getDietTreatment().setStart(start);
		CalendarDate end = _endDateButton.getSelectedDate();
		int days = end.subtract(start);
		getDietTreatment().setDuration(days);
	}
}
