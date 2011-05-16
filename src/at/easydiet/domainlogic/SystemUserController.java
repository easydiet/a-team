package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.businessobjects.SystemUserBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.SystemUserDAO;
import at.easydiet.model.SystemUser;

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

	public List<?> getAllUsers() {
		SystemUserDAO dao = DAOFactory.getInstance().getSystemUserDAO();
		
		List<SystemUserBO> list = new ArrayList<SystemUserBO>();
		
		for(SystemUser bo : dao.findAll())
		{
			list.add(new SystemUserBO(bo));
		}
		
		return list;
	}
}
