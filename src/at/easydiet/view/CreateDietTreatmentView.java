package at.easydiet.view;

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
	}
}
