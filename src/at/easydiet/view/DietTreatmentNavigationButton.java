package at.easydiet.view;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.view.EasyDietContentView.NavigationButton;

/**
 * This button is used as the navigation button for a
 * {@link DietTreatmentDetailView} in the GUI
 */
public class DietTreatmentNavigationButton extends NavigationButton
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(DietTreatmentNavigationButton.class);

    /**
     * 
     * @see at.easydiet.view.EasyDietContentView.NavigationButton#refresh()
     */
    @Override
    public void refresh()
    {
        setText(DietTreatmentDetailViewController.getInstance()
                .getDietTreatment().getDisplayText());
    }

}
