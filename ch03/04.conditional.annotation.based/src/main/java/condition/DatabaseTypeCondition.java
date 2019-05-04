package condition;

import java.util.Map;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DatabaseTypeCondition implements Condition {

	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
		
		Map<String, Object> attributes = metadata.getAnnotationAttributes(DatabaseType.class.getName());
		String type = (String) attributes.get("value");
		String enabledDBType = System.getProperty("dbType");
		return (enabledDBType != null && type != null && enabledDBType.equalsIgnoreCase(type));
		
	}

}