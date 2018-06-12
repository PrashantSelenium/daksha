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
package daksha.core.leaping.interfaces.appactions;

import daksha.tpi.leaping.enums.UiElementType;
import daksha.tpi.leaping.interfaces.UiElement;

public interface ElementCreationHandler {
	UiElement elementWithId(String id) throws Exception;
	UiElement elementWithName(String name) throws Exception;
	UiElement elementWithClass(String klass) throws Exception;
	UiElement elementWithCss(String cssSelector) throws Exception;
	UiElement elementWithLinkText(String text) throws Exception;
	UiElement elementWithPartialLinkText(String textContent) throws Exception;
	UiElement elementWithXPath(String xpath) throws Exception;
	UiElement elementWithXText(String text) throws Exception;
	UiElement elementWithXPartialText(String textContent) throws Exception;
	UiElement elementWithXValue(String value) throws Exception;
	UiElement elementWithXImageSource(String path) throws Exception;
	UiElement elementOfXType(UiElementType type) throws Exception;
	UiElement elementBasedOnImage(String imagePath) throws Exception;
}
