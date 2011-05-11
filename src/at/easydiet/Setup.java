package at.easydiet;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.easydiet.dao.HibernateUtil;

/**
 * A simple setup utiltity importing the default schema and system data.
 */
public class Setup
{
    private static final Logger LOG = Logger.getLogger(Setup.class);
    
    /**
     * A setup runner
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        Setup s = new Setup();
        s.doIt();
    }

    /**
     * Run the setup
     * @throws Exception
     */
    private void doIt() throws Exception
    {
        BasicConfigurator.configure();
        createSchema();
        importDefaultData();
    }

    /**
     * Setup the database schema.
     * @throws Exception
     */
    private void createSchema() throws Exception
    {
        LOG.info("Creating schema");
        HibernateUtil.currentSession();
    }

    /**
     * Importing the default system data.
     */
    protected void importDefaultData()
    {
        LOG.info("Importing Data");
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Session dom4jSession = session.getSession(EntityMode.DOM4J);

        SAXReader saxReader = new SAXReader();
        try
        {
            Document document = saxReader.read(Setup.class.getResource("/DefaultData.xml"));
            
            for (Object obj : document.selectNodes("//name"))
            {
                Node node = (Node)obj;
                node.setText(node.getText().trim());
            }
            
            List<?> nodes = document.selectNodes("/Data/*/*");
            
            for (Object obj : nodes)
            {
                Node node = (Node)obj;
                
                Class<?> clazz = Class.forName("at.easydiet.model." + node.getName());
                LOG.info("Importing " + clazz.getName());
                dom4jSession.save(clazz.getName(), node);
            }
            
            session.flush();
            tx.commit();
            HibernateUtil.closeSession();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tx.rollback();
        }
        LOG.info("Importing ended");
    }

}
