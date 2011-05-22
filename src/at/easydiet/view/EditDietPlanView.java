package at.easydiet.view;

import org.apache.pivot.beans.Bindable;

import at.easydiet.businesslogic.DietPlanEditingController;

public class EditDietPlanView extends DietPlanManagementView implements
		Bindable {

	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(EditDietPlanView.class);

	@Override
	public void onLoad() {
		DietPlanEditingController.getInstance().refresh();

		rebuildUI();

		_parameterTableViewTemplate
				.setParameterProvider(DietPlanEditingController.getInstance()
						.getDietPlan());
	}

}