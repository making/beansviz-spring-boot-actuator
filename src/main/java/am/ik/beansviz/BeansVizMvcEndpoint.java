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

import org.springframework.boot.actuate.endpoint.mvc.AbstractMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.HypermediaDisabled;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@HypermediaDisabled
public class BeansVizMvcEndpoint extends AbstractMvcEndpoint {
	public final BeansVizMvcHandler beansVizMvcHandler;

	public BeansVizMvcEndpoint(BeansVizMvcHandler beansVizMvcHandler) {
		super("/beansviz", true);
		this.beansVizMvcHandler = beansVizMvcHandler;
	}

	@GetMapping(produces = BeansVizMvcHandler.IMAGE_SVG_VALUE)
	ResponseEntity<String> beansviz(
			@RequestParam(name = "all", defaultValue = "false") boolean all) {
		return beansVizMvcHandler.beansviz(all);
	}

}
