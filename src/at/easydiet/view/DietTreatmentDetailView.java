package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.SortDirection;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.Mouse.Button;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businessobjects.ContactJournalBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.domainlogic.DietPlanEditingController;

/**
 * This is the background class for viewing and changing a
 * {@link DietTreatmentBO}
 */
public class DietTreatmentDetailView extends EasyDietContentView implements
        Bindable
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(DietTreatmentDetailView.class);
    /**
     * Stores the {@link DietPlanBO} table view from the GUI
     */
    @BXML
    private TableView                            _dietPlanTable;
    /**
     * Stores the {@link ContactJournalBO} table view from the GUI
     */
    @BXML
    private TableView                            _contactJournalTable;
    /**
     * Stores the {@link NutritionProtocolBO} table view from the GUI
     */
    @BXML
    private TableView                            _nutritionProtocolTable;

    /**
     * 
     * @see at.easydiet.view.EasyDietContentView#onLoad()
     */
    @Override
    public void onLoad()
    {
        DietTreatmentDetailViewController.getInstance().refresh();
        setDietTreatment(DietTreatmentDetailViewController.getInstance()
                .getDietTreatment());
    }

    /**
     * Sets the data to the corresponding views
     * 
     * @param treatment
     *            The {@link DietTreatmentBO} which provides the data
     */
    public void setDietTreatment(DietTreatmentBO treatment)
    {
        _dietPlanTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getDietTreatment().getDietPlans());
        _dietPlanTable.setSort(_dietPlanTable.getColumns().get(0).getName(),
                SortDirection.ASCENDING);

        _dietPlanTable.getComponentMouseButtonListeners().add(
                new ComponentMouseButtonListener.Adapter()
                {
                    @Override
                    public boolean mouseClick(Component component,
                            Button button, int x, int y, int count)
                    {
                        if (_dietPlanTable.getSelectedRow() != null
                                && count == 2)
                        {
                            editDietPlan((DietPlanBO) _dietPlanTable
                                    .getSelectedRow());
                            return true;
                        }
                        return false;
                    }
                });
        _contactJournalTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getDietTreatment().getContactJournals());
        _nutritionProtocolTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getDietTreatment().getNutritionProtocols());
    }

    /**
     * Open {@link EditDietPlanView}
     * 
     * @param dietPlan
     *            The {@link DietPlanBO} to edit
     */
    protected void editDietPlan(DietPlanBO dietPlan)
    {
        DietPlanEditingController.getInstance().setDietPlan(dietPlan);
        ViewController.getInstance().loadContent("EditDietPlanView", this);
    }

    /**
     * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map,
     *      java.net.URL, org.apache.pivot.util.Resources)
     */
    public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2)
    {}

}
