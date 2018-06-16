/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package daksha.tpi.leaping.pageobject;

import java.util.Map;

import daksha.tpi.leaping.automator.GuiAutomator;

public interface App{
	
	String getName();
	void setName(String name);

	void addElement(String uiElementName, Map<String, String> elemMap) throws Exception;
	void addElement(String uiLabel, String uiElementName, Map<String, String> elemMap) throws Exception;

	void registerPage(String uiLabel, GuiAutomator uiDriver, String mapPath) throws Exception;
	//void registerPage(String uiLabel, UiDriver uiDriver, PageMapper mapper) throws Exception;

	Page page(String name) throws Exception;
	
}
