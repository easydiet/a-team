package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ListListener;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.PushButton;

import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.validation.ParameterTemplateValidator;

public abstract class DietPlanManagementView extends EasyDietContentView implements Bindable {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
    .getLogger(DietPlanManagementView.class);

	protected BoxPane                             _timeSpanContainer;
    protected ParameterTableViewTemplate                 _parameterTableViewTemplate;

    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
        _timeSpanContainer = (BoxPane) namespace.get("timeSpanContainer");

        // parameterTableView
        _parameterTableViewTemplate= (ParameterTableViewTemplate) namespace
                .get("parameterTableViewTemplate");

        final Border errorBorder = (Border) namespace.get("errorBorder");
        ListView errorBox = (ListView) namespace.get("errorBox");
        errorBox.setListData(DietPlanEditingController.getInstance()
                .getErrors());
        
        
        DietPlanEditingController.getInstance().getErrors().getListListeners()
		.add(new ListListener.Adapter<String>() {

			@Override
			public void itemInserted(List<String> list, int arg1) {
				errorBorder.setVisible(list
						.getLength() > 0);
			}

			@Override
			public void itemsRemoved(List<String> list, int arg1,
					Sequence<String> arg2) {
				errorBorder.setVisible(list
						.getLength() > 0);
			}
			
			@Override
			public void listCleared(List<String> list)
			{
				errorBorder.setVisible(list.getLength() >0);
			}
		});
        
        
        errorBorder.setVisible((errorBox.getListData().getLength() > 0));

        ButtonPressListener createTimeSpan = new ButtonPressListener()
        {

            public void buttonPressed(Button button)
            {
                addTimeSpan(DietPlanEditingController.getInstance()
                        .createTimeSpan());
                // rebuildUI();
            }
        };

        Button saveButton = (Button) namespace.get("save");
        saveButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            public void buttonPressed(Button button)
            {
                save();
            }
        });

        Button refreshUIButton = (Button) namespace.get("refreshUI");
        refreshUIButton.getButtonPressListeners().add(new ButtonPressListener()
        {

            public void buttonPressed(Button button)
            {
                rebuildUI();
            }
        });

        PushButton createTimeSpanTop = (PushButton) namespace
                .get("createTimeSpanTop");
        PushButton createTimeSpanBottom = (PushButton) namespace
                .get("createTimeSpanBottom");

        createTimeSpanTop.getButtonPressListeners().add(createTimeSpan);
        createTimeSpanBottom.getButtonPressListeners().add(createTimeSpan);
        
        Button validateButton = (Button)namespace.get("validate");
        validateButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            public void buttonPressed(Button button)
            {
                DietPlanEditingController.getInstance().validateDietPlan(true);                
            }
        });
    }

    private void save()
    {
        boolean saved = DietPlanEditingController.getInstance().saveDietPlan();
        if(saved)
        {
            ViewController.getInstance().loadContent(
                    "DietTreatmentDetailView", DietPlanManagementView.this);
        }
        else if(DietPlanEditingController.getInstance().getErrors().getLength() == 0)
        {
            EasyAlerts.error("Es ist ein Fehler beim Speichern des Plans aufgetreten, bitte versuchen Sie es erneut!", EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(), null);
        }
        else
        {
            EasyAlerts.error("Es sind noch Fehler im Plan vorhanden! Bitte korrigieren Sie diese!", EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(), null);
        }
    }

    @Override
    public abstract void onLoad();
    
    public void rebuildUI()
    {
        DietPlanBO dietPlan = DietPlanEditingController.getInstance()
                .getDietPlan();

        _timeSpanContainer.removeAll();
        for (TimeSpanBO timeSpan : dietPlan.getSortedTimeSpans())
        {
            addTimeSpan(timeSpan);
        }
    }

    private void addTimeSpan(TimeSpanBO timeSpan)
    {
        TimeSpanContainer container = new TimeSpanContainer();
        container.setTimeSpan(timeSpan);
        _timeSpanContainer.add(container);
    }

    
    @Override
    public boolean onClose()
    {
    	ParameterTemplateValidator.getInstance().clearCache();
    	return true;
    }
}
