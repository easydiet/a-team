package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.domainlogic.DietPlanEditingController;
import at.easydiet.domainlogic.ParametersetEditingController;

public class CreateParametersetView extends EasyDietContentView implements Bindable
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateParametersetView.class);
    protected ParameterTableViewTemplate                 _parameterTableViewTemplate;

    @Override
    public void onLoad()
    {
    	ParametersetEditingController.getInstance().createNew();
    	//ParametersetEditingController.getInstance().refresh();
    	_parameterTableViewTemplate
        .setParameterProvider(ParametersetEditingController.getInstance()
                .getParameterset());
    }


	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		_parameterTableViewTemplate = (ParameterTableViewTemplate) namespace
        .get("parameterTableViewTemplate");
		
		Button saveButton = (Button) namespace.get("save");
        saveButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            public void buttonPressed(Button button)
            {
                save();
            }
        });
	}
	
	private void save()
    {
        boolean saved = ParametersetEditingController.getInstance().saveParameterset();
        if(saved)
        {
            ViewController.getInstance().loadContent(
                    "CreateParametersetView", CreateParametersetView.this);
        }
        else if(ParametersetEditingController.getInstance().getErrors().getLength() == 0)
        {
            EasyAlerts.error("Es ist ein Fehler beim Speichern des Parameterset aufgetreten, bitte versuchen Sie es erneut!", EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(), null);
        }
        else
        {
            EasyAlerts.error("Es sind noch Fehler im Parameterset vorhanden! Bitte korrigieren Sie diese!", EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(), null);
        }
    }


}
