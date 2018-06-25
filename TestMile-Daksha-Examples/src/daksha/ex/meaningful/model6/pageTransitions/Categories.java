package daksha.ex.meaningful.model6.pageTransitions;

import daksha.core.meaningful.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.meaningful.element.GuiElement;
import daksha.tpi.meaningful.element.GuiMultiElement;

public class Categories extends BaseNestedGui{

	public Categories(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}

	public void playWithCats() throws Exception {
		GuiMultiElement tags = this.elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (GuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}		
	}
}