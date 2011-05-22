package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListView;

import at.easydiet.businesslogic.ParametersetEditingController;
import at.easydiet.businessobjects.DietParameterSetBO;
import at.easydiet.businessobjects.DietParameterTemplateBO;

public class CreateParametersetView extends EasyDietContentView implements
        Bindable
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateParametersetView.class);

    protected ParameterTableViewTemplate        _parameterTableViewTemplate;
    private ListView                            _errorBox;
    private Border                              _errorBorder;

    /**
     * @see at.easydiet.view.EasyDietContentView#onLoad()
     */
    @Override
    public void onLoad()
    {
        ParametersetEditingController.getInstance().createNew();
        setParameterSet(ParametersetEditingController.getInstance()
                .getParameterset());
        _errorBox.setListData(ParametersetEditingController.getInstance()
                .getErrors());
    }

    /**
     * Sets the parameterset for all ui elements.
     *
     * @param parameterset the new parameterset
     */
    private void setParameterSet(DietParameterSetBO parameterset)
    {
        load(parameterset);
        _parameterTableViewTemplate.setParameterProvider(parameterset);
    }

    @Override
    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
        _parameterTableViewTemplate = (ParameterTableViewTemplate) namespace
                .get("parameterTableViewTemplate");
        _parameterTableViewTemplate
                .setNewInstanceType(DietParameterTemplateBO.class);

        _errorBox = (ListView) namespace.get("errorBox");
        _errorBorder = (Border) namespace.get("errorBorder");

        Button saveButton = (Button) namespace.get("save");
        saveButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            public void buttonPressed(Button button)
            {
                save();
                updateErrorBox();
            }
        });

        Button validateButton = (Button) namespace.get("validate");
        validateButton.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button arg0)
            {
                store(ParametersetEditingController.getInstance()
                        .getParameterset());
                ParametersetEditingController.getInstance().validate();
                updateErrorBox();
            }
        });
        updateErrorBox();
    }

    /**
     * Update the error box.
     */
    private void updateErrorBox()
    {
        _errorBorder.setVisible((_errorBox.getListData().getLength() > 0));
    }

    /**
     * Save the parameterset.
     */
    private void save()
    {
        store(ParametersetEditingController.getInstance().getParameterset());
        boolean saved = ParametersetEditingController.getInstance().save();
        if (saved)
        {
            ViewController.getInstance().loadContent("DashboardView",
                    CreateParametersetView.this);
        }
        else if (ParametersetEditingController.getInstance().getErrors()
                .getLength() == 0)
        {
            EasyAlerts
                    .error("Es ist ein Fehler beim Speichern des Parameterset aufgetreten, bitte versuchen Sie es erneut!",
                            EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
                            null);
        }
        else
        {
            EasyAlerts
                    .error("Es sind noch Fehler im Parameterset vorhanden! Bitte korrigieren Sie diese!",
                            EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
                            null);
        }
    }

}
