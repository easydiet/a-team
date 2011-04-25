package at.easydiet.domainlogic;

import at.easydiet.businessobjects.SystemUserBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.SystemUserDAO;

public class SystemUserController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(SystemUserController.class);
    
    private static SystemUserController _singleton;
    
    public static SystemUserController getInstance()
    {
        if(_singleton == null)
        {
            _singleton = new SystemUserController();
        }
        return _singleton;
    }
    
    private SystemUserBO _currentUser;
    
    public SystemUserBO getCurrentUser()
    {
        return _currentUser;
    }
    
    private SystemUserController()
    {
        // load default user
        SystemUserDAO dao = DAOFactory.getInstance().getSystemUserDAO();
        _currentUser = new SystemUserBO(dao.findAll().get(0));
    }
}