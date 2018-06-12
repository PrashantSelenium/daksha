package daksha.core.batteries.integration;

import java.util.List;

import daksha.core.batteries.ds.MessagesContainer;
import daksha.core.batteries.ds.NamesContainer;
import daksha.core.batteries.hocon.HoconReader;
import daksha.core.lib.ComponentIntegrator;
import daksha.core.property.ConfigProperty;

public abstract class AbstractComponentConfigurator implements ComponentConfigurator {
	private String baseDir = null;
	private String componentName;
	private ComponentIntegrator integrator = null;

	public AbstractComponentConfigurator(String componentName) {
		this.setComponentName(componentName);
	}

	@Override
	public String getBaseDir() {
		return baseDir;
	}

	@Override
	public void setBaseDir(String dir) {
		this.baseDir = dir;
	}

	@Override
	public ComponentIntegrator getIntegrator() {
		return integrator;
	}

	@Override
	public void setIntegrator(ComponentIntegrator integrator) {
		this.integrator = integrator;
	}

	public String getComponentName() {
		return componentName;
	}

	private void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	protected void registerProperty(ConfigProperty prop) throws Exception {
		integrator.registerProperty(this.getComponentName(), prop);
	}

	protected void processDefaults(HoconReader reader) throws Exception {
		reader.process();
		processConfigProperties(reader.getProperties());
		configureNames(getAllNames());
		configureMessages(getAllMessages());
	}

	protected abstract List<MessagesContainer> getAllMessages();

	protected abstract List<NamesContainer> getAllNames();

	public void configureMessages(List<MessagesContainer> messages) {
		this.integrator.populateMessages(messages);
	}

	public void configureNames(List<NamesContainer> names) {
		this.integrator.populateNames(names);
	}
}
