package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.Window;

import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.businessobjects.PatientBO;

public class PatientDetailView extends Window implements Bindable
{
    @BXML
    private TableView                   _dietTreatmentsTable;
    @BXML
    private TableView                   _patientStatesTable;
    @BXML
    private TableView                   _laborReportsTable;

    private PatientDetailViewController _controller = new PatientDetailViewController();

    public void setPatient(PatientBO patient)
    {
        _controller.setPatient(patient);
        _dietTreatmentsTable.setTableData(_controller.getDietTreatments());
   }

    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
    }

}
