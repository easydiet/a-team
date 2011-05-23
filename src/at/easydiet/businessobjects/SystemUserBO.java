package at.easydiet.businessobjects;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.SystemUser;
import at.easydiet.model.UserRight;

/**
 * This class encapsules a SystemUser instance.
 */
public class SystemUserBO 
{
	private SystemUser _model;
	
    /**
     * Initializes a new instance of the {@link SystemUserBO} class.
     */
	public SystemUserBO()
	{
		// TODO: add default values
		this(new SystemUser());
	}
	
    /**
     * Initializes a new instance of the {@link SystemUserBO} class.
     * @param model the original model object
     */
	public SystemUserBO(SystemUser model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link SystemUser} object.
	 */
 	public SystemUser getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the systemUserId of this instance. 
     * @return the systemUserId currently set for this instance.
     */
    public long getSystemUserId() 
    {
        return _model.getSystemUserId();
    }
    
    /**       
     * Sets the systemUserId of this instance. 
     * @param systemUserId the new systemUserId of this instance.
     */    
    public void setSystemUserId(long systemUserId) 
    {
        _model.setSystemUserId(systemUserId);
    }

    /**       
     * Gets the username of this instance. 
     * @return the username currently set for this instance.
     */
    public String getUsername() 
    {
        return _model.getUsername();
    }
    
    /**       
     * Sets the username of this instance. 
     * @param username the new username of this instance.
     */    
    public void setUsername(String username) 
    {
        _model.setUsername(username);
    }

    /**       
     * Gets the password of this instance. 
     * @return the password currently set for this instance.
     */
    public String getPassword() 
    {
        return _model.getPassword();
    }
    
    /**       
     * Sets the password of this instance. 
     * @param password the new password of this instance.
     */    
    public void setPassword(String password) 
    {
        _model.setPassword(password);
    }

    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _model.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _model.setName(name);
    }

    /**       
     * Gets the email of this instance. 
     * @return the email currently set for this instance.
     */
    public String getEmail() 
    {
        return _model.getEmail();
    }
    
    /**       
     * Sets the email of this instance. 
     * @param email the new email of this instance.
     */    
    public void setEmail(String email) 
    {
        _model.setEmail(email);
    }

    /**       
     * Gets the directDial of this instance. 
     * @return the directDial currently set for this instance.
     */
    public String getDirectDial() 
    {
        return _model.getDirectDial();
    }
    
    /**       
     * Sets the directDial of this instance. 
     * @param directDial the new directDial of this instance.
     */    
    public void setDirectDial(String directDial) 
    {
        _model.setDirectDial(directDial);
    }

    /**       
     * Gets the department of this instance. 
     * @return the department currently set for this instance.
     */
    public String getDepartment() 
    {
        return _model.getDepartment();
    }
    
    /**       
     * Sets the department of this instance. 
     * @param department the new department of this instance.
     */    
    public void setDepartment(String department) 
    {
        _model.setDepartment(department);
    }

    /**       
     * Gets the job of this instance. 
     * @return the job currently set for this instance.
     */
    public String getJob() 
    {
        return _model.getJob();
    }
    
    /**       
     * Sets the job of this instance. 
     * @param job the new job of this instance.
     */    
    public void setJob(String job) 
    {
        _model.setJob(job);
    }


	private List<UserRightBO> _rights;
	
    /**
     * Gets a list of referenced Rights of this instance.
     * This list is cached, use {@link SystemUser#updateRightsCache()) to update this cache.
     * @return a cached list of referenced Rights wrapped into the correct businessobject. 
     */
    public List<UserRightBO> getRights()
    {
        if(_rights == null) 
        {
            _rights = new ArrayList<UserRightBO>();
            for(UserRight rights : _model.getRights())
            {
                _rights.add(new UserRightBO(rights));
            }
        }
        return _rights;
    }
	
    /**
     * Adds a new UserRight to the list of referenced rights.
     * The cache will updated
     * @param rights the UserRight to add. 
     */
    public void addRights(UserRightBO rights)
    {
        getRights().add(rights);
        _model.getRights().add(rights.getModel());
    }
    
        
    /**
     * Removes the given UserRight from the list of referenced rights.
     * The cache will updated
     * @param rights the timespan to add. 
     */
    public void removeRights(UserRightBO rights)
    {
        getRights().remove(rights);
        _model.getRights().remove(rights.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced rights.
     */
    public void updateRightsCache()
    {
        _rights = null;
        getRights();
    }
    
    
    public String toString()
    {
        return getName();
    }


}