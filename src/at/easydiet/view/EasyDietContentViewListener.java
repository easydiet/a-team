package at.easydiet.view;

import org.apache.pivot.collections.Sequence;

import at.easydiet.view.EasyDietContentView.NavigationButton;
import at.easydiet.view.EasyDietContentView.ToolbarButton;

/**
 * Listens to changes in the {@link EasyDietContentView}
 */
public interface EasyDietContentViewListener
{
    /**
     * Table pane listener adapter.
     */
    public static class Adapter implements EasyDietContentViewListener
    {
        public void navigationButtonInserted(EasyDietContentView contentView,
                int index)
        {}

        public void navigationButtonsRemoved(EasyDietContentView contentView,
                int index, Sequence<NavigationButton> navigationButtons)
        {}

        public void toolbarButtonInserted(EasyDietContentView contentView,
                int index)
        {}

        public void toolbarButtonsRemoved(EasyDietContentView contentView,
                int index, Sequence<ToolbarButton> toolbarButtons)
        {}
    }

    /**
     * Called when a navigationButton has been inserted into a
     * EasyDietContentView.
     * 
     * @param contentView
     * @param index
     */
    public void navigationButtonInserted(EasyDietContentView contentView,
            int index);

    /**
     * Called when a navigationButton has been removed a EasyDietContentView.
     * 
     * @param contentView
     * @param index
     * @param navigationButtons
     */
    public void navigationButtonsRemoved(EasyDietContentView contentView,
            int index, Sequence<NavigationButton> navigationButtons);

    /**
     * Called when a toolbarButton has been inserted into a EasyDietContentView.
     * 
     * @param contentView
     * @param index
     */
    public void toolbarButtonInserted(EasyDietContentView contentView, int index);

    /**
     * Called when a toolbarButton has been removed a EasyDietContentView.
     * 
     * @param contentView
     * @param index
     * @param toolbarButtons
     */
    public void toolbarButtonsRemoved(EasyDietContentView contentView,
            int index, Sequence<ToolbarButton> toolbarButtons);

}
