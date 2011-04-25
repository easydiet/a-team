package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TableView.Column;

import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.domainlogic.DietPlanEditingController;
import at.easydiet.validation.ParameterValidator;
import at.easydiet.view.content.ParameterCellRenderer;

public class CreateDietPlanView extends EasyDietContentView implements Bindable
{ 
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateDietPlanView.class);

    private BoxPane _timeSpanContainer;
    private ParameterTableView _parameterTableView;
    
    
    
    public CreateDietPlanView()
    {
    }
    
    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
        _timeSpanContainer = (BoxPane) namespace.get("timeSpanContainer");
        
        _parameterTableView = (ParameterTableView) namespace.get("dietPlanParameterTableView");
        
        ButtonPressListener createTimeSpan = new ButtonPressListener()
        {
            
            public void buttonPressed(Button button)
            { 
                addTimeSpan(DietPlanEditingController.getInstance().createTimeSpan());
                //rebuildUI();
            }
        };
        
        Button saveButton = (Button)namespace.get("save");
        saveButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            public void buttonPressed(Button button)
            {
                DietPlanEditingController.getInstance().saveDietPlan();
                ViewController.getInstance().loadContent("DietTreatmentDetailView", CreateDietPlanView.this);
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
        
        PushButton createTimeSpanTop = (PushButton) namespace.get("createTimeSpanTop");
        PushButton createTimeSpanBottom = (PushButton) namespace.get("createTimeSpanBottom");
        
        createTimeSpanTop.getButtonPressListeners().add(createTimeSpan);
        createTimeSpanBottom.getButtonPressListeners().add(createTimeSpan);
    }
    
    @Override
    public void onLoad()
    {
        DietPlanEditingController.getInstance().createNew(DietTreatmentDetailViewController.getInstance().getDietTreatment());
        DietPlanEditingController.getInstance().refresh();
        
        List<DietParameterBO> listData = DietPlanEditingController.getInstance().getDietPlan().getDietParameters();
        
        _parameterTableView.setTableData(listData);
        ParameterValidator parameterValidator = new ParameterValidator();
        parameterValidator.isValid(listData);
        
        for(Column col : _parameterTableView.getColumns())
        {
        	((ParameterCellRenderer)col.getCellRenderer()).setValidator(parameterValidator);
        }
    }
    
    public void rebuildUI()
    {
        DietPlanBO dietPlan = DietPlanEditingController.getInstance().getDietPlan();
        
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
}
