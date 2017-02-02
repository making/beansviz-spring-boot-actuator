package am.ik.beansviz;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

import java.util.List;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.BeansEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

public class BeansVizMvcHandler {
	public static final String IMAGE_SVG_VALUE = "image/svg+xml";
	private final BeansEndpoint beansEndpoint;

	public BeansVizMvcHandler(BeansEndpoint beansEndpoint) {
		this.beansEndpoint = beansEndpoint;
	}

	@SuppressWarnings("unchecked")
	ResponseEntity<String> beansviz(boolean all) {
		List<Object> info = beansEndpoint.invoke();
		MutableGraph graphs = mutGraph().setDirected();
		for (Object o : info) {
			Map<String, Object> context = (Map<String, Object>) o;
			MutableGraph graph = new MutableGraph().setDirected()
					.setLabel((String) context.get("context"));
			graphs.add(graph);
			List<Map<String, Object>> beans = (List<Map<String, Object>>) context
					.get("beans");
			for (Map<String, Object> bean : beans) {
				List<String> dependencies = (List<String>) bean.get("dependencies");
				if (!dependencies.isEmpty() || all) {
					MutableNode node = mutNode(shorten((String) bean.get("bean")));
					for (String dep : dependencies) {
						node.addLink(shorten(dep));
					}
					graph.add(node);
				}
			}
		}
		String svg;
		synchronized (this) {
			Graphviz.initEngine();
			try {
				svg = Graphviz.fromGraph(graphs).createSvg();
			}
			finally {
				Graphviz.releaseEngine();
			}
		}
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf(IMAGE_SVG_VALUE)).body(svg);
	}

	private String shorten(String name) {
		name = name.split("@")[0];
		if (name.contains(".")) {
			String[] split = name.split("\\.");
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < split.length - 1; i++) {
				builder.append(split[i].toLowerCase().charAt(0));
				builder.append(".");
			}
			builder.append(split[split.length - 1]);
			return builder.toString();
		}
		return name;
	}
}
