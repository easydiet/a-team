package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TableView;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businessobjects.DietTreatmentBO;

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
        setDietTreatment(DietTreatmentDetailViewController.getInstance().getDietTreatment());
    }

    public void setDietTreatment(DietTreatmentBO treatment)
    {
        _dietPlanTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getDietPlans());
        _contactJournalTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getContactJournals());
        _nutritionProtocolTable.setTableData(DietTreatmentDetailViewController
                .getInstance().getNutritionProcotols());
    }

    public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2)
    {}

}
