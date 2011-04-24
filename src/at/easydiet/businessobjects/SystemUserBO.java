package at.easydiet.businessobjects;

import java.util.Set;

import at.easydiet.model.SystemUser;
import at.easydiet.model.UserRight;


public class SystemUserBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(SystemUserBO.class);
    
    private SystemUser _systemUser;
    
    public SystemUser getSystemUser()
    {
        return _systemUser;
    }

    /** 
     * Initializes a new instance of the {@link SystemUserBO} class. 
     * @param systemUser
     */
    public SystemUserBO(SystemUser systemUser)
    {
        super();
        _systemUser = systemUser;
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getSystemUserId()
     */
    public long getSystemUserId()
    {
        return _systemUser.getSystemUserId();
    }

    /**
     * @param systemUserId
     * @see at.easydiet.model.SystemUser#setSystemUserId(long)
     */
    public void setSystemUserId(long systemUserId)
    {
        _systemUser.setSystemUserId(systemUserId);
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getUsername()
     */
    public String getUsername()
    {
        return _systemUser.getUsername();
    }

    /**
     * @param username
     * @see at.easydiet.model.SystemUser#setUsername(java.lang.String)
     */
    public void setUsername(String username)
    {
        _systemUser.setUsername(username);
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getPassword()
     */
    public String getPassword()
    {
        return _systemUser.getPassword();
    }

    /**
     * @param password
     * @see at.easydiet.model.SystemUser#setPassword(java.lang.String)
     */
    public void setPassword(String password)
    {
        _systemUser.setPassword(password);
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getName()
     */
    public String getName()
    {
        return _systemUser.getName();
    }

    /**
     * @param name
     * @see at.easydiet.model.SystemUser#setName(java.lang.String)
     */
    public void setName(String name)
    {
        _systemUser.setName(name);
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getEmail()
     */
    public String getEmail()
    {
        return _systemUser.getEmail();
    }

    /**
     * @param email
     * @see at.easydiet.model.SystemUser#setEmail(java.lang.String)
     */
    public void setEmail(String email)
    {
        _systemUser.setEmail(email);
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getDirectDial()
     */
    public String getDirectDial()
    {
        return _systemUser.getDirectDial();
    }

    /**
     * @param directDial
     * @see at.easydiet.model.SystemUser#setDirectDial(java.lang.String)
     */
    public void setDirectDial(String directDial)
    {
        _systemUser.setDirectDial(directDial);
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getDepartment()
     */
    public String getDepartment()
    {
        return _systemUser.getDepartment();
    }

    /**
     * @param department
     * @see at.easydiet.model.SystemUser#setDepartment(java.lang.String)
     */
    public void setDepartment(String department)
    {
        _systemUser.setDepartment(department);
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getJob()
     */
    public String getJob()
    {
        return _systemUser.getJob();
    }

    /**
     * @param job
     * @see at.easydiet.model.SystemUser#setJob(java.lang.String)
     */
    public void setJob(String job)
    {
        _systemUser.setJob(job);
    }

    /**
     * @return
     * @see at.easydiet.model.SystemUser#getRights()
     */
    public Set<UserRight> getRights()
    {
        return _systemUser.getRights();
    }

    /**
     * @param rights
     * @see at.easydiet.model.SystemUser#setRights(java.util.Set)
     */
    public void setRights(Set<UserRight> rights)
    {
        _systemUser.setRights(rights);
    }
    


    
}
