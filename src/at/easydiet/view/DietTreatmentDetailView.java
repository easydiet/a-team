package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.Window;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businessobjects.DietTreatmentBO;

public class DietTreatmentDetailView extends Window implements Bindable
{
    public static final org.apache.log4j.Logger LOG         = org.apache.log4j.Logger
                                                                    .getLogger(DietTreatmentDetailView.class);
    @BXML
    private TableView                           _dietPlanTable;
    @BXML
    private TableView                           _contactJournalTable;
    @BXML
    private TableView                           _nutritionProtocolTable;

    private DietTreatmentDetailViewController   _controller = new DietTreatmentDetailViewController();

    public void setDietTreatment(DietTreatmentBO treatment)
    {
        _controller.setDietTreatment(treatment);
        _dietPlanTable.setTableData(_controller.getDietPlans());
        _contactJournalTable.setTableData(_controller.getContactJournals());
        _nutritionProtocolTable.setTableData(_controller.getNutritionProcotols());
    }

    public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2)
    {}

}
