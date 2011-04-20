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
import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.PatientBO;

public class PatientDetailView extends EasyDietContentView implements Bindable
{
    public static final org.apache.log4j.Logger LOG         = org.apache.log4j.Logger
                                                                    .getLogger(PatientDetailView.class);

    @BXML
    private TableView                           _dietTreatmentsTable;
    @BXML
    private TableView                           _patientStatesTable;
    @BXML
    private TableView                           _laborReportsTable;
    
    @Override
    public void onLoad()
    {
        setPatient(PatientDetailViewController.getInstance().getPatient());
    }

    public void setPatient(PatientBO patient)
    {
        _dietTreatmentsTable.setTableData(PatientDetailViewController.getInstance().getDietTreatments());
        _patientStatesTable.setTableData(PatientDetailViewController.getInstance().getPatientStates());
        _laborReportsTable.setTableData(PatientDetailViewController.getInstance().getLaborReports());
    }

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
    }

    protected void openDietTreatmentDetailView(DietTreatmentBO dietTreatment)
    {
        DietTreatmentDetailViewController.getInstance().setDietTreatment(dietTreatment);
        ViewController.getInstance().loadContent("DietTreatmentDetailView",
                this);
    }

}
