package am.ik.beansviz;

import org.springframework.boot.actuate.endpoint.BeansEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansVizAutoConfiguration {
	@Bean
	public BeansVizMvcHandler beansVizMvcHandler(BeansEndpoint beansEndpoint) {
		return new BeansVizMvcHandler(beansEndpoint);
	}

	@Configuration
	@ConditionalOnMissingClass(value = "org.springframework.boot.actuate.endpoint.mvc.AbstractNamedMvcEndpoint")
	static class ConfigForOldSpringBoot {
		@Bean
		@ConfigurationProperties(prefix = "endpoints.beansviz")
		public BeansVizMvcEndpoint beansVizMvcEndpoint(
				BeansVizMvcHandler beansVizMvcHandler) {
			return new BeansVizMvcEndpoint(beansVizMvcHandler);
		}
	}

	@Configuration
	@ConditionalOnClass(name = "org.springframework.boot.actuate.endpoint.mvc.AbstractNamedMvcEndpoint")
	static class ConfigForNewSpringBoot {
		@Bean
		@ConfigurationProperties(prefix = "endpoints.beansviz")
		public BeansVizNamedMvcEndpoint beansVizNamedMvcEndpoint(
				BeansVizMvcHandler beansVizMvcHandler) {
			return new BeansVizNamedMvcEndpoint(beansVizMvcHandler);
		}
	}
}
