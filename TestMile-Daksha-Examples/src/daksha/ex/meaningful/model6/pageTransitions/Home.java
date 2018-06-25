package daksha.ex.meaningful.model6.pageTransitions;

import daksha.core.meaningful.automator.proxy.GuiAutomatorProxy;
import daksha.ex.config.AppConfig;
import daksha.tpi.meaningful.element.GuiElement;

public class Home extends BaseSimpleGui{

	public Home(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}
	
	protected void load() throws Exception{
		this.goTo(AppConfig.WP_ADMIN_URL);
	}
	
	public Dashboard login() throws Exception {
		GuiElement userTextBox = this.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(AppConfig.USER_NAME);
		this.element("PASSWORD").enterText(AppConfig.PASSWORD);
		this.element("SUBMIT").click();		
		this.waitForBody();
		return new Dashboard(this.getAutomator());
	}

}