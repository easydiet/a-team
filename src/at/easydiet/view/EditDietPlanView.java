package at.easydiet.view;

import org.apache.pivot.beans.Bindable;

import at.easydiet.domainlogic.DietPlanEditingController;

/**
 * This is the background class for the EditDietPlanView.bxml
 */
public class EditDietPlanView extends DietPlanManagementView implements
        Bindable
{

    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(EditDietPlanView.class);

    /**
     * @see at.easydiet.view.DietPlanManagementView#onLoad()
     */
    @Override
    public void onLoad()
    {
        DietPlanEditingController.getInstance().refresh();

        rebuildUI();

        _parameterTableViewTemplate
                .setParameterProvider(DietPlanEditingController.getInstance()
                        .getDietPlan());
    }

}