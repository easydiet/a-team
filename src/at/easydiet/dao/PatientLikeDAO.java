package at.easydiet.dao;

import java.util.List;

import org.hibernate.criterion.Example;

import at.easydiet.model.Patient;
import at.easydiet.model.PatientLike;

/**
 * A DAO implementation for PatientLike objects.
 */
public class PatientLikeDAO extends GenericHibernateDAO<PatientLike, Long>
{

    /**
     * Find {@link PatientLike}s by {@link Patient}
     * 
     * @param patient Get {@link PatientLike}s from this {@link Patient}
     * @return List of all {@link PatientLike} from the {@link Patient}
     */
    public List<PatientLike> findByPatient(Patient patient)
    {
        PatientLike like = new PatientLike();
        like.setPatient(patient);

        Example ex = Example.create(like).excludeZeroes();
        return findByCriteria(ex);
    }
}