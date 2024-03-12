package example.uppercase;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class UppercaseHandler {

	@Autowired
	private Function<String, String> uppercase;

	@FunctionName("uppercase")
	public String execute(
		@HttpTrigger(
			name = "req",
			methods = {HttpMethod.GET, HttpMethod.POST},
			authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
		ExecutionContext context
	) {
		context.getLogger().warning("Using Java (" + System.getProperty("java.version") + ")");
		return uppercase.apply(request.getBody().get());
	}
}
