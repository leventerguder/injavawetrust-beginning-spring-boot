/**
 * 
 */
package condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Siva
 *
 */
public class MongoDriverNotPresentsCondition implements Condition
{

	@Override
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata)
	{
		try
		{
			Class.forName("com.mongodb.connection.Server");
			return false;
		} catch (ClassNotFoundException e)
		{
			return true;
		}
	}
}
