package at.easydiet.view;

import org.apache.pivot.beans.Bindable;

import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;

public class CreateDietPlanView extends DietPlanManagementView implements Bindable
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateDietPlanView.class);


    @Override
    public void onLoad()
    {
        DietPlanEditingController.getInstance().createNew(
                DietTreatmentDetailViewController.getInstance()
                        .getDietTreatment());
        DietPlanEditingController.getInstance().refresh();

        _parameterTableViewTemplate
                .setParameterProvider(DietPlanEditingController.getInstance()
                        .getDietPlan());
    }


}
