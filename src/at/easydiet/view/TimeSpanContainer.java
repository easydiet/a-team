package at.easydiet.view;

import java.io.IOException;
import java.util.Iterator;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.DefaultProperty;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.ImmutableIterator;
import org.apache.pivot.util.ListenerList;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.DialogCloseListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Orientation;

import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietParameterTypeBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionDataTypeBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.domainlogic.DietPlanEditingController;

@DefaultProperty("mealContainers")
public class TimeSpanContainer extends BoxPane {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(TimeSpanContainer.class);

	private CalendarButton _startDateButton;
	private CalendarButton _endDateButton;
	private Label _durationLabel;
	private BoxPane _mealBox;
	private Button _deleteButton;

	private Button _addTimeSpanParameterButton;
	private Button _removeTimeSpanParameterButton;

	private TimeSpanBO _timeSpan;

	private ArrayList<MealContainer> _mealContainers;
	private MealContainerSequence _mealContainerSequence = new MealContainerSequence();
	private TimeSpanContainerListenerList _timeSpanContainerListeners = new TimeSpanContainerListenerList();

	private ParameterTableView _timeSpanParameterTableView;

	/**
	 * Gets the timeSpan.
	 * 
	 * @return the timeSpan
	 */
	public TimeSpanBO getTimeSpan() {
		return _timeSpan;
	}

	/**
	 * Sets the timeSpan.
	 * 
	 * @param timeSpan
	 *            the timeSpan to set
	 */
	public void setTimeSpan(TimeSpanBO timeSpan) {
		_timeSpan = timeSpan;
		
		//tableview
		List<DietParameterBO> listData = _timeSpan.getDietParameters();
		_timeSpanParameterTableView.setTableData(listData);
		
		refreshUI();
	}

	private void refreshUI() {
		_startDateButton.setSelectedDate(_timeSpan.getStartDate());
		_endDateButton.setSelectedDate(_timeSpan.getEndDate());
		_mealBox.removeAll();
		for (MealBO meal : _timeSpan.getMeals()) {
			addMeal(meal);
		}
	}

	public final class MealContainerSequence implements
			Sequence<MealContainer>, Iterable<MealContainer> {

		private MealContainerSequence() {
		}

		public int add(MealContainer mealContainer) {
			int index = getLength();
			insert(mealContainer, index);

			return index;
		}

		public void insert(MealContainer mealContainer, int index) {
			if (mealContainer == null) {
				throw new IllegalArgumentException("mealContainer is null.");
			}

			_mealContainers.insert(mealContainer, index);
			TimeSpanContainer.this._mealBox.insert(mealContainer, index);

			// Notify listeners
			_timeSpanContainerListeners.mealContainerInserted(
					TimeSpanContainer.this, index);
		}

		public MealContainer update(int index, MealContainer mealContainer) {
			throw new UnsupportedOperationException();
		}

		public int remove(MealContainer mealContainer) {
			int index = indexOf(mealContainer);
			if (index != -1) {
				remove(index, 1);
			}

			return index;
		}

		public Sequence<MealContainer> remove(int index, int count) {
			Sequence<MealContainer> removed = _mealContainers.remove(index,
					count);
			TimeSpanContainer.this._mealBox.remove(index, count);

			if (count > 0) {
				/*
				 * for (int i = 0, n = removed.getLength(); i < n; i++) {
				 * MealContainer container = removed.get(i); }
				 */

				_timeSpanContainerListeners.mealContainerRemoved(
						TimeSpanContainer.this, index, removed);
			}

			return removed;
		}

		public MealContainer get(int index) {
			return _mealContainers.get(index);
		}

		public int indexOf(MealContainer toolbarButton) {
			return _mealContainers.indexOf(toolbarButton);
		}

		public int getLength() {
			return _mealContainers.getLength();
		}

		public Iterator<MealContainer> iterator() {
			return new ImmutableIterator<MealContainer>(
					_mealContainers.iterator());
		}
	}

	public final class TimeSpanContainerListenerList extends
			ListenerList<TimeSpanContainerListener> {
		public void mealContainerInserted(TimeSpanContainer container, int index) {
			for (TimeSpanContainerListener listener : this) {
				listener.mealContainerInserted(container, index);
			}
		}

		public void mealContainerRemoved(TimeSpanContainer container,
				int index, Sequence<MealContainer> mealContainer) {
			for (TimeSpanContainerListener listener : this) {
				listener.mealContainerRemoved(container, index, mealContainer);
			}
		}
	}

	public TimeSpanContainer() {
		this(new ArrayList<MealContainer>());
	}

	public MealContainerSequence getMealContainers() {
		return _mealContainerSequence;
	}

	public TimeSpanContainerListenerList getTimeSpanContainerListeners() {
		return _timeSpanContainerListeners;
	}

