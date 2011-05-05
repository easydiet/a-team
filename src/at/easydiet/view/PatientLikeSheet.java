package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;

public class PatientLikeSheet extends Sheet implements Bindable
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientLikeSheet.class);

    private ListButton                          _gradeList;
    private TableView                           _recipeList;
    private TextInput                           _blsPattern;
    private Button                              _cancelButton;
    private Button                              _saveButton;

    /**
     * Gets the gradeList.
     * @return the gradeList
     */
    public ListButton getGradeList()
    {
        return _gradeList;
    }

    /**
     * Gets the recipeList.
     * @return the recipeList
     */
    public TableView getRecipeList()
    {
        return _recipeList;
    }

    /**
     * Gets the blsPattern.
     * @return the blsPattern
     */
    public TextInput getBlsPattern()
    {
        return _blsPattern;
    }

    /**
     * Gets the cancelButton.
     * @return the cancelButton
     */
    public Button getCancelButton()
    {
        return _cancelButton;
    }

    /**
     * Gets the saveButton.
     * @return the saveButton
     */
    public Button getSaveButton()
    {
        return _saveButton;
    }

    @Override
    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
        _gradeList = (ListButton) namespace.get("gradeList");
        _recipeList = (TableView) namespace.get("recipeList");
        _blsPattern = (TextInput) namespace.get("blsPattern");
        _cancelButton = (Button) namespace.get("cancelButton");
        _saveButton = (Button) namespace.get("saveButton");
    }
}
