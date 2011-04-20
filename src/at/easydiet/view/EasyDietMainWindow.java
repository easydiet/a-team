package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.CardPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.FlowPane;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.Window;

public class EasyDietMainWindow extends Window implements Bindable
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(EasyDietMainWindow.class);

    private FlowPane                            _navigation;
    private CardPane                            _content;
    private BoxPane                             _toolbar;

    /**
     * Gets the navigation.
     * @return the navigation
     */
    public FlowPane getNavigationPane()
    {
        return _navigation;
    }

    /**
     * Gets the content.
     * @return the content
     */
    public CardPane getContentPane()
    {
        return _content;
    }

    /**
     * Gets the toolbar.
     * @return the toolbar
     */
    public BoxPane getToolbarPane()
    {
        return _toolbar;
    }

    /**
     * Actions
     */
    static
    {
        // Define the actions
        Action.getNamedActions().put("quitApplication", new Action()
        {
            @Override
            public void perform(Component source)
            {
                System.exit(0);
            }
        });

        Action.getNamedActions().put("showHelp", new Action()
        {
            @Override
            public void perform(Component source)
            {
                Prompt.prompt("Jetzt sollte sich die Hilfe öffnen.", source
                        .getWindow().getRootOwner());
            }
        });

        Action.getNamedActions().put("showInfo", new Action()
        {
            @Override
            public void perform(Component source)
            {
                Prompt.prompt("EasyDiet v.0.0.0.0.0.0.0.0.0.0.0.1", source
                        .getWindow().getRootOwner());
            }
        });
        Action.getNamedActions().put("todo", new Action()
        {
            @Override
            public void perform(Component source)
            {
                Prompt.prompt("Dieses Feature ist noch nicht verfügbar!", source
                        .getWindow().getRootOwner());
            }
        });

        // other xml views
        Action.getNamedActions().put("showDashboard", new Action()
        {
            @Override
            public void perform(Component source)
            {
                ViewController.getInstance().loadContent("Dashboard.xml",
                        source);
            }
        });

        Action.getNamedActions().put("showPatientDetailView", new Action()
        {
            @Override
            public void perform(Component source)
            {
                ViewController.getInstance().loadContent(
                        "PatientDetailView.xml", source);
            }
        });
    }

    public void initialize(Map<String, Object> ns, URL loc, Resources es)
    {
        _content = (CardPane) ns.get("content");
        _navigation = (FlowPane) ns.get("navigation");
        _toolbar = (BoxPane) ns.get("toolbar");

        ViewController.getInstance().loadContent("DashboardView", this);
    }
}
