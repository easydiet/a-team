package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.Mouse.Button;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;

import at.easydiet.businesslogic.DashboardViewController;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.model.Patient;

public class DashboardView extends Window implements Bindable
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DashboardView.class);

    @BXML
    private TableView                           _patientSearchTable;
    @BXML
    private TextInput                           _searchBox;

    private DashboardViewController                 _controller;

    public void searchPatient(String searchString)
    {
        // perform search and update UI
        _controller.setPatientFilter(searchString);
        _controller.refreshPatients();
        _patientSearchTable.setTableData(_controller.getPatients());
    }

    public DashboardView()
    {
        _controller = new DashboardViewController();
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

        _patientSearchTable.setTableData(_controller.getPatients());
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
        try
        {
            BXMLSerializer serializer = new BXMLSerializer();
            PatientDetailView view = (PatientDetailView) serializer
                    .readObject(PatientDetailView.class
                            .getResource("PatientDetailView.xml"));
            view.setPatient(patient);
            view.open(getDisplay());
            close();
        }
        catch (Exception e)
        {
            LOG.error("Error Opening Detail View", e);
        }
    }
}
