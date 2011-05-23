package at.easydiet.view;

import org.apache.pivot.util.ListenerList;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableView.RowEditor;
import org.apache.pivot.wtk.content.TableViewRowEditor;

/**
 * This editor is used to edit tableviews. Mainly used for the adapter
 */
public class EasyTableViewRowEditor extends TableViewRowEditor
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(EasyTableViewRowEditor.class);

    /**
     * Row editor listener list.
     */
    public static class RowEditorListenerList extends
            ListenerList<RowEditorListener> implements RowEditorListener
    {
        public Vote previewEditRow(RowEditor rowEditor, TableView tableView,
                int rowIndex, int columnIndex)
        {
            Vote vote = Vote.APPROVE;

            for (RowEditorListener listener : this)
            {
                vote = vote.tally(listener.previewEditRow(rowEditor, tableView,
                        rowIndex, columnIndex));
            }

            return vote;
        }

        public void editRowVetoed(RowEditor rowEditor, Vote reason)
        {
            for (RowEditorListener listener : this)
            {
                listener.editRowVetoed(rowEditor, reason);
            }
        }

        public void rowEditing(RowEditor rowEditor, TableView tableView,
                int rowIndex, int columnIndex)
        {
            for (RowEditorListener listener : this)
            {
                listener.rowEditing(rowEditor, tableView, rowIndex, columnIndex);
            }
        }

        public void changesSaved(RowEditor rowEditor, TableView tableView,
                int rowIndex, int columnIndex)
        {
            for (RowEditorListener listener : this)
            {
                listener.changesSaved(rowEditor, tableView, rowIndex,
                        columnIndex);
            }
        }

        public void editCancelled(RowEditor rowEditor, TableView tableView,
                int rowIndex, int columnIndex)
        {
            for (RowEditorListener listener : this)
            {
                listener.editCancelled(rowEditor, tableView, rowIndex,
                        columnIndex);
            }
        }
    }

    /**
     * The row editor listener interface. This provides callers with
     * notifications about a row editor's activity.
     */
    public interface RowEditorListener
    {
        /**
         * Row editor listener adapter.
         */
        public static class Adapter implements RowEditorListener
        {
            public Vote previewEditRow(RowEditor rowEditor,
                    TableView tableView, int rowIndex, int columnIndex)
            {
                return Vote.APPROVE;
            }

            public void editRowVetoed(RowEditor rowEditor, Vote reason)
            {}

            public void rowEditing(RowEditor rowEditor, TableView tableView,
                    int rowIndex, int columnIndex)
            {}

            public void changesSaved(RowEditor rowEditor, TableView tableView,
                    int rowIndex, int columnIndex)
            {}

            public void editCancelled(RowEditor rowEditor, TableView tableView,
                    int rowIndex, int columnIndex)
            {}
        }

        /**
         * Called to preview a row edit.
         * 
         * @param rowEditor
         *            The row editor
         * 
         * @param tableView
         *            The table view containing the row to be edited.
         * 
         * @param rowIndex
         *            The index of the row to edit.
         * 
         * @param columnIndex
         *            The index of the column to edit.
         * 
         * @return A vote on whether editing should be allowed to begin.
         */
        public Vote previewEditRow(RowEditor rowEditor, TableView tableView,
                int rowIndex, int columnIndex);

        /**
         * Called when a row edit was vetoed by a listener in the preview event.
         * 
         * @param rowEditor
         *            The row editor
         * 
         * @param reason
         *            The reason for the veto
         */
        public void editRowVetoed(RowEditor rowEditor, Vote reason);

        /**
         * Called when editing has begun.
         * 
         * @param rowEditor
         *            The row editor
         * 
         * @param tableView
         *            The table view containing the row being edited.
         * 
         * @param rowIndex
         *            The index of the row being edited.
         * 
         * @param columnIndex
         *            The index of the column being edited.
         */
        public void rowEditing(RowEditor rowEditor, TableView tableView,
                int rowIndex, int columnIndex);

        /**
         * Called when changes have been saved.
         * 
         * @param rowEditor
         *            The row editor
         * 
         * @param tableView
         *            The table view containing the row that was edited.
         * 
         * @param rowIndex
         *            The index of the row that was edited.
         * 
         * @param columnIndex
         *            The index of the column that was edited.
         */
        public void changesSaved(RowEditor rowEditor, TableView tableView,
                int rowIndex, int columnIndex);

        /**
         * Called when an edit has been cancelled.
         * 
         * @param rowEditor
         *            The row editor
         * 
         * @param tableView
         *            The table view containing the row that was being edited.
         * 
         * @param rowIndex
         *            The index of the row that was being edited.
         * 
         * @param columnIndex
         *            The index of the column that was being edited.
         */
        public void editCancelled(RowEditor rowEditor, TableView tableView,
                int rowIndex, int columnIndex);
    }

    /**
     * Stores al the {@link RowEditorListener}s
     */
    private RowEditorListenerList _rowEditorListeners = new RowEditorListenerList();

    /**
     * Gets the list of all {@link RowEditorListener}s
     * 
     * @return List of all {@link RowEditorListener}s of this Instance
     */
    public RowEditorListenerList getRowEditorListeners()
    {
        return _rowEditorListeners;
    }

    /**
     * @see org.apache.pivot.wtk.content.TableViewRowEditor#beginEdit(org.apache.pivot.wtk.TableView,
     *      int, int)
     */
    @Override
    public void beginEdit(TableView tableView, int rowIndex, int columnIndex)
    {
        Vote vote = _rowEditorListeners.previewEditRow(this, tableView,
                rowIndex, columnIndex);
        if (vote == Vote.APPROVE)
        {
            _rowEditorListeners.rowEditing(this, tableView, rowIndex,
                    columnIndex);
            super.beginEdit(tableView, rowIndex, columnIndex);
        }
        else
        {
            _rowEditorListeners.editRowVetoed(this, vote);
        }
    }

    /**
     * @see org.apache.pivot.wtk.content.TableViewRowEditor#endEdit(boolean)
     */
    @Override
    public void endEdit(boolean result)
    {
        super.endEdit(result);

        if (result)
        {
            _rowEditorListeners.changesSaved(this, getTableView(),
                    getRowIndex(), 0);
        }
        else
        {
            _rowEditorListeners.editCancelled(this, getTableView(),
                    getRowIndex(), 0);
        }
    }

}
