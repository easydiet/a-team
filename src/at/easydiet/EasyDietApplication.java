package at.easydiet;

import java.awt.Font;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.ApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Theme;
import org.apache.pivot.wtk.Window;

import at.easydiet.view.EasyDietMainWindow;

public class EasyDietApplication implements Application
{
    public static final org.apache.log4j.Logger LOG               = org.apache.log4j.Logger
                                                                          .getLogger(EasyDietApplication.class);

    public static final String                  APPLICATION_TITLE = "EasyDiet v0.1(dev) ~ ";
    public static final String                  DATE_FORMAT       = "dd.MM.yyyy";

    private Window                              window            = null;

    public void resume() throws Exception
    {}

    public boolean shutdown(boolean arg0) throws Exception
    {
        if (window != null)
        {
            window.close();
        }

        return false;
    }

    public void startup(Display display, Map<String, String> arg1)
            throws Exception
    {
        Theme.getTheme().setFont(new Font("Arial", Font.PLAIN, 12));

        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        window = (Window) bxmlSerializer.readObject(EasyDietMainWindow.class,
                "EasyDietMainWindow.xml");
        window.open(display);
    }

    public void suspend() throws Exception
    {}

}