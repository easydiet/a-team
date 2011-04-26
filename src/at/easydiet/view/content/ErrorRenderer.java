package at.easydiet.view.content;

import java.awt.Color;

import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.content.ListItem;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.view.EasyDietMainWindow;
import at.easydiet.view.ImageLabel;

public class ErrorRenderer extends ImageLabel implements ListView.ItemRenderer
{
    private static final Image ERROR_IMAGE;

    static
    {
        Image img = null;
        try
        {
            img = Image.load(EasyDietMainWindow.class.getResource("fatal.png"));
        }
        catch (Exception e)
        {}
        ERROR_IMAGE = img;
    }

    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        validate();
    }

    public void render(Object item, int index, ListView listView,
            boolean selected, boolean checked, boolean highlighted,
            boolean disabled)
    {
        renderStyles(listView, selected, highlighted, disabled);
        Image icon = null;
        String text = null;

        if (item instanceof ListItem)
        {
            ListItem listItem = (ListItem) item;
            icon = listItem.getIcon();
            text = listItem.getText();
        }
        else if (item instanceof Image)
        {
            icon = (Image) item;
        }
        else
        {
            if (item != null)
            {
                text = item.toString();
            }
            icon = ERROR_IMAGE;
        }

        setImage(icon);
        setText(text);
    }

    protected void renderStyles(ListView listView, boolean selected,
            boolean highlighted, boolean disabled)
    {
        Color transparent = new Color(0xffecec);
        listView.getStyles().put("selectionColor", transparent);
        listView.getStyles().put("inactiveSelectionColor", transparent);
        listView.getStyles().put("backgroundColor", transparent);
        getTextLabel().getStyles().put("color", new Color(0xbf3b2c));
        getStyles().put("backgroundColor", transparent);
    }

    public String toString(Object item)
    {
        String string = null;

        if (item instanceof ListItem)
        {
            ListItem listItem = (ListItem) item;
            string = listItem.getText();
        }
        else
        {
            if (item != null)
            {
                string = item.toString();
            }
        }

        return string;
    }
}
