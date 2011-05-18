package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.Mouse.Button;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;

import at.easydiet.businesslogic.DashboardViewController;
import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.businessobjects.PatientBO;

public class DashboardView extends EasyDietContentView implements Bindable
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DashboardView.class);

    @BXML
    private TableView                           _patientSearchTable;
    @BXML
    private TextInput                           _searchBox;

    public void searchPatient(String searchString)
    {
        // perform search and update UI
        DashboardViewController.getInstance().setPatientFilter(searchString);
        DashboardViewController.getInstance().refreshPatients();
        _patientSearchTable.setTableData(DashboardViewController.getInstance().getPatients());
    }
    
    @Override
    public void onLoad()
    {
        DashboardViewController.getInstance().refreshPatients();
        _searchBox.setText(DashboardViewController.getInstance().getPatientFilter());
        _searchBox.requestFocus();
    }

    public DashboardView()
    {
    }

    public void initialize(Map<String, Object> namespace, URL url,
            Resources resources)
    {
        _searchBox.getComponentKeyListeners().add(
                new ComponentKeyListener.Adapter()
                {
                    @Override
                    public boolean keyReleased(Component component,
                            int keyCode, KeyLocation keyLocation)
                    {
                        searchPatient(_searchBox.getText());
                        return true;
                    }
                });

        _patientSearchTable.setTableData(DashboardViewController.getInstance().getPatients());
        _patientSearchTable.getComponentMouseButtonListeners().add(
                new ComponentMouseButtonListener.Adapter()
                {
                    @Override
                    public boolean mouseClick(Component component,
                            Button button, int x, int y, int count)
                    {
                        if (count >= 2
                                && _patientSearchTable.getSelectedRow() != null)
                        {
                            openPatientDetailView((PatientBO) _patientSearchTable
                                    .getSelectedRow());
                            return true;
                        }
                        return false;
                    }
                });
    }

    protected void openPatientDetailView(PatientBO patient)
    {
        PatientDetailViewController.getInstance().setPatient(patient);
        ViewController.getInstance().loadContent("PatientDetailView", this);
    }
}
