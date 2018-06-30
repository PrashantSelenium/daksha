package daksha.core.uiauto.namestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daksha.core.uiauto.identifier.DefaultGuiElementMetaData;
import daksha.core.uiauto.identifier.GuiElementMetaData;
import daksha.tpi.uiauto.enums.GuiAutomationContext;

public class GuiNamespace {
	private Map<String, Map<GuiAutomationContext,GuiElementMetaData>> ns = new HashMap<String, Map<GuiAutomationContext,GuiElementMetaData>>();
	
	public void addElementMetaData(String name, GuiAutomationContext context, List<StringNVPair> locators) throws Exception {
		GuiElementMetaData emd = new DefaultGuiElementMetaData(locators);
		emd.process();
		if (!ns.containsKey(name.toLowerCase())){
			ns.put(name.toLowerCase(), new HashMap<GuiAutomationContext,GuiElementMetaData>());
		} 
		this.ns.get(name.toLowerCase()).put(context, emd);
	}
	
	public synchronized GuiElementMetaData getMetaData(String name, GuiAutomationContext context) {
		return this.ns.get(name.toLowerCase()).get(context);
	}

	public boolean has(String name) {
		return this.ns.containsKey(name.toLowerCase());
	}
	
	public boolean has(String name, GuiAutomationContext context) {
		if (has(name)) {
			return this.ns.get(name.toLowerCase()).containsKey(context);
		} else {
			return false;
		}
	}

}
