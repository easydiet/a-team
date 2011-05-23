package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.Mouse.Button;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.LaborReportBO;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.PatientStateBO;

/**
 * This is the background class for the PatientDetailView.bxml
 */
public class PatientDetailView extends EasyDietContentView implements Bindable
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(PatientDetailView.class);

    /**
     * Stores the {@link TableView} for {@link DietTreatmentBO}s from the GUI
     */
    @BXML
    private TableView                            _dietTreatmentsTable;
    /**
     * Stores the {@link TableView} for {@link PatientStateBO}s from the GUI
     */
    @BXML
    private TableView                            _patientStatesTable;
    /**
     * Stores the {@link TableView} for {@link LaborReportBO}s from the GUI
     */
    @BXML
    private TableView                            _laborReportsTable;

    /**
     * @see at.easydiet.view.EasyDietContentView#onLoad()
     */
    @Override
    public void onLoad()
    {
        PatientDetailViewController.getInstance().refresh();
        setPatient(PatientDetailViewController.getInstance().getPatient());
    }

    /**
     * Sets the patient
     * 
     * @param patient
     *            The new {@link PatientBO}
     */
    public void setPatient(PatientBO patient)
    {
        _dietTreatmentsTable.setTableData(PatientDetailViewController
                .getInstance().getDietTreatments());
        _patientStatesTable.setTableData(PatientDetailViewController
                .getInstance().getPatientStates());
        _laborReportsTable.setTableData(PatientDetailViewController
                .getInstance().getLaborReports());
    }

    /**
     * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map,
     *      java.net.URL, org.apache.pivot.util.Resources)
     */
    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
        _dietTreatmentsTable.getComponentMouseButtonListeners().add(
                new ComponentMouseButtonListener.Adapter()
                {
                    @Override
                    public boolean mouseClick(Component component,
                            Button button, int x, int y, int count)
                    {
                        if (count >= 2
                                && _dietTreatmentsTable.getSelectedRow() != null)
                        {
                            openDietTreatmentDetailView((DietTreatmentBO) _dietTreatmentsTable
                                    .getSelectedRow());
                            return true;
                        }
                        return false;
                    }
                });

        PushButton editLikes = (PushButton) namespace.get("editLikes");
        editLikes.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(org.apache.pivot.wtk.Button button)
            {
                openPatientLikeManagementView();
            }
        });

        PushButton addDietTreatment = (PushButton) namespace
                .get("addDietTreatment");
        addDietTreatment.getButtonPressListeners().add(
                new ButtonPressListener()
                {

                    @Override
                    public void buttonPressed(org.apache.pivot.wtk.Button button)
                    {
                        openAddNewDietTreatmentManagementView();
                    }

                });

    }

    /**
     * Opens the view to add a new {@link DietTreatmentBO}
     */
    protected void openAddNewDietTreatmentManagementView()
    {
        ViewController.getInstance().loadContent("CreateDietTreatmentView",
                this);
    }

    /**
     * Opens the view to manage the patient likes and dislikes
     */
    protected void openPatientLikeManagementView()
    {
        ViewController.getInstance().loadContent("PatientLikeManagementView",
                this);
    }

    /**
     * Opens the view to edit a {@link DietTreatmentBO}
     * 
     * @param dietTreatment
     *            The {@link DietTreatmentBO} to edit
     */
    protected void openDietTreatmentDetailView(DietTreatmentBO dietTreatment)
    {
        DietTreatmentDetailViewController.getInstance().setDietTreatment(
                dietTreatment);
        ViewController.getInstance().loadContent("DietTreatmentDetailView",
                this);
    }

}
