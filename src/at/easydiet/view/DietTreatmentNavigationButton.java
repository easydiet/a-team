package at.easydiet.view;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.view.EasyDietContentView.NavigationButton;

public class DietTreatmentNavigationButton extends NavigationButton
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietTreatmentNavigationButton.class);

    @Override
    public void refresh()
    {
        setText(DietTreatmentDetailViewController.getInstance()
                .getDietTreatment().getDisplayText());
    }

}
