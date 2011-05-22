package at.easydiet.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import at.easydiet.model.Recipe;
import at.easydiet.util.StringUtils;

/**
 * The base DAO implementation for using with hibernate objects.
 * 
 * @param <T>
 *            The type of the entity.
 * @param <ID>
 *            The type of the primary key property.
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable>
{
    /**
     * Defines the class of the object
     */
    private Class<T> _persistentClass;

    /**
     * Current hibernate session
     */
    private Session  _session;

    /**
     * Initializes a new instance of the {@link GenericHibernateDAO} class.
     */
    @SuppressWarnings("unchecked")
    public GenericHibernateDAO()
    {
        this._persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Sets the current session of the DAO to the given one.
     * 
     * @param s
     *            the session to use.
     */
    public void setSession(Session s)
    {
        _session = s;
    }

    /**
     * Gets session currently used by this DAO.
     * 
     * @return the session currently used.
     */
    protected Session getSession()
    {
        if (_session == null)
            throw new IllegalStateException(
                    "Session has not been set on DAO before usage");
        return _session;
    }

    /**
     * Gets the class type which this DAO handles.
     * 
     * @return class type which this DAO handles
     */
    public Class<T> getPersistentClass()
    {
        return _persistentClass;
    }

    /**
     * Loads the item with the specified id.
     * 
     * @param id
     *            the id of the item to load.
     * @param lock
     *            true if the entry should be locked within the database,
     *            otherwise flase.
     * @return the loaded item.
     */
    @SuppressWarnings("unchecked")
    public T findById(ID id, boolean lock)
    {
        T entity;
        if (lock)
            entity = (T) getSession().load(getPersistentClass(), id,
                    LockOptions.UPGRADE);
        else
            entity = (T) getSession().load(getPersistentClass(), id);
        return entity;
    }

    /**
     * Loads all items within the database.
     * 
     * @return a list containing all items.
     */
    public List<T> findAll()
    {
        return findByCriteria();
    }

    /**
     * Loads a list of items, matching the properties set in the given instance.
     * 
     * @param exampleInstance
     *            The instance which conains the set properties.
     * @param excludeProperty
     *            The properties to ignore on compare
     * @return A list of all items matching the set properties of the given
     *         instance.
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty)
    {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty)
        {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    /**
     * Either save(Object) or update(Object) the given instance, depending upon
     * resolution of the unsaved-value checks (see the manual for discussion of
     * unsaved-value checking). This operation cascades to associated instances
     * if the association is mapped with cascade="save-update".
     * 
     * @param entity
     *            a transient or detached instance containing new or updated
     *            state.
     * @return the stored item.
     */
    public T makePersistent(T entity)
    {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    /**
     * Re-read the state of the given instance from the underlying database. It
     * is inadvisable to use this to implement long-running sessions that span
     * many business tasks. This method is, however, useful in certain special
     * circumstances. For example
     * <ul>
     * <li>where a database trigger alters the object state upon insert or
     * update</li>
     * <li>after executing direct SQL (eg. a mass update) in the same session</li>
     * <li>after inserting a Blob or Clob</li>
     * </ul>
     * 
     * @param entity
     *            a persistent or detached instance
     */
    public void refresh(T entity)
    {
        getSession().refresh(entity);
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an
     * instance associated with the receiving Session or a transient instance
     * with an identifier associated with existing persistent state. This
     * operation cascades to associated instances if the association is mapped
     * with cascade="delete".
     * 
     * @param entity
     *            the instance to be removed
     */
    public void makeTransient(T entity)
    {
        getSession().delete(entity);
    }

    /**
     * Force this session to flush. Must be called at the end of a unit of work,
     * before committing the transaction and closing the session (depending on
     * flush-mode, Transaction.commit() calls this method). Flushing is the
     * process of synchronizing the underlying persistent store with persistable
     * state held in memory.
     */
    public void flush()
    {
        getSession().flush();
    }

    /**
     * Completely clear the session. Evict all loaded instances and cancel all
     * pending saves, updates and deletions. Do not close open iterators or
     * instances of ScrollableResults.
     */
    public void clear()
    {
        getSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method. This method allows to
     * find entities matching the given criteria.
     * 
     * @param criterion
     *            Criteria to match
     * @return List of all entities matching the criteria
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion)
    {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion)
        {
            crit.add(c);
        }
        return crit.list();
    }

    /**
     * Use this inside subclasses as a convenience method. This method allows to
     * find entities matching the given criteria returning only 200 entries.
     * 
     * @param sort
     *            Sort direction
     * @param criterion
     *            Criteria to match
     * @return List of 200 entities matching the criteria
     */
    @SuppressWarnings("unchecked")
    public List<Recipe> findByCriteriaSearch(String sort,
            Criterion... criterion)
    {
        Criteria crit = getSession().createCriteria(getPersistentClass())
                .setMaxResults(200);
        if (!StringUtils.isNullOrWhitespaceOnly(sort))
        {
            crit.addOrder(Order.asc("name"));
        }
        for (Criterion c : criterion)
        {
            crit.add(c);
        }
        return crit.list();
    }

}