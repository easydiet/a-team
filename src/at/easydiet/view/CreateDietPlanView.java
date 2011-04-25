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
import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietParameterTypeBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionDataTypeBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.domainlogic.DietPlanEditingController;

public class CreateDietPlanView extends EasyDietContentView implements Bindable {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(CreateDietPlanView.class);

	private BoxPane _timeSpanContainer;
	private ParameterTableView _parameterTableView;

	public CreateDietPlanView() {
	}

	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {
		_timeSpanContainer = (BoxPane) namespace.get("timeSpanContainer");

		_parameterTableView = (ParameterTableView) namespace
				.get("dietPlanParameterTableView");

		Button addDietPlanParameterButton = (Button) namespace
				.get("addDietPlanParameters");
		addDietPlanParameterButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						addNewParameters();
					}
				});

		Button removeDietPlanParameterButton = (Button) namespace
				.get("removeDietPlanParameter");
		removeDietPlanParameterButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						removeParameter((DietParameterBO) _parameterTableView
								.getSelectedRow());
					}
				});

		ButtonPressListener createTimeSpan = new ButtonPressListener() {

			public void buttonPressed(Button button) {
				addTimeSpan(DietPlanEditingController.getInstance()
						.createTimeSpan());
				// rebuildUI();
			}
		};

		Button saveButton = (Button) namespace.get("save");
		saveButton.getButtonPressListeners().add(new ButtonPressListener() {
			public void buttonPressed(Button button) {
				DietPlanEditingController.getInstance().saveDietPlan();
				ViewController.getInstance().loadContent(
						"DietTreatmentDetailView", CreateDietPlanView.this);
			}
		});

		Button refreshUIButton = (Button) namespace.get("refreshUI");
		refreshUIButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					public void buttonPressed(Button button) {
						rebuildUI();
					}
				});

		PushButton createTimeSpanTop = (PushButton) namespace
				.get("createTimeSpanTop");
		PushButton createTimeSpanBottom = (PushButton) namespace
				.get("createTimeSpanBottom");

		createTimeSpanTop.getButtonPressListeners().add(createTimeSpan);
		createTimeSpanBottom.getButtonPressListeners().add(createTimeSpan);
	}

	@Override
	public void onLoad() {
		DietPlanEditingController.getInstance().createNew(
				DietTreatmentDetailViewController.getInstance()
						.getDietTreatment());
		DietPlanEditingController.getInstance().refresh();

		List<DietParameterBO> listData = DietPlanEditingController
				.getInstance().getDietPlan().getDietParameters();

		_parameterTableView.setTableData(listData);
		_parameterTableView.initialize();

	}

	public void rebuildUI() {
		DietPlanBO dietPlan = DietPlanEditingController.getInstance()
				.getDietPlan();

		_timeSpanContainer.removeAll();
		for (TimeSpanBO timeSpan : dietPlan.getSortedTimeSpans()) {
			addTimeSpan(timeSpan);
		}
	}

	private void addTimeSpan(TimeSpanBO timeSpan) {
		TimeSpanContainer container = new TimeSpanContainer();
		container.setTimeSpan(timeSpan);
		_timeSpanContainer.add(container);
	}

	@SuppressWarnings("unchecked")
	private void addNewParameters() {
		// TODO: show sheet
		System.out.println("add parameters");

		DietParameterBO newBo = new DietParameterBO();
		
		newBo.setCheckOperator(CheckOperatorBO.BIGGER);
		newBo.setDietParameterType(DietParameterTypeBO.DEFAULT);
		
		ParameterDefinitionBO newDefinition = new ParameterDefinitionBO();
		newDefinition.setName("Alanin");
		newDefinition.setParameterDefinitionId(1);
		
		ParameterDefinitionUnitBO newUnit = new ParameterDefinitionUnitBO();
		newUnit.setName("mg");
		
		ParameterDefinitionUnitBO secondUnit = new ParameterDefinitionUnitBO();
		secondUnit.setName("mg/100g");
		
		newDefinition.addUnit(newUnit);
		newDefinition.addUnit(secondUnit);
		newBo.setParameterDefinition(newDefinition);
		
		
		newUnit.setType(ParameterDefinitionDataTypeBO.NUMBERS);
		newBo.setParameterDefinitionUnit(newUnit);
		
		newBo.setValue("15");
		
		DietParameterBO secondBo = new DietParameterBO();
		
		secondBo.setCheckOperator(CheckOperatorBO.SMALLER);
		secondBo.setDietParameterType(DietParameterTypeBO.DEFAULT);
		secondBo.setParameterDefinition(newDefinition);
		secondBo.setParameterDefinitionUnit(newUnit);
		secondBo.setValue("10");
		
		((List<DietParameterBO>)_parameterTableView.getTableData()).add(newBo);
		((List<DietParameterBO>)_parameterTableView.getTableData()).add(secondBo);
		
		_parameterTableView.validateView();
	}

	private void removeParameter(DietParameterBO dietParameter) {
		// TODO: remove parameter
		((List<DietParameterBO>)_parameterTableView.getTableData()).remove(dietParameter);
		_parameterTableView.validateView();
	}
}
