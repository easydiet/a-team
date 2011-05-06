package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ListListener;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextAreaContentListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.domainlogic.DietTreatmentEditingController;

public abstract class DietTreatmentManagementView extends EasyDietContentView
		implements Bindable {

	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(DietTreatmentManagementView.class);

	protected boolean _saved;

	protected TextInput _name;
	protected ParameterTableViewTemplate _parameterTableViewTemplate;
	protected TextArea _shortDescription;
	private CalendarButton _startDateButton;
	private CalendarButton _endDateButton;
	private Label _durationLabel;

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
				getDietTreatment();
				//TODO: revert
//				if (DietTreatmentEditingController.getInstance()
//						.saveDietTreatment()) {
//					ViewController.getInstance().loadContent(
//							"PatientDetailView",
//							DietTreatmentManagementView.this);
//					_saved = true;
//				} else {
//					EasyAlerts
//							.error("Fehler beim Speichern der Daten, versuchen Sie es bitte erneut!",
//									EasyAlerts.OK_ONLY, EasyAlerts.OK,
//									getWindow(), null);
//				}

			}
		});
		
		_startDateButton = (CalendarButton) namespace.get("startDate");
		_endDateButton = (CalendarButton) namespace.get("endDate");
		_durationLabel = (Label) namespace.get("durationLabel");

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
                new TextInputContentListener.Adapter()
                {
                    @Override
                    public void textChanged(TextInput textInput)
                    {
                        getDietTreatment().setName(textInput.getText());
                    }
                });
        
        _shortDescription = (TextArea) namespace.get("shortDescription");
        _shortDescription.getTextAreaContentListeners().add(
                new TextAreaContentListener.Adapter()
                {
                    @Override
                    public void textChanged(TextArea textInput)
                    {
                    	//TODO: short description
                        //getDietTreatment().setDescription(textInput.getText());
                    }
                });
	}

	@Override
	public abstract void onLoad();

	@Override
	public boolean onClose() {
		// want to cancel edit?
		if (!_saved) {
			DietTreatmentEditingController.getInstance().revertChanges();
		}
		return super.onClose();
	}
	
	protected void updateDurationLabel()
    {
        int days = getDietTreatment().getDuration();
        String dayLabel = days > 0 ? "Tage" : "Tag";
        _durationLabel.setText((days + 1) + " " + dayLabel);
        DietTreatmentEditingController.getInstance().validateDietTreatment();
    }
	
	protected DietTreatmentBO getDietTreatment() {
		return DietTreatmentEditingController.getInstance().getDietTreatment();
	}
}
