package at.easydiet.view;

import java.io.IOException;
import java.util.Iterator;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.DefaultProperty;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.ImmutableIterator;
import org.apache.pivot.util.ListenerList;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.DialogCloseListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.TableView;

import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.TimeSpanBO;

/**
 * This is the background class for the TimeSpanContainer.bxml
 */
@DefaultProperty("mealContainers")
public class TimeSpanContainer extends BoxPane
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG                         = org.apache.log4j.Logger
                                                                                     .getLogger(TimeSpanContainer.class);

    /**
     * Stores the {@link CalendarButton} to set the start date
     */
    private CalendarButton                       _startDateButton;
    /**
     * Stores the {@link CalendarButton} to set the end date
     */
    private CalendarButton                       _endDateButton;
    /**
     * Stores the {@link Label} to show the duration of the {@link TimeSpanBO}
     */
    private Label                                _durationLabel;
    /**
     * Stores the {@link BoxPane} containing the meals of this
     * {@link TimeSpanBO}
     */
    private BoxPane                              _mealBox;
    /**
     * Stores the {@link Button} to delete this
     */
    private Button                               _deleteButton;
    /**
     * Stores the current opened {@link TimeSpanBO}
     */
    private TimeSpanBO                           _timeSpan;

    /**
     * Stores all the {@link MealContainer}s
     */
    private ArrayList<MealContainer>             _mealContainers;

    /**
     * Stores the {@link MealContainerSequence}
     */
    private MealContainerSequence                _mealContainerSequence      = new MealContainerSequence();
    /**
     * Stores the {@link TimeSpanContainerListener}s
     */
    private TimeSpanContainerListenerList        _timeSpanContainerListeners = new TimeSpanContainerListenerList();

    /**
     * Stores the {@link TableView} to manage the {@link DietParameterBO}
     */
    private ParameterTableViewTemplate           _parameterTableViewTemplate;

    /**
     * Stores whether the GUI is currently loading or not
     */
    private boolean                              _guiLoading;

    /**
     * Gets the timeSpan.
     * 
     * @return the timeSpan
     */
    public TimeSpanBO getTimeSpan()
    {
        return _timeSpan;
    }

    /**
     * Sets the timeSpan.
     * 
     * @param timeSpan
     *            the timeSpan to set
     */
    public void setTimeSpan(TimeSpanBO timeSpan)
    {
        _timeSpan = timeSpan;
        // parameterTableView
        _parameterTableViewTemplate.setParameterProvider(timeSpan);
        refreshUI();
    }

    /**
     * Re-set the GUI elements to the current values
     */
    private void refreshUI()
    {
        _guiLoading = true;
        _startDateButton.setSelectedDate(_timeSpan.getStartDate());
        _endDateButton.setSelectedDate(_timeSpan.getEndDate());
        _mealBox.removeAll();
        for (MealBO meal : _timeSpan.getMeals())
        {
            addMeal(meal);
        }
        _guiLoading = false;
        updateDurationLabel();
    }

    /**
     * This is the sequence of the {@link MealContainer}s
     */
    public final class MealContainerSequence implements
            Sequence<MealContainer>, Iterable<MealContainer>
    {

        /**
         * Initializes a new instance of the {@link MealContainerSequence}
         * class.
         */
        private MealContainerSequence()
        {}

        public int add(MealContainer mealContainer)
        {
            int index = getLength();
            insert(mealContainer, index);

            return index;
        }

        public void insert(MealContainer mealContainer, int index)
        {
            if (mealContainer == null)
            {
                throw new IllegalArgumentException("mealContainer is null.");
            }

            _mealContainers.insert(mealContainer, index);
            TimeSpanContainer.this._mealBox.insert(mealContainer, index);

            // Notify listeners
            _timeSpanContainerListeners.mealContainerInserted(
                    TimeSpanContainer.this, index);
        }

        public MealContainer update(int index, MealContainer mealContainer)
        {
            throw new UnsupportedOperationException();
        }

        public int remove(MealContainer mealContainer)
        {
            int index = indexOf(mealContainer);
            if (index != -1)
            {
                remove(index, 1);
            }

            return index;
        }

        public Sequence<MealContainer> remove(int index, int count)
        {
            Sequence<MealContainer> removed = _mealContainers.remove(index,
                    count);
            TimeSpanContainer.this._mealBox.remove(index, count);

            if (count > 0)
            {
                /*
                 * for (int i = 0, n = removed.getLength(); i < n; i++) {
                 * MealContainer container = removed.get(i); }
                 */

                _timeSpanContainerListeners.mealContainerRemoved(
                        TimeSpanContainer.this, index, removed);
            }

            return removed;
        }

        public MealContainer get(int index)
        {
            return _mealContainers.get(index);
        }

        public int indexOf(MealContainer toolbarButton)
        {
            return _mealContainers.indexOf(toolbarButton);
        }

        public int getLength()
        {
            return _mealContainers.getLength();
        }

        public Iterator<MealContainer> iterator()
        {
            return new ImmutableIterator<MealContainer>(
                    _mealContainers.iterator());
        }
    }

    /**
     * This is the list of all {@link TimeSpanContainerListener}s
     */
    public final class TimeSpanContainerListenerList extends
            ListenerList<TimeSpanContainerListener>
    {
        /**
         * Notify all listeners when a {@link MealContainer} is inserted into a
         * {@link TimeSpanContainer}
         * 
         * @param container
         *            The {@link TimeSpanContainer}
         * @param index
         *            The index of the new {@link MealContainer}
         */
        public void mealContainerInserted(TimeSpanContainer container, int index)
        {
            for (TimeSpanContainerListener listener : this)
            {
                listener.mealContainerInserted(container, index);
            }
        }

        /**
         * Notify all listeners when a {@link MealContainer} is removed from a
         * {@link TimeSpanContainer}
         * 
         * @param container
         *            The {@link TimeSpanContainer}
         * @param index
         *            The index of the removed {@link MealContainer}
         * @param mealContainer
         *            The removed {@link MealContainer}
         */
        public void mealContainerRemoved(TimeSpanContainer container,
                int index, Sequence<MealContainer> mealContainer)
        {
            for (TimeSpanContainerListener listener : this)
            {
                listener.mealContainerRemoved(container, index, mealContainer);
            }
        }
    }

    /**
     * Initializes a new instance of the {@link TimeSpanContainer} class.
     */
    public TimeSpanContainer()
    {
        this(new ArrayList<MealContainer>());
    }

    /**
     * Gets the {@link MealContainerSequence} of this instance
     * 
     * @return The {@link MealContainerSequence} of this instance
     */
    public MealContainerSequence getMealContainers()
    {
        return _mealContainerSequence;
    }

    /**
     * Gets the {@link TimeSpanContainerListenerList} of this instance
     * 
     * @return The {@link TimeSpanContainerListenerList} of this instance
     */
    public TimeSpanContainerListenerList getTimeSpanContainerListeners()
    {
        return _timeSpanContainerListeners;
    }

    /**
     * Initializes a new instance of the {@link TimeSpanContainer} class.
     * 
     * @param mealContainers
     *            Sequence of {@link MealContainer}s
     */
    public TimeSpanContainer(Sequence<MealContainer> mealContainers)
    {
        if (mealContainers == null)
        {
            throw new IllegalArgumentException("mealContainers is null");
        }

        _mealContainers = new ArrayList<MealContainer>();

        setOrientation(Orientation.VERTICAL);
        getStyles().put("fill", true);
        getStyles().put("spacing", 5);

        BXMLSerializer serializer = new BXMLSerializer();
        try
        {
            ViewController.getInstance();
            Border content = (Border) serializer.readObject(
                    TimeSpanContainer.class, "TimeSpanContainerContent"
                            + ViewController.PIVOT_FILE_EXTENSION);
            add(content);
            _startDateButton = (CalendarButton) serializer.getNamespace().get(
                    "startDate");
            _endDateButton = (CalendarButton) serializer.getNamespace().get(
                    "endDate");
            _durationLabel = (Label) serializer.getNamespace().get(
                    "durationLabel");
            _deleteButton = (Button) serializer.getNamespace().get(
                    "deleteButton");

            _parameterTableViewTemplate = (ParameterTableViewTemplate) serializer
                    .getNamespace().get("parameterTableViewTemplate");

            _mealBox = (BoxPane) serializer.getNamespace().get("mealBox");

            // listeners
            _deleteButton.getButtonPressListeners().add(
                    new ButtonPressListener()
                    {

                        public void buttonPressed(Button button)
                        {
                            deleteTimeSpan();
                        }
                    });

            ButtonPressListener createMeal = new ButtonPressListener()
            {
                public void buttonPressed(Button button)
                {
                    MealBO meal = DietPlanEditingController.getInstance()
                            .createMeal(_timeSpan);
                    addMeal(meal);
                }
            };

            Button createMealTop = (Button) serializer.getNamespace().get(
                    "createMealTop");
            Button createMealBottom = (Button) serializer.getNamespace().get(
                    "createMealBottom");

            createMealTop.getButtonPressListeners().add(createMeal);
            createMealBottom.getButtonPressListeners().add(createMeal);

            CalendarButtonSelectionListener dateChangedListener = new CalendarButtonSelectionListener()
            {
                public void selectedDateChanged(CalendarButton calendarButton,
                        CalendarDate previousSelectedDate)
                {
                    if (_guiLoading)
                    {
                        return;
                    }
                    LOG.debug(calendarButton == _startDateButton);
                    LOG.debug(calendarButton == _endDateButton);
                    LOG.debug(calendarButton.getSelectedDate());
                    CalendarDate start = _startDateButton.getSelectedDate();
                    _timeSpan.setStartDate(start);

                    CalendarDate end = _endDateButton.getSelectedDate();
                    if (end.compareTo(start) < 0)
                    {
                        _endDateButton.setSelectedDate(new CalendarDate(start
                                .toCalendar()));
                        return;
                    }

                    int days = end.subtract(start);
                    _timeSpan.setDuration(days);

                    updateDurationLabel();
                }
            };

            _startDateButton.getCalendarButtonSelectionListeners().add(
                    dateChangedListener);
            _endDateButton.getCalendarButtonSelectionListeners().add(
                    dateChangedListener);
        }
        catch (IOException e)
        {
            LOG.debug(e);
        }
        catch (SerializationException e)
        {
            LOG.debug(e);
        }
    }

    /**
     * Update the duration label
     */
    protected void updateDurationLabel()
    {
        int days = _timeSpan.getDuration();
        String dayLabel = days > 0 ? "Tage" : "Tag";
        _durationLabel.setText((days + 1) + " " + dayLabel);
        DietPlanEditingController.getInstance().validateDietPlan();
    }

    /**
     * Add a {@link MealBO} to the {@link TimeSpanBO}
     * 
     * @param meal
     *            The {@link MealBO} to add
     */
    protected void addMeal(MealBO meal)
    {
        MealContainer mealContainer = new MealContainer();
        mealContainer.setMeal(meal);
        _mealBox.add(mealContainer);
    }

    /**
     * Delete the current {@link TimeSpanBO}
     */
    private void deleteTimeSpan()
    {
        EasyAlerts.warning("Wollen Sie diesen Zeitraum wirklich löschen?",
                EasyAlerts.YES_NO, EasyAlerts.NO, getWindow(),
                new DialogCloseListener()
                {

                    public void dialogClosed(Dialog dialog, boolean modal)
                    {
                        if (((Alert) dialog).getSelectedOption().equals(
                                EasyAlerts.YES))
                        {
                            getParent().remove(TimeSpanContainer.this);
                            DietPlanEditingController.getInstance()
                                    .deleteTimeSpan(_timeSpan);
                        }
                    }

                });

    }
}
