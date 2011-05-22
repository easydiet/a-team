package at.easydiet.view;

import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.view.EasyDietContentView.NavigationButton;

/**
 * This button is used as the navigation button for a {@link PatientDetailView}
 * in the GUI
 */
public class PatientNavigationButton extends NavigationButton
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(PatientNavigationButton.class);

    @Override
    public void refresh()
    {
        setText(PatientDetailViewController.getInstance().getPatient()
                .getDisplayName());
    }

}
