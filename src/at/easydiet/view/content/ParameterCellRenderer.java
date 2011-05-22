package at.easydiet.view.content;

import java.awt.Color;
import java.awt.Font;
import org.apache.pivot.json.JSON;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.Insets;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.TableView.CellRenderer;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.businessobjects.DietParameterTemplateBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.validation.ParameterTemplateValidator;
import at.easydiet.view.EasyDietMainWindow;

/**
 * Renderer for our ParameterTableView
 */
public class ParameterCellRenderer extends BoxPane implements CellRenderer
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(MealLineCellRenderer.class);

    /**
     * The image to show if an error occurs
     */
    private static final Image                   ERROR_IMAGE;

    static
    {
        Image img = null;
        try
        {
            img = Image.load(EasyDietMainWindow.class
                    .getResource("error_small.png"));
        }
        catch (TaskExecutionException e)
        {
            LOG.debug(e);
        }
        ERROR_IMAGE = img;

    }

    /**
     * Stores the {@link ImageView}
     */
    private ImageView                            _errorImage;
    /**
     * Stores the {@link Label}
     */
    private Label                                _textLabel;

    /**
     * Stores the parameter provider
     */
    private IDietParameterizable                 _parameterizable;

    /**
     * Initializes a new instance of the {@link ParameterCellRenderer} class.
     */
    public ParameterCellRenderer()
    {
        _textLabel = new Label();
        getStyles().put("fill", true);
        getStyles().put("spacing", 3);
        getStyles().put("verticalAlignment", VerticalAlignment.CENTER);
        _textLabel.getStyles().put("verticalAlignment",
                VerticalAlignment.CENTER);
        getStyles().put("padding", new Insets(2));
        rebuildUI();
        setPreferredHeight(24);
        _textLabel.setPreferredHeight(getPreferredHeight());
    }

    /**
     * Reload the User Interface
     */
    private void rebuildUI()
    {
        this.rebuildUI(false);
    }

    /**
     * Reload the User interface
     * 
     * @param isError
     *            whether there is an error or not
     */
    private void rebuildUI(boolean isError)
    {
        removeAll();
        if (isErrorImageVisible() && isError)
        {
            add(_errorImage);
            add(new Label("Konflikte mit anderen Parametern!"));
        }
        else
        {
            add(_textLabel);
        }
    }

    /**
     * @see org.apache.pivot.wtk.Component#setSize(int, int)
     */
    @Override
    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        validate();
    }

    /**
     * @see org.apache.pivot.wtk.TableView.CellRenderer#render(java.lang.Object,
     *      int, int, org.apache.pivot.wtk.TableView, java.lang.String, boolean,
     *      boolean, boolean)
     */
    public void render(Object row, int rowIndex, int columnIndex,
            TableView tableView, String columnName, boolean selected,
            boolean highlighted, boolean disabled)
    {

        String text = null;

        if (row != null && columnName != null)
        {
            text = toString(row, columnName);
        }

        _textLabel.setText(text);

        Component.StyleDictionary tableViewStyles = tableView.getStyles();

        if (!ParameterTemplateValidator.getInstance().isValid(_parameterizable,
                (DietParameterTemplateBO) row))
        {
            rebuildUI(true);
            if (selected)
            {
                getStyles().put("backgroundColor", new Color(0xFF6666));
            }
            else
            {
                getStyles().put("backgroundColor", new Color(0xFFAAAA));
            }
        }
        else if (selected)
        {
            rebuildUI(false);
            getStyles().put("backgroundColor",
                    tableViewStyles.get("selectionBackgroundColor"));
        }
        else
        {
            rebuildUI(false);
            getStyles().put("backgroundColor",
                    tableViewStyles.get("getInactiveSelectionBackgroundColor"));
        }

        renderStyles(tableView, selected, disabled);
    }

    /**
     * Set the styles for a row
     * 
     * @param tableView
     *            The {@link TableView}
     * @param rowSelected
     *            Is the row selected
     * @param rowDisabled
     *            Is the row disabled
     */
    protected void renderStyles(TableView tableView, boolean rowSelected,
            boolean rowDisabled)
    {
        Component.StyleDictionary tableViewStyles = tableView.getStyles();
        Component.StyleDictionary labelStyles = _textLabel.getStyles();

        Font font = (Font) tableViewStyles.get("font");
        labelStyles.put("font", font);

        Color color;
        if (tableView.isEnabled() && !rowDisabled)
        {
            if (rowSelected)
            {
                if (tableView.isFocused())
                {
                    color = (Color) tableViewStyles.get("selectionColor");
                }
                else
                {
                    color = (Color) tableViewStyles
                            .get("inactiveSelectionColor");
                }
            }
            else
            {
                color = (Color) tableViewStyles.get("color");
            }
        }
        else
        {
            color = (Color) tableViewStyles.get("disabledColor");
        }

        labelStyles.put("color", color);
    }

    /**
     * @see org.apache.pivot.wtk.TableView.CellRenderer#toString(java.lang.Object,
     *      java.lang.String)
     */
    public String toString(Object row, String columnName)
    {
        if (!isErrorImageVisible())
        {
            Object cellData = JSON.get(row, columnName);
            String cellString = (cellData == null) ? "{null}" : cellData
                    .toString();

            return cellString;
        }
        return "";
    }

    /**
     * Sets the parameter provider
     * 
     * @param parameterizable
     *            Instance of an {@link IDietParameterizable} object
     */
    public void setParameterizable(IDietParameterizable parameterizable)
    {
        _parameterizable = parameterizable;
    }

    /**
     * Checks if the error image is visible or not
     * 
     * @return True if it is visible
     */
    public boolean isErrorImageVisible()
    {
        return _errorImage != null;
    }

    /**
     * Sets the visibility of the error image
     * 
     * @param visible
     *            Visibility
     */
    public void setErrorImageVisible(boolean visible)
    {
        if (visible)
        {
            _errorImage = new ImageView(ERROR_IMAGE);
            _errorImage.setPreferredHeight(getPreferredHeight());
        }
        else
        {
            _errorImage = null;
        }
        rebuildUI();
    }
}
