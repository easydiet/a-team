package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.media.Image;

/**
 * This is a simple button
 */
public class SimpleButton extends PushButton
{
    /**
     * The {@link EasyDietContentView} this button points to
     */
    private EasyDietContentView easyDietContentView;
    /**
     * The name of the view
     */
    private String              view;

    /**
     * Initializes a new instance of the {@link SimpleButton} class.
     */
    public SimpleButton()
    {
        this.setButtonData(new ButtonData());
    }

    /**
     * Gets the text of this instance
     * 
     * @return The text of this instance
     */
    public String getText()
    {
        return ((ButtonData) this.getButtonData()).getText();
    }

    /**
     * Sets the text of this instance
     * 
     * @param text
     *            The new text to set
     */
    public void setText(String text)
    {
        ((ButtonData) this.getButtonData()).setText(text);
    }

    /**
     * Gets the icon of this instance
     * 
     * @return The icon of this instance
     */
    public Image getIcon()
    {
        return ((ButtonData) this.getButtonData()).getIcon();
    }

    /**
     * Sets the icon of this instance
     * 
     * @param icon
     *            The new icon to set
     */
    public void setIcon(Image icon)
    {
        ((ButtonData) this.getButtonData()).setIcon(icon);
    }

    /**
     * Sets the icon of this instance
     * 
     * @param icon
     *            The path to the new icon
     */
    public void setIcon(String icon)
    {
        ((ButtonData) this.getButtonData()).setIcon(icon);
    }

    /**
     * Sets the icon of this instance
     * 
     * @param icon
     *            The {@link URL} to the new icon
     */
    public void setIcon(URL icon)
    {
        ((ButtonData) this.getButtonData()).setIcon(icon);
    }

    /**
     * Gets the {@link EasyDietContentView} of this instance
     * 
     * @return The {@link EasyDietContentView} of this instance
     */
    public EasyDietContentView getEasyDietContentView()
    {
        return easyDietContentView;
    }

    /**
     * Set the view of this instance
     * 
     * @param value
     *            The new view to set
     */
    public void setView(String value)
    {
        this.view = value;
    }

    /**
     * Gets the view of this instance
     * 
     * @return The view of this instance
     */
    public String getView()
    {
        return this.view;
    }
}