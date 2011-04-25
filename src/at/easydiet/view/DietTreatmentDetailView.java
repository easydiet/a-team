package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.Mouse.Button;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.domainlogic.DietPlanEditingController;

public class DietTreatmentDetailView extends EasyDietContentView implements
        Bindable
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietTreatmentDetailView.class);
    @BXML
    private TableView                           _dietPlanTable;
    @BXML
    private TableView                           _contactJournalTable;
    @BXML
    private TableView                           _nutritionProtocolTable;

    @Override
    public void onLoad()
    {
        DietTreatmentDetailViewController.getInstance().refresh();
        setDietTreatment(DietTreatmentDetailViewController.getInstance().getDietTreatment());
    }

    public void setDietTreatment(DietTreatmentBO treatment)
    {
        _dietPlanTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getDietPlans());
        _dietPlanTable.getComponentMouseButtonListeners().add(new ComponentMouseButtonListener.Adapter()
        {
            @Override
            public boolean mouseClick(Component component, Button button,
                    int x, int y, int count)
            {
                if(_dietPlanTable.getSelectedRow() != null && count == 2)
                {
                    editDietPlan((DietPlanBO)_dietPlanTable.getSelectedRow());
                    return true;
                }
                return false;
            }
        });
        _contactJournalTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getContactJournals());
        _nutritionProtocolTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getNutritionProcotols());
    }

    protected void editDietPlan(DietPlanBO selectedRow)
    {
        DietPlanEditingController.getInstance().setDietPlan(selectedRow);
        ViewController.getInstance().loadContent("EditDietPlanView", this);
    }

    public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2)
    {}

}
