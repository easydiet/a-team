package at.easydiet.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a SystemUser
 */
public class SystemUser  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 5847655385889813965L;
    private long _systemUserId;
    private String _username;
    private String _password;
    private String _name;
    private String _email;
    private String _directDial;
    private String _department;
    private String _job;
    private Set<UserRight> _rights = new HashSet<UserRight>(0);

    /**
     * Initializes a new instance of the {@link SystemUser} class.
     */
    public SystemUser() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link SystemUser} class.
     * @param username the username to set for this instance
     * @param password the password to set for this instance
     * @param name the name to set for this instance
     * @param email the email to set for this instance
     */
    public SystemUser(String username, String password, String name, String email) 
    {
        _username = username;
        _password = password;
        _name = name;
        _email = email;
    }

    /**
     * Initializes a new instance of the {@link SystemUser} class.
     * @param username the username to set for this instance
     * @param password the password to set for this instance
     * @param name the name to set for this instance
     * @param email the email to set for this instance
     * @param directDial the directDial to set for this instance
     * @param department the department to set for this instance
     * @param job the job to set for this instance
     * @param rights the rights to set for this instance
     */
    public SystemUser(String username, String password, String name, String email, String directDial, String department, String job, Set<UserRight> rights) 
    {
       _username = username;
       _password = password;
       _name = name;
       _email = email;
       _directDial = directDial;
       _department = department;
       _job = job;
       _rights = rights;
    }
   
    /**       
     * Gets the systemUserId of this instance. 
     * @return the systemUserId currently set for this instance.
     */
    public long getSystemUserId() 
    {
        return _systemUserId;
    }
    
    /**       
     * Sets the systemUserId of this instance. 
     * @param systemUserId the new systemUserId of this instance.
     */    
    public void setSystemUserId(long systemUserId) 
    {
        _systemUserId = systemUserId;
    }
    
    /**       
     * Gets the username of this instance. 
     * @return the username currently set for this instance.
     */
    public String getUsername() 
    {
        return _username;
    }
    
    /**       
     * Sets the username of this instance. 
     * @param username the new username of this instance.
     */    
    public void setUsername(String username) 
    {
        _username = username;
    }
    
    /**       
     * Gets the password of this instance. 
     * @return the password currently set for this instance.
     */
    public String getPassword() 
    {
        return _password;
    }
    
    /**       
     * Sets the password of this instance. 
     * @param password the new password of this instance.
     */    
    public void setPassword(String password) 
    {
        _password = password;
    }
    
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _name;
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _name = name;
    }
    
    /**       
     * Gets the email of this instance. 
     * @return the email currently set for this instance.
     */
    public String getEmail() 
    {
        return _email;
    }
    
    /**       
     * Sets the email of this instance. 
     * @param email the new email of this instance.
     */    
    public void setEmail(String email) 
    {
        _email = email;
    }
    
    /**       
     * Gets the directDial of this instance. 
     * @return the directDial currently set for this instance.
     */
    public String getDirectDial() 
    {
        return _directDial;
    }
    
    /**       
     * Sets the directDial of this instance. 
     * @param directDial the new directDial of this instance.
     */    
    public void setDirectDial(String directDial) 
    {
        _directDial = directDial;
    }
    
    /**       
     * Gets the department of this instance. 
     * @return the department currently set for this instance.
     */
    public String getDepartment() 
    {
        return _department;
    }
    
    /**       
     * Sets the department of this instance. 
     * @param department the new department of this instance.
     */    
    public void setDepartment(String department) 
    {
        _department = department;
    }
    
    /**       
     * Gets the job of this instance. 
     * @return the job currently set for this instance.
     */
    public String getJob() 
    {
        return _job;
    }
    
    /**       
     * Sets the job of this instance. 
     * @param job the new job of this instance.
     */    
    public void setJob(String job) 
    {
        _job = job;
    }
    
    /**       
     * Gets the rights of this instance. 
     * @return the rights currently set for this instance.
     */
    public Set<UserRight> getRights() 
    {
        return _rights;
    }
    
    /**       
     * Sets the rights of this instance. 
     * @param rights the new rights of this instance.
     */    
    public void setRights(Set<UserRight> rights) 
    {
        _rights = rights;
    }
    
    /**
     * Returns a string representation of this instance.
     * @return a string
     */
    @Override
    public String toString() 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
        builder.append("]");
      
        return builder.toString();
    }
}
