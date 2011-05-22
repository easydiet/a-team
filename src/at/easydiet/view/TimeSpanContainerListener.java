package at.easydiet.view;

import org.apache.pivot.collections.Sequence;

/**
 * This is the {@link TimeSpanContainer} listener
 */
public interface TimeSpanContainerListener
{
    /**
     * Table pane listener adapter.
     */
    public static class Adapter implements TimeSpanContainerListener
    {

        public void mealContainerInserted(TimeSpanContainer container, int index)
        {}

        public void mealContainerRemoved(TimeSpanContainer container,
                int index, Sequence<MealContainer> mealContainers)
        {}
    }

    /**
     * Called when a {@link MealContainer} has been inserted into a
     * {@link TimeSpanContainer}.
     * 
     * @param container
     * @param index
     */
    public void mealContainerInserted(TimeSpanContainer container, int index);

    /**
     * Called when a {@link MealContainer} has been removed a
     * {@link TimeSpanContainer}.
     * 
     * @param container
     * @param index
     * @param mealContainers
     */
    public void mealContainerRemoved(TimeSpanContainer container, int index,
            Sequence<MealContainer> mealContainers);

}
