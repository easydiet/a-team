package at.easydiet.view;

import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.ListViewItemListener;
import org.apache.pivot.wtk.PushButton;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.domainlogic.DietPlanEditingController;

public class CreateDietPlanView extends EasyDietContentView implements Bindable {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(CreateDietPlanView.class);

	private BoxPane _timeSpanContainer;
	private ParameterTableView _dietPlanParameterTableView;

	public CreateDietPlanView() {
	}

	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {
		
		//parameterTableView
		_dietPlanParameterTableView = (ParameterTableView)namespace.get("dietPlanParameterTableView");
		
		_timeSpanContainer = (BoxPane) namespace.get("timeSpanContainer");

		final Border errorBorder = (Border) namespace.get("errorBorder");
		ListView errorBox = (ListView) namespace.get("errorBox");
		errorBox.setListData(DietPlanEditingController.getInstance()
				.getErrors());
		errorBox.getListViewItemListeners().add(
				new ListViewItemListener.Adapter() {
					@Override
					public void itemsRemoved(ListView listView, int index,
							int count) {
						errorBorder.setVisible((listView.getListData()
								.getLength() > 0));
					}

					@Override
					public void itemInserted(ListView listView, int index) {
						errorBorder.setVisible((listView.getListData()
								.getLength() > 0));
					}
				});
		errorBorder.setVisible((errorBox.getListData().getLength() > 0));

		//start:parameterTableView
		//add button listeners
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
						removeParameter((DietParameterBO) _dietPlanParameterTableView
								.getSelectedRow());
					}
				});
		//end:parameterTableView
		
		
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

		//start: parameterTableView
		_dietPlanParameterTableView.setParameterProvider(DietPlanEditingController.getInstance().getDietPlan());
		_dietPlanParameterTableView.initialize();
		//end: parameterTableView
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

	//start:parameterTableView
	/**
	 * Adds a new parameter into the view
	 */
	private void addNewParameters() {
		_dietPlanParameterTableView.addParameterTemplate();
	}

	/**
	 * Removes a parameter from the view
	 * @param dietParameter parameter to remove
	 */
	private void removeParameter(DietParameterBO dietParameter) {
		_dietPlanParameterTableView.remove(dietParameter);
	}
	//end:ParameterTableView
}
