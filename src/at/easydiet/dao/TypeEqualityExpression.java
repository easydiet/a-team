package at.easydiet.dao;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.engine.TypedValue;

// http://opensource.atlassian.com/projects/hibernate/secure/attachment/13384/TypeEqualityExpression.java
public class TypeEqualityExpression extends SimpleExpression
{

    private Class  classValue;
    private String classPropertyName;

    public TypeEqualityExpression(String propertyName, Class value)
    {
        super(propertyName, value, "=");
        this.classPropertyName = propertyName;
        this.classValue = value;
    }

    public TypedValue[] getTypedValues(Criteria criteria,
            CriteriaQuery criteriaQuery) throws HibernateException
    {

        return new TypedValue[] { fixDiscriminatorTypeValue(criteriaQuery
                .getTypedValue(criteria, classPropertyName, classValue)) };

    }

    private TypedValue fixDiscriminatorTypeValue(TypedValue typedValue)
    {
        Object value = typedValue.getValue();

        // check to make sure we can reconstruct an equivalent TypedValue
        if (!String.class.isInstance(value)
                || !typedValue.equals(new TypedValue(typedValue.getType(),
                        typedValue.getValue(), EntityMode.POJO)))

        return typedValue;

        /** replace leading and trailing apostrophes **/
        String svalue = value.toString();

        if (svalue.charAt(0) == '\''
                && svalue.charAt(svalue.length() - 1) == '\'')
            value = svalue.substring(0, svalue.length() - 1).substring(1);
        /********************************************/

        if (!value.equals(typedValue.getValue()))
            return new TypedValue(typedValue.getType(), value, EntityMode.POJO);
        else
            return typedValue;
    }

}