package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ListListener;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextAreaContentListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.TableView.RowEditor;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.DietTreatmentSystemUserBO;
import at.easydiet.businessobjects.PatientStateBO;
import at.easydiet.businessobjects.SystemUserBO;
import at.easydiet.businessobjects.SystemUserFunctionBO;
import at.easydiet.domainlogic.DietPlanEditingController;
import at.easydiet.domainlogic.DietTreatmentEditingController;
import at.easydiet.domainlogic.SystemUserController;

public abstract class DietTreatmentManagementView extends EasyDietContentView
		implements Bindable {

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(DietTreatmentManagementView.class);

	protected boolean _saved;

	protected TextInput _name;
	protected ParameterTableViewTemplate _parameterTableViewTemplate;
	protected TableView _patientState;
	protected TextArea _shortDescription;
	protected CalendarButton _startDateButton;
	protected CalendarButton _endDateButton;
	private Label _durationLabel;

	private Button _addSystemUser;

	private Button _removeSystemUser;

	protected TableView _systemUserTable;

	private EasyTableViewRowEditor _editor;

	protected ListButton _systemUserListButton;

	protected ListButton _systemUserFunctionListButton;

	@Override
	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {
		// parameterTableView
		_parameterTableViewTemplate = (ParameterTableViewTemplate) namespace
				.get("parameterTableViewTemplate");

		// error box
		final Border errorBorder = (Border) namespace.get("errorBorder");
		ListView errorBox = (ListView) namespace.get("errorBox");
		errorBox.setListData(DietTreatmentEditingController.getInstance()
				.getErrors());

		DietTreatmentEditingController.getInstance().getErrors()
				.getListListeners().add(new ListListener.Adapter<String>() {

					@Override
					public void itemInserted(List<String> list, int arg1) {
						errorBorder.setVisible(list.getLength() > 0);
					}

					@Override
					public void itemsRemoved(List<String> list, int arg1,
							Sequence<String> arg2) {
						errorBorder.setVisible(list.getLength() > 0);
					}

					@Override
					public void listCleared(List<String> list) {
						errorBorder.setVisible(list.getLength() > 0);
					}
				});

		errorBorder.setVisible((errorBox.getListData().getLength() > 0));

		// define buttons
		Button saveButton = (Button) namespace.get("save");
		saveButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				// save data
				save();

			}
		});

		_startDateButton = (CalendarButton) namespace.get("startDate");
		_endDateButton = (CalendarButton) namespace.get("endDate");
		_durationLabel = (Label) namespace.get("durationLabel");

		Button validateButton = (Button) namespace.get("validate");
		validateButton.getButtonPressListeners().add(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				DietTreatmentEditingController.getInstance()
						.validateDietTreatment();

			}
		});

		CalendarButtonSelectionListener dateChangedListener = new CalendarButtonSelectionListener() {
			public void selectedDateChanged(CalendarButton calendarButton,
					CalendarDate previousSelectedDate) {
				LOG.debug(calendarButton == _startDateButton);
				LOG.debug(calendarButton == _endDateButton);
				LOG.debug(calendarButton.getSelectedDate());
				CalendarDate start = _startDateButton.getSelectedDate();
				getDietTreatment().setStart(start);

				CalendarDate end = _endDateButton.getSelectedDate();
				if (end.compareTo(start) < 0) {
					_endDateButton.setSelectedDate(new CalendarDate(start
							.toCalendar()));
					return;
				}

				int days = end.subtract(start);
				getDietTreatment().setDuration(days);

				updateDurationLabel();
			}
		};

		_startDateButton.getCalendarButtonSelectionListeners().add(
				dateChangedListener);
		_endDateButton.getCalendarButtonSelectionListeners().add(
				dateChangedListener);
		
		_name = (TextInput) namespace.get("name");
		_name.getTextInputContentListeners().add(
				new TextInputContentListener.Adapter() {
					@Override
					public void textChanged(TextInput textInput) {
						getDietTreatment().setName(textInput.getText());
					}
				});

		_shortDescription = (TextArea) namespace.get("shortDescription");
		_shortDescription.getTextAreaContentListeners().add(
				new TextAreaContentListener.Adapter() {
					@Override
					public void textChanged(TextArea textInput) {
						getDietTreatment().setShortDescription(
								textInput.getText());
					}
				});

		_systemUserTable = (TableView) namespace.get("systemUsers");

		_addSystemUser = (Button) namespace.get("addSystemUser");

		_addSystemUser.getButtonPressListeners().add(new ButtonPressListener() {

			public void buttonPressed(Button arg0) {
				addNewSystemUser();
			}
		});

		_removeSystemUser = (Button) namespace.get("removeSystemUser");
		_removeSystemUser.getButtonPressListeners().add(
				new ButtonPressListener() {
					public void buttonPressed(Button arg0) {
						removeSystemUser((DietTreatmentSystemUserBO) _systemUserTable
								.getSelectedRow());
					}
				});

		_editor = (EasyTableViewRowEditor) _systemUserTable.getRowEditor();

		// add systemusers to the ListButton
		_editor.getRowEditorListeners().add(
				new EasyTableViewRowEditor.RowEditorListener.Adapter() {
					@Override
					public Vote previewEditRow(RowEditor rowEditor,
							TableView tableView, int rowIndex, int columnIndex) {
						// update list of units
						_systemUserListButton = (ListButton) _editor
								.getCellEditors().get("systemUser.name");
						DietTreatmentSystemUserBO row = (DietTreatmentSystemUserBO) _systemUserTable
								.getTableData().get(rowIndex);

						_systemUserListButton
								.setListData((org.apache.pivot.collections.List<?>) SystemUserController
										.getInstance().getAllUsers());

						for (int i = 0; i < _systemUserListButton.getListData()
								.getLength(); i++) {
							SystemUserBO systemUser = (SystemUserBO) _systemUserListButton
									.getListData().get(i);
							if (systemUser.getName().equalsIgnoreCase(
									row.getSystemUser().getName())) {
								_systemUserListButton.setSelectedIndex(i);
								layout();
								break;
							}
						}

						return super.previewEditRow(rowEditor, tableView,
								rowIndex, columnIndex);
					}
				});

		_editor.getRowEditorListeners().add(
				new EasyTableViewRowEditor.RowEditorListener.Adapter() {
					@Override
					public Vote previewEditRow(RowEditor rowEditor,
							TableView tableView, int rowIndex, int columnIndex) {
						// update list of units
						_systemUserFunctionListButton = (ListButton) _editor
								.getCellEditors().get("function.name");
						DietTreatmentSystemUserBO row = (DietTreatmentSystemUserBO) _systemUserTable
								.getTableData().get(rowIndex);

						_systemUserFunctionListButton
								.setListData(SystemUserFunctionBO
										.getAllValues());

						for (int i = 0; i < _systemUserFunctionListButton
								.getListData().getLength(); i++) {
							SystemUserFunctionBO systemUserFunction = (SystemUserFunctionBO) _systemUserFunctionListButton
									.getListData().get(i);
							if (systemUserFunction == row.getFunction()) {
								_systemUserFunctionListButton
										.setSelectedIndex(i);
								layout();
								break;
							}
						}

						return super.previewEditRow(rowEditor, tableView,
								rowIndex, columnIndex);
					}
				});

		//assignment
		_patientState = (TableView) namespace.get("patientState");

		_patientState.setTableData(PatientDetailViewController.getInstance()
				.getAllDiagnosis());

		_patientState.getTableViewSelectionListeners().add(
				new TableViewSelectionListener.Adapter() {

					@Override
					public void selectedRowChanged(TableView table, Object row) {
						changeAssignment((PatientStateBO) row);
					}
				});

	}

	/**
	 * Change the chosen assignment diagnosis
	 * @param patientState the state to use as diagnosis
	 */
	protected void changeAssignment(PatientStateBO patientState) {
		DietTreatmentEditingController.getInstance().changeAssignment(patientState);

	}

	/**
	 * Remove the selected system user from the list
	 * @param systemUser the user to remove
	 */
	protected void removeSystemUser(DietTreatmentSystemUserBO systemUser) {
		DietTreatmentEditingController.getInstance().removeSystemUser(
				systemUser);

	}

	/**
	 * Add a new system user to the list
	 */
	protected void addNewSystemUser() {
		DietTreatmentEditingController.getInstance().addNewSystemUser();
	}

	@Override
	public abstract void onLoad();

	@Override
	public boolean onClose() {
		// want to cancel edit?
		if (!_saved) {
			DietTreatmentEditingController.getInstance().revertChanges();
		}
		
		//clear all errors
		DietTreatmentEditingController.getInstance().getErrors().clear();
		
		return super.onClose();
	}

	protected void updateDurationLabel() {
		int days = getDietTreatment().getDuration();
		String dayLabel = days > 0 ? "Tage" : "Tag";
		_durationLabel.setText((days + 1) + " " + dayLabel);
	}

	protected DietTreatmentBO getDietTreatment() {
		return DietTreatmentEditingController.getInstance().getDietTreatment();
	}

	private void save() {
		_saved = DietTreatmentEditingController.getInstance()
				.saveDietTreatment();
		if (_saved) {
			DietTreatmentDetailViewController.getInstance().setDietTreatment(
					getDietTreatment());
			ViewController.getInstance().loadContent("DietTreatmentDetailView",
					DietTreatmentManagementView.this);
		} else if (DietPlanEditingController.getInstance().getErrors()
				.getLength() == 0) {
			EasyAlerts
					.error("Es ist ein Fehler beim Speichern der Behandlung aufgetreten, bitte versuchen Sie es erneut!",
							EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
							null);
		} else {
			EasyAlerts
					.error("Es sind noch Fehler im Diätplan vorhanden! Bitte korrigieren Sie diese!",
							EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
							null);
		}
	}

}
