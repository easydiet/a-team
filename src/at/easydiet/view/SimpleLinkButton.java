package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.media.Image;

public class SimpleLinkButton extends LinkButton
{
    public SimpleLinkButton()
    {
        this.setButtonData(new ButtonData());
    }

    public String getText()
    {
        return ((ButtonData) this.getButtonData()).getText();
    }

    public void setText(String text)
    {
        ((ButtonData) this.getButtonData()).setText(text);
    }

    public Image getIcon()
    {
        return ((ButtonData) this.getButtonData()).getIcon();
    }

    public void setIcon(Image icon)
    {
        ((ButtonData) this.getButtonData()).setIcon(icon);
    }

    public void setIcon(String icon)
    {
        ((ButtonData) this.getButtonData()).setIcon(icon);
    }
    
    public void setIcon(URL icon)
    {
        ((ButtonData) this.getButtonData()).setIcon(icon);
    }
}