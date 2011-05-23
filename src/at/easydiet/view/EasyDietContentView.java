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

/**
 * Defines the look and feel of the EasyDiet application window content
 */
public class EasyDietContentView extends BoxPane
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(EasyDietContentView.class);

    /**
     * Defines the button in the navigation bar
     */
    public static class NavigationButton extends StackPane
    {
        /**
         * Stores the content view of the GUI
         */
        private EasyDietContentView easyDietContentView;

        /**
         * Stores the text to which view this button leads
         */
        private String              view;

        /**
         * Stores the button of the GUI
         */
        private SimpleButton        _button;

        /**
         * Initializes a new instance of the {@link NavigationButton} class.
         */
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

        /**
         * 
         */
        public void refresh()
        {

        }

        /**
         * Gets the text of this button
         * 
         * @return The text of this button
         */
        public String getText()
        {
            return ((ButtonData) _button.getButtonData()).getText();
        }

        /**
         * Sets the text of this button
         * 
         * @param text
         *            The new text
         */
        public void setText(String text)
        {
            ((ButtonData) _button.getButtonData()).setText(text);
        }

        /**
         * Gets the icon of this button
         * 
         * @return The icon of this button
         */
        public Image getIcon()
        {
            return ((ButtonData) _button.getButtonData()).getIcon();
        }

        /**
         * Sets the icon of this button
         * 
         * @param icon
         *            The new icon
         */
        public void setIcon(Image icon)
        {
            ((ButtonData) _button.getButtonData()).setIcon(icon);
        }

        /**
         * Sets the icon of this button
         * 
         * @param icon
         *            The path to the new icon
         */
        public void setIcon(String icon)
        {
            ((ButtonData) _button.getButtonData()).setIcon(icon);
        }

        /**
         * Sets the icon of this button
         * 
         * @param icon
         *            The {@link URL} to the new icon
         */
        public void setIcon(URL icon)
        {
            ((ButtonData) _button.getButtonData()).setIcon(icon);
        }

        /**
         * Sets the view of this button
         * 
         * @param value
         *            The name of the view
         */
        public void setView(String value)
        {
            this.view = value;
        }

        /**
         * Gets the view of this button
         * 
         * @return The name of the view
         */
        public String getView()
        {
            return this.view;
        }
    }

    /**
     * Defines the button in the toolbar
     */
    public static class ToolbarButton extends SimpleButton
    {
        /**
         * Stores the content view of the GUI
         */
        private EasyDietContentView easyDietContentView;

        /**
         * Initializes a new instance of the {@link ToolbarButton} class.
         */
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

    /**
     * Stores the {@link NavigationButton}s
     */
    private ArrayList<NavigationButton>     navigationButtons;
    /**
     * Stores the {@link ToolbarButton}s
     */
    private ArrayList<ToolbarButton>        toolbarButtons;
    /**
     * Stores the {@link NavigationButtonSequence}
     */
    private NavigationButtonSequence        navigationButtonSequence     = new NavigationButtonSequence();
    /**
     * Stores the {@link ToolbarButtonSequence}
     */
    private ToolbarButtonSequence           toolbarButtonSequence        = new ToolbarButtonSequence();

    /**
     * Stores the {@link EasyDietContentViewListener}s
     */
    private EasyDietContentViewListenerList easyDietContentViewListeners = new EasyDietContentViewListenerList();

    /**
     * Stores all {@link NavigationButton}s
     */
    public final class NavigationButtonSequence implements
            Sequence<NavigationButton>, Iterable<NavigationButton>
    {
        /**
         * Initializes a new instance of the {@link NavigationButtonSequence}
         * class.
         */
        private NavigationButtonSequence()
        {}

        /**
         * @see org.apache.pivot.collections.Sequence#add(java.lang.Object)
         */
        public int add(NavigationButton navigationButton)
        {
            int index = getLength();
            insert(navigationButton, index);

            return index;
        }

        /**
         * @see org.apache.pivot.collections.Sequence#insert(java.lang.Object,
         *      int)
         */
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

        /**
         * @see org.apache.pivot.collections.Sequence#update(int,
         *      java.lang.Object)
         */
        public NavigationButton update(int index,
                NavigationButton navigationButton)
        {
            throw new UnsupportedOperationException();
        }

        /**
         * @see org.apache.pivot.collections.Sequence#remove(java.lang.Object)
         */
        public int remove(NavigationButton navigationButton)
        {
            int index = indexOf(navigationButton);
            if (index != -1)
            {
                remove(index, 1);
            }

            return index;
        }

        /**
         * @see org.apache.pivot.collections.Sequence#remove(int, int)
         */
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

        /**
         * @see org.apache.pivot.collections.Sequence#get(int)
         */
        public NavigationButton get(int index)
        {
            return navigationButtons.get(index);
        }

        /**
         * @see org.apache.pivot.collections.Sequence#indexOf(java.lang.Object)
         */
        public int indexOf(NavigationButton navigationButton)
        {
            return navigationButtons.indexOf(navigationButton);
        }

        /**
         * @see org.apache.pivot.collections.Sequence#getLength()
         */
        public int getLength()
        {
            return navigationButtons.getLength();
        }

        /**
         * @see java.lang.Iterable#iterator()
         */
        public Iterator<NavigationButton> iterator()
        {
            return new ImmutableIterator<NavigationButton>(
                    navigationButtons.iterator());
        }
    }

    /**
     * Stores all {@link ToolbarButton}s
     */
    public final class ToolbarButtonSequence implements
            Sequence<ToolbarButton>, Iterable<ToolbarButton>
    {

        /**
         * Initializes a new instance of the {@link ToolbarButtonSequence}
         * class.
         */
        private ToolbarButtonSequence()
        {}

        /**
         * @see org.apache.pivot.collections.Sequence#add(java.lang.Object)
         */
        public int add(ToolbarButton toolbarButton)
        {
            int index = getLength();
            insert(toolbarButton, index);

            return index;
        }

        /**
         * @see org.apache.pivot.collections.Sequence#insert(java.lang.Object,
         *      int)
         */
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

        /**
         * @see org.apache.pivot.collections.Sequence#update(int,
         *      java.lang.Object)
         */
        public ToolbarButton update(int index, ToolbarButton toolbarButton)
        {
            throw new UnsupportedOperationException();
        }

        /**
         * @see org.apache.pivot.collections.Sequence#remove(java.lang.Object)
         */
        public int remove(ToolbarButton toolbarButton)
        {
            int index = indexOf(toolbarButton);
            if (index != -1)
            {
                remove(index, 1);
            }

            return index;
        }

        /**
         * @see org.apache.pivot.collections.Sequence#remove(int, int)
         */
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

        /**
         * @see org.apache.pivot.collections.Sequence#get(int)
         */
        public ToolbarButton get(int index)
        {
            return toolbarButtons.get(index);
        }

        /**
         * @see org.apache.pivot.collections.Sequence#indexOf(java.lang.Object)
         */
        public int indexOf(ToolbarButton toolbarButton)
        {
            return toolbarButtons.indexOf(toolbarButton);
        }

        /**
         * @see org.apache.pivot.collections.Sequence#getLength()
         */
        public int getLength()
        {
            return toolbarButtons.getLength();
        }

        /**
         * @see java.lang.Iterable#iterator()
         */
        public Iterator<ToolbarButton> iterator()
        {
            return new ImmutableIterator<ToolbarButton>(
                    toolbarButtons.iterator());
        }
    }

    /**
     * Stores all {@link EasyDietContentViewListener}s
     */
    private final class EasyDietContentViewListenerList extends
            ListenerList<EasyDietContentViewListener>
    {
        /**
         * If a new {@link NavigationButton} is inserted, notify all listeners
         * 
         * @param contentView
         *            The {@link EasyDietContentView}
         * @param index
         *            The index of the new {@link NavigationButton}
         */
        public void navigationButtonInserted(EasyDietContentView contentView,
                int index)
        {
            for (EasyDietContentViewListener listener : this)
            {
                listener.navigationButtonInserted(contentView, index);
            }
        }

        /**
         * If a {@link NavigationButton} is removed, notify all listeners
         * 
         * @param contentView
         *            The view
         * @param index
         *            The index of the deleted {@link NavigationButton}
         * @param navigationButtons
         *            Sequence of {@link NavigationButton}s
         */
        public void navigationButtonsRemoved(EasyDietContentView contentView,
                int index, Sequence<NavigationButton> navigationButtons)
        {
            for (EasyDietContentViewListener listener : this)
            {
                listener.navigationButtonsRemoved(contentView, index,
                        navigationButtons);
            }
        }

        /**
         * If a new {@link ToolbarButton} is inserted, notify all listeners
         * 
         * @param contentView
         *            The {@link EasyDietContentView}
         * @param index
         *            The index of the new {@link ToolbarButton}
         */
        public void toolbarButtonInserted(EasyDietContentView contentView,
                int index)
        {
            for (EasyDietContentViewListener listener : this)
            {
                listener.toolbarButtonInserted(contentView, index);
            }
        }

        /**
         * If a {@link ToolbarButton} is removed, notify all listeners
         * 
         * @param contentView
         *            The view
         * @param index
         *            The index of the deleted {@link ToolbarButton}
         * @param toolbarButtons
         *            Sequence of {@link ToolbarButton}s
         */
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

    /**
     * Initializes a new instance of the {@link EasyDietContentView} class.
     */
    public EasyDietContentView()
    {
        this(new ArrayList<NavigationButton>(), new ArrayList<ToolbarButton>());
    }

    /**
     * Initializes a new instance of the {@link EasyDietContentView} class.
     * 
     * @param navigationButtons
     *            Sequence of {@link NavigationButton}s
     * @param toolbarButtons
     *            Sequence of {@link ToolbarButton}s
     */
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

    /**
     * Gets the {@link NavigationButtonSequence}
     * 
     * @return The {@link NavigationButtonSequence}
     */
    public NavigationButtonSequence getNavigationButtons()
    {
        return navigationButtonSequence;

    }

    /**
     * Gets the {@link ToolbarButtonSequence}
     * 
     * @return The {@link ToolbarButtonSequence}
     */
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
     * 
     * @return true if it's allowed to close
     */
    public boolean onClose()
    {
        return true;
    }
}
