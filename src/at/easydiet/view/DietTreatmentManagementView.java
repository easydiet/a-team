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

/**
 * This is the abstract background class for creating and editing a
 * {@link DietTreatmentBO}
 */
public abstract class DietTreatmentManagementView extends EasyDietContentView
        implements Bindable
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(DietTreatmentManagementView.class);

    /**
     * Stores if the {@link DietTreatmentBO} is saved or not
     */
    protected boolean                            _saved;

    /**
     * Stores the input field for the name from the GUI
     */
    protected TextInput                          _name;

    /**
     * Stores the parameter table view from the GUI
     */
    protected ParameterTableViewTemplate         _parameterTableViewTemplate;

    /**
     * Stores the patient state table view from the GUI
     */
    protected TableView                          _patientState;

    /**
     * Stores the input field for the short description from the GUI
     */
    protected TextArea                           _shortDescription;
    /**
     * Stores the start date button from the GUI
     */
    protected CalendarButton                     _startDateButton;
    /**
     * Stores the end date button from the GUI
     */
    protected CalendarButton                     _endDateButton;

    /**
     * Stores the label to show the duration of a {@link DietTreatmentBO} from
     * the GUI
     */
    private Label                                _durationLabel;

    /**
     * Stores the button to add a system user
     */
    private Button                               _addSystemUser;

    /**
     * Stores the button to remove a system user
     */
    private Button                               _removeSystemUser;

    /**
     * Stores the table view to show {@link SystemUserBO}s
     */
    protected TableView                          _systemUserTable;

    /**
     * Stores the editor used to edit the
     * {@link DietTreatmentManagementView#_systemUserTable}
     */
    private EasyTableViewRowEditor               _editor;

    /**
     * Stores the list button, that shows the available {@link SystemUserBO}s,
     * used while editing {@link DietTreatmentManagementView#_systemUserTable}
     */
    protected ListButton                         _systemUserListButton;

    /**
     * Stores the list button, that shows the {@link SystemUserFunctionBO}s,
     * used while editing {@link DietTreatmentManagementView#_systemUserTable}
     */
    protected ListButton                         _systemUserFunctionListButton;

    /**
     * 
     * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map,
     *      java.net.URL, org.apache.pivot.util.Resources)
     */
    @Override
    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
        // parameterTableView
        _parameterTableViewTemplate = (ParameterTableViewTemplate) namespace
                .get("parameterTableViewTemplate");

        // error box
        final Border errorBorder = (Border) namespace.get("errorBorder");
        ListView errorBox = (ListView) namespace.get("errorBox");
        errorBox.setListData(DietTreatmentEditingController.getInstance()
                .getErrors());

        DietTreatmentEditingController.getInstance().getErrors()
                .getListListeners().add(new ListListener.Adapter<String>()
                {

                    @Override
                    public void itemInserted(List<String> list, int arg1)
                    {
                        errorBorder.setVisible(list.getLength() > 0);
                    }

                    @Override
                    public void itemsRemoved(List<String> list, int arg1,
                            Sequence<String> arg2)
                    {
                        errorBorder.setVisible(list.getLength() > 0);
                    }

                    @Override
                    public void listCleared(List<String> list)
                    {
                        errorBorder.setVisible(list.getLength() > 0);
                    }
                });

        errorBorder.setVisible((errorBox.getListData().getLength() > 0));

        // define buttons
        Button saveButton = (Button) namespace.get("save");
        saveButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button button)
            {
                // save data
                save();

            }
        });

        _startDateButton = (CalendarButton) namespace.get("startDate");
        _endDateButton = (CalendarButton) namespace.get("endDate");
        _durationLabel = (Label) namespace.get("durationLabel");

        Button validateButton = (Button) namespace.get("validate");
        validateButton.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button button)
            {
                DietTreatmentEditingController.getInstance()
                        .validateDietTreatment();

            }
        });

        CalendarButtonSelectionListener dateChangedListener = new CalendarButtonSelectionListener()
        {
            public void selectedDateChanged(CalendarButton calendarButton,
                    CalendarDate previousSelectedDate)
            {
                LOG.debug(calendarButton == _startDateButton);
                LOG.debug(calendarButton == _endDateButton);
                LOG.debug(calendarButton.getSelectedDate());
                CalendarDate start = _startDateButton.getSelectedDate();
                getDietTreatment().setStart(start);

                CalendarDate end = _endDateButton.getSelectedDate();
                if (end.compareTo(start) < 0)
                {
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
                        getDietTreatment().setShortDescription(
                                textInput.getText());
                    }
                });

        _systemUserTable = (TableView) namespace.get("systemUsers");

        _addSystemUser = (Button) namespace.get("addSystemUser");

        _addSystemUser.getButtonPressListeners().add(new ButtonPressListener()
        {

            public void buttonPressed(Button arg0)
            {
                addNewSystemUser();
            }
        });

        _removeSystemUser = (Button) namespace.get("removeSystemUser");
        _removeSystemUser.getButtonPressListeners().add(
                new ButtonPressListener()
                {
                    public void buttonPressed(Button arg0)
                    {
                        removeSystemUser((DietTreatmentSystemUserBO) _systemUserTable
                                .getSelectedRow());
                    }
                });

        _editor = (EasyTableViewRowEditor) _systemUserTable.getRowEditor();

        // add systemusers to the ListButton
        _editor.getRowEditorListeners().add(
                new EasyTableViewRowEditor.RowEditorListener.Adapter()
                {
                    @Override
                    public Vote previewEditRow(RowEditor rowEditor,
                            TableView tableView, int rowIndex, int columnIndex)
                    {
                        // update list of units
                        _systemUserListButton = (ListButton) _editor
                                .getCellEditors().get("systemUser.name");
                        DietTreatmentSystemUserBO row = (DietTreatmentSystemUserBO) _systemUserTable
                                .getTableData().get(rowIndex);

                        _systemUserListButton
                                .setListData((org.apache.pivot.collections.List<?>) SystemUserController
                                        .getInstance().getAllUsers());

                        for (int i = 0; i < _systemUserListButton.getListData()
                                .getLength(); i++)
                        {
                            SystemUserBO systemUser = (SystemUserBO) _systemUserListButton
                                    .getListData().get(i);
                            if (systemUser.getName().equalsIgnoreCase(
                                    row.getSystemUser().getName()))
                            {
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
                new EasyTableViewRowEditor.RowEditorListener.Adapter()
                {
                    @Override
                    public Vote previewEditRow(RowEditor rowEditor,
                            TableView tableView, int rowIndex, int columnIndex)
                    {
                        // update list of units
                        _systemUserFunctionListButton = (ListButton) _editor
                                .getCellEditors().get("function.name");
                        DietTreatmentSystemUserBO row = (DietTreatmentSystemUserBO) _systemUserTable
                                .getTableData().get(rowIndex);

                        _systemUserFunctionListButton
                                .setListData(SystemUserFunctionBO
                                        .getAllValues());

                        for (int i = 0; i < _systemUserFunctionListButton
                                .getListData().getLength(); i++)
                        {
                            SystemUserFunctionBO systemUserFunction = (SystemUserFunctionBO) _systemUserFunctionListButton
                                    .getListData().get(i);
                            if (systemUserFunction == row.getFunction())
                            {
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

        // assignment
        _patientState = (TableView) namespace.get("patientState");

        _patientState.setTableData(PatientDetailViewController.getInstance()
                .getAllDiagnosis());

        _patientState.getTableViewSelectionListeners().add(
                new TableViewSelectionListener.Adapter()
                {

                    @Override
                    public void selectedRowChanged(TableView table,
                            Object previousRow)
                    {
                        changeAssignment((PatientStateBO) table
                                .getSelectedRow());
                    }
                });

    }

    /**
     * Change the chosen assignment diagnosis
     * 
     * @param patientState
     *            the state to use as diagnosis
     */
    protected void changeAssignment(PatientStateBO patientState)
    {
        DietTreatmentEditingController.getInstance().changeAssignment(
                patientState);

    }

    /**
     * Remove the selected system user from the list
     * 
     * @param systemUser
     *            the user to remove
     */
    protected void removeSystemUser(DietTreatmentSystemUserBO systemUser)
    {
        DietTreatmentEditingController.getInstance().removeSystemUser(
                systemUser);

    }

    /**
     * Add a new system user to the list
     */
    protected void addNewSystemUser()
    {
        DietTreatmentEditingController.getInstance().addNewSystemUser();
    }

    @Override
    public abstract void onLoad();

    @Override
    public boolean onClose()
    {
        // want to cancel edit?
        if (!_saved)
        {
            DietTreatmentEditingController.getInstance().revertChanges();
        }

        // clear all errors
        DietTreatmentEditingController.getInstance().getErrors().clear();

        return super.onClose();
    }

    /**
     * Update the label which shows the duration of the {@link DietTreatmentBO}
     */
    protected void updateDurationLabel()
    {
        int days = getDietTreatment().getDuration();
        String dayLabel = days > 0 ? "Tage" : "Tag";
        _durationLabel.setText((days + 1) + " " + dayLabel);
    }

    /**
     * Gets the current {@link DietTreatmentBO}
     * 
     * @return Instance of the current {@link DietTreatmentBO}
     */
    protected DietTreatmentBO getDietTreatment()
    {
        return DietTreatmentEditingController.getInstance().getDietTreatment();
    }

    /**
     * Save the current {@link DietTreatmentBO}
     */
    private void save()
    {
        _saved = DietTreatmentEditingController.getInstance()
                .saveDietTreatment();
        if (_saved)
        {
            DietTreatmentDetailViewController.getInstance().setDietTreatment(
                    getDietTreatment());
            ViewController.getInstance().loadContent("DietTreatmentDetailView",
                    DietTreatmentManagementView.this);
        }
        else if (DietPlanEditingController.getInstance().getErrors()
                .getLength() == 0)
        {
            EasyAlerts
                    .error("Es ist ein Fehler beim Speichern der Behandlung aufgetreten, bitte versuchen Sie es erneut!",
                            EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
                            null);
        }
        else
        {
            EasyAlerts
                    .error("Es sind noch Fehler im Diätplan vorhanden! Bitte korrigieren Sie diese!",
                            EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
                            null);
        }
    }

}
