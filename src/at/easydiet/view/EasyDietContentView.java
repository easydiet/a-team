package at.easydiet.view;

import java.awt.Color;
import java.net.URL;
import java.util.Iterator;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.ImmutableIterator;
import org.apache.pivot.util.ListenerList;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.HorizontalAlignment;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.Insets;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.StackPane;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.content.ButtonDataRenderer;
import org.apache.pivot.wtk.media.Image;

public class EasyDietContentView extends BoxPane
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(EasyDietContentView.class);

    /**
     * defines the button in the navigation bar
     * @author Mathias
     * 
     */
    public static class NavigationButton extends StackPane
    {
        private EasyDietContentView easyDietContentView;
        private String              view;

        private SimpleButton        _button;

        public NavigationButton()
        {
            // Background image
            Image img = null;
            try
            {
                img = Image.load(EasyDietMainWindow.class
                        .getResource("navigation_arrow.gif"));
                ImageView buttonBg = new ImageView(img);
                buttonBg.setPreferredHeight(29);
                buttonBg.getStyles().put("horizontalAlignment",
                        HorizontalAlignment.RIGHT);
                buttonBg.getStyles().put("preserveAspectRatio", true);
                add(buttonBg);
            }
            catch (TaskExecutionException e)
            {
                LOG.debug(e);
            }

            // button
            _button = new SimpleButton();
            if (img != null)
                _button.getStyles().put("padding",
                        new Insets(0, 10, 0, img.getWidth()));
            _button.getStyles().put("color", new Color(0x173760));
            _button.getStyles().put("toolbar", true);
            _button.getButtonPressListeners().add(new ButtonPressListener()
            {
                public void buttonPressed(Button button)
                {
                    if (NavigationButton.this.view != null)
                    {
                        ViewController.getInstance().loadContent(
                                NavigationButton.this.view,
                                NavigationButton.this);
                    }
                }
            });
            add(_button);
        }

        public void refresh()
        {

        }

        public String getText()
        {
            return ((ButtonData) _button.getButtonData()).getText();
        }

        public void setText(String text)
        {
            ((ButtonData) _button.getButtonData()).setText(text);
        }

        public Image getIcon()
        {
            return ((ButtonData) _button.getButtonData()).getIcon();
        }

        public void setIcon(Image icon)
        {
            ((ButtonData) _button.getButtonData()).setIcon(icon);
        }

        public void setIcon(String icon)
        {
            ((ButtonData) _button.getButtonData()).setIcon(icon);
        }

        public void setIcon(URL icon)
        {
            ((ButtonData) _button.getButtonData()).setIcon(icon);
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

    /**
     * deifnes the button in the toolbar
     * @author Mathias
     * 
     */
    public static class ToolbarButton extends SimpleButton
    {
        private EasyDietContentView easyDietContentView;

        public ToolbarButton()
        {
            // buttonrenderer to render the button vertically
            ButtonDataRenderer bdr = (ButtonDataRenderer) getDataRenderer();
            bdr.setOrientation(Orientation.VERTICAL);

            setDataRenderer(bdr);

            // Height
            setHeight(60);
            setMinimumHeight(60);
            setMaximumHeight(60);

            // Width
            setMinimumWidth(60);
            setMaximumWidth(180);

            // styles
            getStyles().put("color", new Color(0x173760));
            getStyles().put("toolbar", true);
        }

        public EasyDietContentView getEasyDietContentView()
        {
            return easyDietContentView;
        }
    }

    private ArrayList<NavigationButton>     navigationButtons;
    private ArrayList<ToolbarButton>        toolbarButtons;
    private NavigationButtonSequence        navigationButtonSequence     = new NavigationButtonSequence();
    private ToolbarButtonSequence           toolbarButtonSequence        = new ToolbarButtonSequence();

    private EasyDietContentViewListenerList easyDietContentViewListeners = new EasyDietContentViewListenerList();

    public final class NavigationButtonSequence implements
            Sequence<NavigationButton>, Iterable<NavigationButton>
    {

        private NavigationButtonSequence()
        {}

        public int add(NavigationButton navigationButton)
        {
            int index = getLength();
            insert(navigationButton, index);

            return index;
        }

        public void insert(NavigationButton navigationButton, int index)
        {
            if (navigationButton == null)
            {
                throw new IllegalArgumentException("navigationButton is null.");
            }

            if (navigationButton.easyDietContentView != null)
            {
                throw new IllegalArgumentException(
                        "navigationButton is already in use by another table pane.");
            }

            navigationButtons.insert(navigationButton, index);
            navigationButton.easyDietContentView = EasyDietContentView.this;

            // Notify listeners
            easyDietContentViewListeners.navigationButtonInserted(
                    EasyDietContentView.this, index);
        }

        public NavigationButton update(int index,
                NavigationButton navigationButton)
        {
            throw new UnsupportedOperationException();
        }

        public int remove(NavigationButton navigationButton)
        {
            int index = indexOf(navigationButton);
            if (index != -1)
            {
                remove(index, 1);
            }

            return index;
        }

        public Sequence<NavigationButton> remove(int index, int count)
        {
            Sequence<NavigationButton> removed = navigationButtons.remove(
                    index, count);

            if (count > 0)
            {
                for (int i = 0, n = removed.getLength(); i < n; i++)
                {
                    NavigationButton navigationButton = removed.get(i);
                    navigationButton.easyDietContentView = null;
                }

                easyDietContentViewListeners.navigationButtonsRemoved(
                        EasyDietContentView.this, index, removed);
            }

            return removed;
        }

        public NavigationButton get(int index)
        {
            return navigationButtons.get(index);
        }

        public int indexOf(NavigationButton navigationButton)
        {
            return navigationButtons.indexOf(navigationButton);
        }

        public int getLength()
        {
            return navigationButtons.getLength();
        }

        public Iterator<NavigationButton> iterator()
        {
            return new ImmutableIterator<NavigationButton>(
                    navigationButtons.iterator());
        }
    }

    public final class ToolbarButtonSequence implements
            Sequence<ToolbarButton>, Iterable<ToolbarButton>
    {

        private ToolbarButtonSequence()
        {}

        public int add(ToolbarButton toolbarButton)
        {
            int index = getLength();
            insert(toolbarButton, index);

            return index;
        }

        public void insert(ToolbarButton toolbarButton, int index)
        {
            if (toolbarButton == null)
            {
                throw new IllegalArgumentException("toolbarButton is null.");
            }

            if (toolbarButton.easyDietContentView != null)
            {
                throw new IllegalArgumentException(
                        "toolbarButton is already in use by another table pane.");
            }

            toolbarButtons.insert(toolbarButton, index);
            toolbarButton.easyDietContentView = EasyDietContentView.this;

            // Notify listeners
            easyDietContentViewListeners.toolbarButtonInserted(
                    EasyDietContentView.this, index);
        }

        public ToolbarButton update(int index, ToolbarButton toolbarButton)
        {
            throw new UnsupportedOperationException();
        }

        public int remove(ToolbarButton toolbarButton)
        {
            int index = indexOf(toolbarButton);
            if (index != -1)
            {
                remove(index, 1);
            }

            return index;
        }

        public Sequence<ToolbarButton> remove(int index, int count)
        {
            Sequence<ToolbarButton> removed = toolbarButtons.remove(index,
                    count);

            if (count > 0)
            {
                for (int i = 0, n = removed.getLength(); i < n; i++)
                {
                    ToolbarButton toolbarButton = removed.get(i);
                    toolbarButton.easyDietContentView = null;
                }

                easyDietContentViewListeners.toolbarButtonsRemoved(
                        EasyDietContentView.this, index, removed);
            }

            return removed;
        }

        public ToolbarButton get(int index)
        {
            return toolbarButtons.get(index);
        }

        public int indexOf(ToolbarButton toolbarButton)
        {
            return toolbarButtons.indexOf(toolbarButton);
        }

        public int getLength()
        {
            return toolbarButtons.getLength();
        }

        public Iterator<ToolbarButton> iterator()
        {
            return new ImmutableIterator<ToolbarButton>(
                    toolbarButtons.iterator());
        }
    }

    private final class EasyDietContentViewListenerList extends
            ListenerList<EasyDietContentViewListener>
    {
        public void navigationButtonInserted(EasyDietContentView contentView,
                int index)
        {
            for (EasyDietContentViewListener listener : this)
            {
                listener.navigationButtonInserted(contentView, index);
            }
        }

        public void navigationButtonsRemoved(EasyDietContentView contentView,
                int index, Sequence<NavigationButton> navigationButtons)
        {
            for (EasyDietContentViewListener listener : this)
            {
                listener.navigationButtonsRemoved(contentView, index,
                        navigationButtons);
            }
        }

        public void toolbarButtonInserted(EasyDietContentView contentView,
                int index)
        {
            for (EasyDietContentViewListener listener : this)
            {
                listener.toolbarButtonInserted(contentView, index);
            }
        }

        public void toolbarButtonsRemoved(EasyDietContentView contentView,
                int index, Sequence<ToolbarButton> toolbarButtons)
        {
            for (EasyDietContentViewListener listener : this)
            {
                listener.toolbarButtonsRemoved(contentView, index,
                        toolbarButtons);
            }
        }
    }

    public EasyDietContentView()
    {
        this(new ArrayList<NavigationButton>(), new ArrayList<ToolbarButton>());
    }

    public EasyDietContentView(Sequence<NavigationButton> navigationButtons,
            Sequence<ToolbarButton> toolbarButtons)
    {
        if (navigationButtons == null)
        {
            throw new IllegalArgumentException("navigationButtons is null");
        }
        if (toolbarButtons == null)
        {
            throw new IllegalArgumentException("toolbarButtons is null");
        }

        this.navigationButtons = new ArrayList<NavigationButton>(
                navigationButtons);
        this.toolbarButtons = new ArrayList<ToolbarButton>(toolbarButtons);
        this.setOrientation(Orientation.VERTICAL);
        this.getStyles().put("fill", true);
        this.getStyles().put("padding", 10);
    }

    public NavigationButtonSequence getNavigationButtons()
    {
        return navigationButtonSequence;

    }

    public ToolbarButtonSequence getToolbarButtons()
    {
        return toolbarButtonSequence;
    }

    /**
     * What to do when the view is loading
     */
    public void onLoad()
    {

    }

    /**
     * What to do when the view is closing
     * @return true if it's allowed to close
     */
    public boolean onClose()
    {
        return true;
    }
}
