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

public class Setup
{
    private static final Logger LOGGER = Logger.getLogger(Setup.class);
    public static void main(String[] args) throws Exception
    {
        Setup s = new Setup();
        s.doIt();
    }

    private void doIt() throws Exception
    {
        BasicConfigurator.configure();
        createSchema();
        importDefaultData();
    }

    private void createSchema() throws Exception
    {
        HibernateUtil.currentSession();
    }

    private void importDefaultData()
    {
        LOGGER.info("Importing Data");
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Session dom4jSession = session.getSession(EntityMode.DOM4J);

        SAXReader saxReader = new SAXReader();
        try
        {
            Document document = saxReader.read(Setup.class.getResource("/DefaultData.xml"));
            
            List<?> nodes = document.selectNodes("/Data/*/*");
            
            for (Object obj : nodes)
            {
                Node node = (Node)obj;
                
                Class<?> clazz = Class.forName("at.easydiet.model." + node.getName());
                LOGGER.info("Importing " + clazz.getName());
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
        LOGGER.info("Importing ended");
    }

}
