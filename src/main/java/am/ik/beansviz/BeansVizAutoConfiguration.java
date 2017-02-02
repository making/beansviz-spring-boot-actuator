/*
 * Copyright (C) 2017 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
