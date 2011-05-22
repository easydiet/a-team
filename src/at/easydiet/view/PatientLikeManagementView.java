package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.businesslogic.PatientLikeManagementController;
import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.PatientLikeBO;

/**
 * This is the background class to the PatientLikeManagementView.bxml
 */
public class PatientLikeManagementView extends EasyDietContentView implements
        Bindable
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(PatientLikeManagementView.class);

    /**
     * Stores the {@link TableView} that shows the {@link PatientLikeBO}s
     */
    private TableView                            _likeTable;
    /**
     * Stores whether the likes are saved or not
     */
    private boolean                              _saved;

    /**
     * @see at.easydiet.view.EasyDietContentView#onLoad()
     */
    @Override
    public void onLoad()
    {
        _saved = false;
        PatientDetailViewController.getInstance().refresh();
        setPatient(PatientDetailViewController.getInstance().getPatient());
    }

    /**
     * @see at.easydiet.view.EasyDietContentView#onClose()
     */
    @Override
    public boolean onClose()
    {
        // want to cancel edit?
        if (!_saved)
        {
            PatientLikeManagementController.getInstance().revertChanges();
        }
        return super.onClose();
    }

    /**
     * Sets the patient
     * 
     * @param patient
     *            The new {@link PatientBO}
     */
    public void setPatient(PatientBO patient)
    {
        PatientLikeManagementController.getInstance().setPatient(patient);
        _likeTable.setTableData(PatientLikeManagementController.getInstance()
                .getLikes());
    }

    /**
     * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map,
     *      java.net.URL, org.apache.pivot.util.Resources)
     */
    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
        _likeTable = (TableView) namespace.get("likeTable");

        Button saveButton = (Button) namespace.get("save");
        saveButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button button)
            {
                // save data
                if (PatientLikeManagementController.getInstance().saveData())
                {
                    ViewController.getInstance()
                            .loadContent("PatientDetailView",
                                    PatientLikeManagementView.this);
                    _saved = true;
                }
                else
                {
                    EasyAlerts
                            .error("Fehler beim Speichern der Daten, versuchen Sie es bitte erneut!",
                                    EasyAlerts.OK_ONLY, EasyAlerts.OK,
                                    getWindow(), null);
                }

            }
        });

        Button addLike = (Button) namespace.get("addLike");
        addLike.getButtonPressListeners().add(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button button)
            {
                showCreateLikeSheet();
            }
        });

        Button editLike = (Button) namespace.get("editLike");
        editLike.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button button)
            {
                showEditLikeSheet();
            }
        });

        Button removeLike = (Button) namespace.get("removeLike");
        removeLike.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button button)
            {
                removeLike();
            }
        });
    }

    /**
     * Remove the selected like
     */
    protected void removeLike()
    {
        if (_likeTable.getSelectedRow() == null) return;
        PatientLikeManagementController.getInstance().removeLike(
                (PatientLikeBO) _likeTable.getSelectedRow());
    }

    /**
     * Show the view to edit a {@link PatientLikeBO}
     */
    protected void showEditLikeSheet()
    {
        if (_likeTable.getSelectedRow() == null) return;
        final PatientLikeSheet editSheet = loadEditSheet();

        // load current data
        editSheet.load(_likeTable.getSelectedRow());

        // add listener to detect save
        editSheet.getSaveButton().getButtonPressListeners()
                .add(new ButtonPressListener()
                {
                    @Override
                    public void buttonPressed(Button button)
                    {
                        if (validateSheet(editSheet))
                        {
                            editSheet.store(_likeTable.getSelectedRow());
                            editSheet.close(true);
                        }
                    }
                });

        editSheet.open(getWindow());
    }

    /**
     * Show the view to add a {@link PatientLikeBO}
     */
    protected void showCreateLikeSheet()
    {
        final PatientLikeSheet editSheet = loadEditSheet();

        // create new like
        final PatientLikeBO newLike = PatientLikeManagementController
                .getInstance().getNewLike();
        editSheet.load(newLike);

        // add listener to detect save
        editSheet.getSaveButton().getButtonPressListeners()
                .add(new ButtonPressListener()
                {
                    @Override
                    public void buttonPressed(Button button)
                    {
                        if (validateSheet(editSheet))
                        {
                            editSheet.store(newLike);
                            PatientLikeManagementController.getInstance()
                                    .addLike(newLike);
                            editSheet.close(true);
                        }
                    }
                });

        editSheet.open(getWindow());
    }

    /**
     * Validate the edit- or create {@link PatientLikeBO} view
     * 
     * @param editSheet
     *            The {@link PatientLikeSheet} to validate
     * @return True if everything is valid
     */
    protected boolean validateSheet(PatientLikeSheet editSheet)
    {
        if (editSheet.getRecipeList().getTableData().getLength() <= 0)
        {
            EasyAlerts
                    .error("Das angegebene BLS Pattern muss mindestens 1 Rezept betreffen",
                            EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
                            null);
            editSheet.getBlsPattern().requestFocus();
            return false;
        }

        if (editSheet.getGradeList().getSelectedItem() == null)
        {
            EasyAlerts.error("Sie müssen ein Bindungsgrad wählen",
                    EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(), null);
            editSheet.getGradeList().requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Load the EditSheet from the .bxml file
     * 
     * @return A new {@link PatientLikeSheet}
     */
    private PatientLikeSheet loadEditSheet()
    {
        BXMLSerializer serializer = new BXMLSerializer();
        try
        {
            final PatientLikeSheet editSheet = (PatientLikeSheet) serializer
                    .readObject(PatientLikeManagementView.class,
                            "PatientLikeSheet"
                                    + ViewController.PIVOT_FILE_EXTENSION);

            // add grade list data
            editSheet.getGradeList().setListData(
                    PatientLikeManagementController.getInstance().getGrades());

            // add listener to search for recipes
            editSheet.getBlsPattern().getTextInputContentListeners()
                    .add(new TextInputContentListener.Adapter()
                    {
                        public void textChanged(TextInput txt)
                        {
                            editSheet.getRecipeList()
                                    .setTableData(
                                            PatientLikeManagementController
                                                    .getInstance()
                                                    .getRecipesForPattern(
                                                            txt.getText()));
                        };
                    });

            return editSheet;
        }
        catch (Exception e)
        {
            LOG.error(e);
            return null;
        }
    }
}
