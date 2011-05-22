package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.businessobjects.SystemUserBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.SystemUserDAO;
import at.easydiet.model.SystemUser;

/**
 * Provides data and methods for handling system users
 */
public class SystemUserController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(SystemUserController.class);

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static SystemUserController          _singleton;

    /**
     * Get a Instance of this {@link SystemUserController}
     * 
     * @return The instance of this {@link SystemUserController}
     */
    public static SystemUserController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new SystemUserController();
        }
        return _singleton;
    }

    /**
     * Stores the current logged in {@link SystemUserBO}
     */
    private SystemUserBO _currentUser;

    /**
     * Gets the current logged in {@link SystemUserBO}
     * 
     * @return {@link SystemUserBO} currently logged in
     */
    public SystemUserBO getCurrentUser()
    {
        return _currentUser;
    }

    /**
     * Initializes a new instance of the {@link SystemUserController} class.
     */
    private SystemUserController()
    {
        // load default user
        SystemUserDAO dao = DAOFactory.getInstance().getSystemUserDAO();
        _currentUser = new SystemUserBO(dao.findAll().get(0));
    }

    /**
     * Get all registered {@link SystemUserBO}s
     * 
     * @return List of all registered {@link SystemUserBO}s
     */
    public List<?> getAllUsers()
    {
        SystemUserDAO dao = DAOFactory.getInstance().getSystemUserDAO();

        List<SystemUserBO> list = new ArrayList<SystemUserBO>();

        for (SystemUser bo : dao.findAll())
        {
            list.add(new SystemUserBO(bo));
        }

        return list;
    }
}
