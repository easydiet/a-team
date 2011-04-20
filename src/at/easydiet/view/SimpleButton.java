package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.media.Image;

public class SimpleButton extends PushButton
{
    private EasyDietContentView easyDietContentView;
    private String              view;

    public SimpleButton()
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
    
    public EasyDietContentView getEasyDietContentView()
    {
        return easyDietContentView;
    }

    public void setView(String value)
    {
        this.view = value;
    }

    public String getView()
    {
        return this.view;
    }
}