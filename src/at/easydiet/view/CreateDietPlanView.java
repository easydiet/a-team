package at.easydiet.view;

import org.apache.pivot.beans.Bindable;

import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;

/**
 * Background class for the CreateDietPlanView.bxml Provides data and methods
 * for the GUI.
 */
public class CreateDietPlanView extends DietPlanManagementView implements
        Bindable
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(CreateDietPlanView.class);

    /**
     * @see at.easydiet.view.EasyDietContentView#onLoad()
     */
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
