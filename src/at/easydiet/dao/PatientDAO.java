package at.easydiet.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import at.easydiet.model.Patient;
import at.easydiet.validation.PatientValidator;

/**
 * A DAO implementation for Patient objects.
 */
public class PatientDAO extends GenericHibernateDAO<Patient, Long>
{
    /**
     * Logger for debugging purposes
     */
    private static final Logger LOG = Logger.getLogger(PatientDAO.class);

    /**
     * Get a patient by forename, lastname and/or insurancenumber
     * 
     * @param query
     *            String with forename, lastname and/or insurancenumber
     *            seperated by whitespaces
     * @return List of all {@link Patient}s matching the query
     */
    public List<Patient> findByQuery(String query)
    {
        // find forename, lastname and insuranceNumber within this query
        String[] parts = query.split(" ");

        String forename = null;
        String lastname = null;
        String insuranceNumber = null;

        for (String string : parts)
        {
            if (forename != null && lastname != null && insuranceNumber != null)
            {
                LOG.warn("Ignoring additional strings, will use the first found templates");
            }
            else if (insuranceNumber == null
                    && PatientValidator.isInsuranceNumber(string, true))
            {
                insuranceNumber = string;
            }
            else if (forename == null)
            {
                forename = string;
            }
            else if (lastname == null)
            {
                lastname = string;
            }
        }

        return findByData(forename, lastname, insuranceNumber);
    }

    /**
     * Find a patient by data
     * 
     * @param forename
     *            The forename
     * @param lastname
     *            The lastname
     * @param insuranceNumber
     *            The insurancenumber
     * @return List of all {@link Patient} matching the data
     */
    public List<Patient> findByData(String forename, String lastname,
            String insuranceNumber)
    {
        Patient template1 = new Patient();
        template1.setForename(forename);
        template1.setLastname(lastname);
        template1.setInsuranceNumber(insuranceNumber);

        Patient template2 = new Patient();
        template2.setForename(lastname);
        template2.setLastname(forename);
        template2.setInsuranceNumber(insuranceNumber);

        Example ex = Example.create(template1).enableLike(MatchMode.ANYWHERE)
                .excludeZeroes().ignoreCase();
        Example ex2 = Example.create(template2).enableLike(MatchMode.ANYWHERE)
                .excludeZeroes().ignoreCase();

        return findByCriteria(Restrictions.or(ex, ex2));
    }
}