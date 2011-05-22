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
import org.apache.pivot.wtk.TableView.CellRenderer;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.view.EasyDietMainWindow;

public class MealLineCellRenderer extends BoxPane implements CellRenderer
{

    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(MealLineCellRenderer.class);

    private static final Image                  ARROW_IMAGE;

    static
    {
        Image img = null;
        try
        {
            img = Image.load(EasyDietMainWindow.class.getResource("alternative_arrow.png"));
        }
        catch (TaskExecutionException e)
        {
            LOG.debug(e);
        }
        ARROW_IMAGE = img;

    }

    private ImageView                           _arrowImage;
    private Label                               _textLabel;

    public boolean isArrowVisible()
    {
        return _arrowImage != null;
    }

    public void setArrowVisible(boolean visible)
    {
        if (visible)
        {
            _arrowImage = new ImageView(ARROW_IMAGE);
            _arrowImage.setPreferredHeight(getPreferredHeight());
        }
        else
        {
            _arrowImage = null;
        }
        rebuildUI();
    }

    private void rebuildUI()
    {
        removeAll();
        if (isArrowVisible())
        {
            add(_arrowImage);
        }
        add(_textLabel);
    }

    public MealLineCellRenderer()
    {
        _textLabel = new Label();
        getStyles().put("fill", true);
        getStyles().put("spacing", 3);
        getStyles().put("verticalAlignment", VerticalAlignment.CENTER);
        _textLabel.getStyles().put("verticalAlignment", VerticalAlignment.CENTER);
        getStyles().put("padding", new Insets(2));
        rebuildUI();
        setPreferredHeight(24);
        _textLabel.setPreferredHeight(getPreferredHeight());
    }

    @Override
    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        validate();
    }

    public void render(Object row, int rowIndex, int columnIndex,
            TableView tableView, String columnName, boolean selected,
            boolean highlighted, boolean disabled)
    {

        renderStyles(tableView, selected, disabled);
        
        MealLineBO line = (MealLineBO)row;
        if(line != null && isArrowVisible())
        {
            _arrowImage.setVisible(line.isAlternative());
        }
        
        String text = null;
        if (row != null && columnName != null)
        {
            text = toString(row, columnName);
        }

        _textLabel.setText(text);
    }

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
    
    public String toString(Object row, String columnName) {
        Object cellData = JSON.get(row, columnName);
        String cellString = (cellData == null) ? "{null}" : cellData.toString();
        MealLineBO line = (MealLineBO)row;
        
        if(line.isAlternative() && !isArrowVisible())
        {
            cellString = "    " + cellString;
        }
        
        return cellString;
    }


}
