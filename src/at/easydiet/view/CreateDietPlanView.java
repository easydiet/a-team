package at.easydiet.view;

import java.net.URL;
import java.util.Comparator;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ListListener;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Filter;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BindType;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentDataListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.ListViewBindingListener;
import org.apache.pivot.wtk.ListViewListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.ListView.ItemBindMapping;
import org.apache.pivot.wtk.ListView.ItemEditor;
import org.apache.pivot.wtk.ListView.ItemRenderer;
import org.apache.pivot.wtk.ListView.ListDataBindMapping;
import org.apache.pivot.wtk.ListView.SelectMode;
import org.apache.pivot.collections.List;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.domainlogic.DietPlanEditingController;
import at.easydiet.validation.ParameterValidator;

public class CreateDietPlanView extends EasyDietContentView implements Bindable {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(CreateDietPlanView.class);

	protected BoxPane _timeSpanContainer;
	protected ParameterTableView _dietPlanParameterTableView;

	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {
		_timeSpanContainer = (BoxPane) namespace.get("timeSpanContainer");

		// parameterTableView
		_dietPlanParameterTableView = (ParameterTableView) namespace
				.get("dietPlanParameterTableView");

		final Border errorBorder = (Border) namespace.get("errorBorder");
		ListView errorBox = (ListView) namespace.get("errorBox");
		errorBox.setListData(DietPlanEditingController.getInstance()
				.getErrors());
		
		DietPlanEditingController.getInstance().getErrors().getListListeners()
		.add(new ListListener<String>() {

			@Override
			public void comparatorChanged(List<String> arg0,
					Comparator<String> arg1) {
				// TODO Auto-generated method stub
				System.out.println("test");
				LOG.debug(23);
			}

			@Override
			public void itemInserted(List<String> arg0, int arg1) {
				// TODO Auto-generated method stub
				System.out.println("test");
				LOG.debug(24);
			}

			@Override
			public void itemUpdated(List<String> arg0, int arg1,
					String arg2) {
				// TODO Auto-generated method stub
				System.out.println("test");
				LOG.debug(25);
			}

			@Override
			public void itemsRemoved(List<String> arg0, int arg1,
					Sequence<String> arg2) {
				// TODO Auto-generated method stub
				System.out.println("test");
				LOG.debug(26);
				errorBorder.setVisible(arg0
						.getLength() > 0);
			}

			@Override
			public void listCleared(List<String> arg0) {
				// TODO Auto-generated method stub
				System.out.println("test");
				LOG.debug(26);
			}

		});


		errorBox.getListViewListeners().add(new ListViewListener() {

			@Override
			public void selectModeChanged(ListView listview,
					SelectMode selectmode) {
				LOG.debug(1);

			}

			@Override
			public void listDataChanged(ListView arg0, List<?> arg1) {
				// TODO Auto-generated method stub
				LOG.debug(2);
			}

			@Override
			public void itemRendererChanged(ListView listview,
					ItemRenderer itemrenderer) {
				// TODO Auto-generated method stub
				LOG.debug(3);
			}

			@Override
			public void itemEditorChanged(ListView listview,
					ItemEditor itemeditor) {
				// TODO Auto-generated method stub
				LOG.debug(4);
			}

			@Override
			public void disabledItemFilterChanged(ListView arg0, Filter<?> arg1) {
				// TODO Auto-generated method stub
				LOG.debug(5);
			}

			@Override
			public void disabledCheckmarkFilterChanged(ListView arg0,
					Filter<?> arg1) {
				// TODO Auto-generated method stub
				LOG.debug(6);
			}

			@Override
			public void checkmarksEnabledChanged(ListView listview) {
				// TODO Auto-generated method stub
				LOG.debug(7);
			}
		});

		errorBox.getComponentDataListeners().add(new ComponentDataListener() {

			@Override
			public void valueUpdated(Component component, String s, Object obj) {
				LOG.debug(8);

			}

			@Override
			public void valueRemoved(Component component, String s, Object obj) {
				// TODO Auto-generated method stub
				LOG.debug(9);
			}

			@Override
			public void valueAdded(Component component, String s) {
				// TODO Auto-generated method stub
				LOG.debug(10);
			}
		});

		errorBox.getListViewBindingListeners().add(
				new ListViewBindingListener() {

					@Override
					public void selectedItemsKeyChanged(ListView listview,
							String s) {
						// TODO Auto-generated method stub
						LOG.debug(11);
					}

					@Override
					public void selectedItemsBindTypeChanged(ListView listview,
							BindType bindtype) {
						// TODO Auto-generated method stub
						LOG.debug(12);
					}

					@Override
					public void selectedItemsBindMappingChanged(
							ListView listview, ItemBindMapping itembindmapping) {
						// TODO Auto-generated method stub
						LOG.debug(13);
					}

					@Override
					public void selectedItemKeyChanged(ListView listview,
							String s) {
						// TODO Auto-generated method stub
						LOG.debug(14);
					}

					@Override
					public void selectedItemBindTypeChanged(ListView listview,
							BindType bindtype) {
						// TODO Auto-generated method stub
						LOG.debug(15);
					}

					@Override
					public void selectedItemBindMappingChanged(
							ListView listview, ItemBindMapping itembindmapping) {
						// TODO Auto-generated method stub
						LOG.debug(16);
					}

					@Override
					public void listDataKeyChanged(ListView listview, String s) {
						// TODO Auto-generated method stub
						LOG.debug(17);
					}

					@Override
					public void listDataBindTypeChanged(ListView listview,
							BindType bindtype) {
						// TODO Auto-generated method stub
						LOG.debug(18);
					}

					@Override
					public void listDataBindMappingChanged(ListView listview,
							ListDataBindMapping listdatabindmapping) {
						// TODO Auto-generated method stub
						LOG.debug(19);
					}

					@Override
					public void checkedItemsKeyChanged(ListView listview,
							String s) {
						// TODO Auto-generated method stub
						LOG.debug(20);
					}

					@Override
					public void checkedItemsBindTypeChanged(ListView listview,
							BindType bindtype) {
						// TODO Auto-generated method stub
						LOG.debug(21);
					}

					@Override
					public void checkedItemsBindMappingChanged(
							ListView listview, ItemBindMapping itembindmapping) {
						// TODO Auto-generated method stub
						LOG.debug(22);
					}
				});

				
		//
		// errorBox.getListViewItemListeners().add(
		// new ListViewItemListener.Adapter()
		// {
		// @Override
		// public void itemsRemoved(ListView listView, int index,
		// int count)
		// {
		// errorBorder.setVisible((listView.getListData()
		// .getLength() > 0));
		// }
		//
		// @Override
		// public void itemInserted(ListView listView, int index)
		// {
		// errorBorder.setVisible((listView.getListData()
		// .getLength() > 0));
		// }
		// });
		errorBorder.setVisible((errorBox.getListData().getLength() > 0));

		// start:parameterTableView
		// add button listeners
		Button addDietPlanParameterButton = (Button) namespace
				.get("addDietPlanParameters");
		addDietPlanParameterButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					public void buttonPressed(Button arg0) {
						addNewParameters();
					}
				});

		Button removeDietPlanParameterButton = (Button) namespace
				.get("removeDietPlanParameter");
		removeDietPlanParameterButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					public void buttonPressed(Button arg0) {
						removeParameter((DietParameterBO) _dietPlanParameterTableView
								.getSelectedRow());
					}
				});
		// end:parameterTableView

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

		Button validateButton = (Button) namespace.get("validate");
		validateButton.getButtonPressListeners().add(new ButtonPressListener() {
			public void buttonPressed(Button button) {
				DietPlanEditingController.getInstance().validateDietPlan();
			}
		});
	}

	@Override
	public void onLoad() {
		DietPlanEditingController.getInstance().createNew(
				DietTreatmentDetailViewController.getInstance()
						.getDietTreatment());
		DietPlanEditingController.getInstance().refresh();

		// start: parameterTableView
		_dietPlanParameterTableView
				.setParameterProvider(DietPlanEditingController.getInstance()
						.getDietPlan());
		_dietPlanParameterTableView.initialize();
		// end: parameterTableView
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

	// start:parameterTableView
	/**
	 * Adds a new parameter into the view
	 */
	private void addNewParameters() {
		_dietPlanParameterTableView.addParameterTemplate();
	}

	/**
	 * Removes a parameter from the view
	 * 
	 * @param dietParameter
	 *            parameter to remove
	 */
	private void removeParameter(DietParameterBO dietParameter) {
		_dietPlanParameterTableView.remove(dietParameter);
	}

	// end:ParameterTableView

	@Override
	public boolean onClose() {
		ParameterValidator.getInstance().clearCache();
		return true;
	}
}