	public TimeSpanContainer(Sequence<MealContainer> mealContainers) {
		if (mealContainers == null) {
			throw new IllegalArgumentException("mealContainers is null");
		}

		_mealContainers = new ArrayList<MealContainer>();

		setOrientation(Orientation.VERTICAL);
		getStyles().put("fill", true);
		getStyles().put("spacing", 5);

		BXMLSerializer serializer = new BXMLSerializer();
		try {
			Border content = (Border) serializer.readObject(
					TimeSpanContainer.class, "TimeSpanContainerContent.xml");
			add(content);
			_startDateButton = (CalendarButton) serializer.getNamespace().get(
					"startDate");
			_endDateButton = (CalendarButton) serializer.getNamespace().get(
					"endDate");
			_durationLabel = (Label) serializer.getNamespace().get(
					"durationLabel");
			_deleteButton = (Button) serializer.getNamespace().get(
					"deleteButton");

			// start parameterView
			_timeSpanParameterTableView = (ParameterTableView) serializer
					.getNamespace().get("timeSpanParameterTableView");
			_timeSpanParameterTableView.initialize();

			_addTimeSpanParameterButton = (Button) serializer.getNamespace()
					.get("addTimeSpanParameters");
			_addTimeSpanParameterButton.getButtonPressListeners().add(
					new ButtonPressListener() {

						@Override
						public void buttonPressed(Button arg0) {
							addNewParameters();
						}
					});

			_removeTimeSpanParameterButton = (Button) serializer.getNamespace()
					.get("removeTimeSpanParameter");
			_removeTimeSpanParameterButton.getButtonPressListeners().add(
					new ButtonPressListener() {

						@Override
						public void buttonPressed(Button arg0) {
							removeParameter((DietParameterBO) _timeSpanParameterTableView
									.getSelectedRow());
						}
					});
			// end parameterview

			_mealBox = (BoxPane) serializer.getNamespace().get("mealBox");

			// listeners
			_deleteButton.getButtonPressListeners().add(
					new ButtonPressListener() {

						public void buttonPressed(Button button) {
							EasyAlerts
									.warning(
											"Wollen Sie diesen Zeitraum wirklich löschen?",
											EasyAlerts.YES_NO, getWindow(),
											new DialogCloseListener() {

												public void dialogClosed(
														Dialog dialog,
														boolean modal) {
													if (((Alert) dialog)
															.getSelectedOption()
															.equals(EasyAlerts.YES)) {
														deleteTimeSpan();
													}
												}

											});
						}
					});

			ButtonPressListener createMeal = new ButtonPressListener() {
				public void buttonPressed(Button button) {
					MealBO meal = DietPlanEditingController.getInstance()
							.createMeal(_timeSpan);
					addMeal(meal);
				}
			};

			Button createMealTop = (Button) serializer.getNamespace().get(
					"createMealTop");
			Button createMealBottom = (Button) serializer.getNamespace().get(
					"createMealBottom");

			createMealTop.getButtonPressListeners().add(createMeal);
			createMealBottom.getButtonPressListeners().add(createMeal);

			CalendarButtonSelectionListener updateDurationListener = new CalendarButtonSelectionListener() {

				public void selectedDateChanged(CalendarButton calendarButton,
						CalendarDate previousSelectedDate) {
					CalendarDate start = _startDateButton.getSelectedDate();
					CalendarDate end = _endDateButton.getSelectedDate();

					if (end.compareTo(start) < 0) {
						_endDateButton.setSelectedDate(new CalendarDate(start
								.toCalendar()));
						return;
					}

					int days = end.subtract(start);
					_timeSpan.setDuration(days);
					String dayLabel = days > 0 ? "Tage" : "Tag";
					_durationLabel.setText((days + 1) + " " + dayLabel);
				}
			};
			_startDateButton.getCalendarButtonSelectionListeners().add(
					updateDurationListener);
			_endDateButton.getCalendarButtonSelectionListeners().add(
					updateDurationListener);
		} catch (IOException e) {
			LOG.debug(e);
		} catch (SerializationException e) {
			LOG.debug(e);
		}
	}

	protected void addMeal(MealBO meal) {
		MealContainer mealContainer = new MealContainer();
		mealContainer.setMeal(meal);
		_mealBox.add(mealContainer);
	}

	private void deleteTimeSpan() {
		getParent().remove(this);
		_timeSpan.getDietPlan().removeTimeSpans(_timeSpan);
	}

	@SuppressWarnings("unchecked")
	private void addNewParameters() {
		// TODO: show sheet
		System.out.println("add parameters");

		DietParameterBO newBo = new DietParameterBO();

		newBo.setCheckOperator(CheckOperatorBO.SMALLER);
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

		secondBo.setCheckOperator(CheckOperatorBO.BIGGER);
		secondBo.setDietParameterType(DietParameterTypeBO.DEFAULT);
		secondBo.setParameterDefinition(newDefinition);
		secondBo.setParameterDefinitionUnit(newUnit);
		secondBo.setValue("10");

		((List<DietParameterBO>) _timeSpanParameterTableView.getTableData())
				.add(newBo);
		((List<DietParameterBO>) _timeSpanParameterTableView.getTableData())
				.add(secondBo);

		_timeSpanParameterTableView.validateView();
	}

	@SuppressWarnings("unchecked")
	private void removeParameter(DietParameterBO dietParameter) {
		// TODO: remove parameter
		((List<DietParameterBO>) _timeSpanParameterTableView.getTableData())
				.remove(dietParameter);
		_timeSpanParameterTableView.validateView();
	}

}
