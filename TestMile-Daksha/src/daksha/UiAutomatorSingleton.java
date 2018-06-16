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
package daksha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daksha.core.leaping.enums.AppiumAndroidBrowserType;
import daksha.core.leaping.enums.AppiumIosBrowserType;
import daksha.core.leaping.enums.AppiumMobilePlatformType;
import daksha.core.leaping.enums.IdentifyBy;
import daksha.core.leaping.enums.MobileNativeIdentifyBy;
import daksha.core.leaping.enums.MobileWebIdentifyBy;
import daksha.core.leaping.enums.NativeIdentifyBy;
import daksha.core.leaping.enums.ScreenIdentifyBy;
import daksha.core.leaping.enums.WebIdentifyBy;
import daksha.core.leaping.loader.CentralPageDefMap;
import daksha.core.leaping.loader.DefaultCentralPageDefMap;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.sysauto.utils.DataUtils;

public enum UiAutomatorSingleton {
	INSTANCE;

	CentralPageDefMap uicentralMap = new DefaultCentralPageDefMap();
	// UI Automator
	private static List<String> allowedGenericIdentifiers = null;
	private static List<String> allowedWebIdentifiers = null;
	private static List<String> allowedNativeIdentifiers = null;
	private static List<String> allowedMobileWebIdentifiers = null;
	private static List<String> allowedMobileNativeIdentifiers = null;
	private static List<String> allowedScreenIdentifiers = null;
	private static List<String> allAllowedUiElementTypes = null;
	private static Map<UiAutomationContext, String> automationContextNames = null;
	// Appium
	private static List<String> allowedAppiumPlatforms = new ArrayList<String>();;
	private static List<String> allowedAppiumAndroidBrowsers = new ArrayList<String>();;
	private static List<String> allowedAppiumIosBrowsers = new ArrayList<String>();

	public void init() throws Exception{
		/* Appium */
		for (AppiumMobilePlatformType prop: AppiumMobilePlatformType.class.getEnumConstants()){
			allowedAppiumPlatforms.add(prop.toString());
		}

		for (AppiumAndroidBrowserType prop: AppiumAndroidBrowserType.class.getEnumConstants()){
			allowedAppiumAndroidBrowsers.add(prop.toString());
		}

		for (AppiumIosBrowserType prop: AppiumIosBrowserType.class.getEnumConstants()){
			allowedAppiumIosBrowsers.add(prop.toString());
		}

		/* UI Automator */
		automationContextNames = new HashMap<UiAutomationContext, String>();
		automationContextNames.put(UiAutomationContext.PC_WEB, "PC Web");
		automationContextNames.put(UiAutomationContext.PC_NATIVE, "PC Native");
		automationContextNames.put(UiAutomationContext.MOBILE_WEB, "Mobile Web");
		automationContextNames.put(UiAutomationContext.MOBILE_NATIVE, "Mobile Native");
		automationContextNames.put(UiAutomationContext.SCREEN, "Screen");
		automationContextNames.put(UiAutomationContext.GENERIC, "Generic");

		allowedGenericIdentifiers = new ArrayList<String>();
		for (IdentifyBy prop: IdentifyBy.class.getEnumConstants()){
			allowedGenericIdentifiers.add(prop.toString());
		}

		allAllowedUiElementTypes = new ArrayList<String>();
		for (GuiElementType prop: GuiElementType.class.getEnumConstants()){
			allAllowedUiElementTypes.add(prop.toString());
		}

		allowedScreenIdentifiers = new ArrayList<String>();
		for (ScreenIdentifyBy prop: ScreenIdentifyBy.class.getEnumConstants()){
			allowedScreenIdentifiers.add(prop.toString());
		}

		allowedNativeIdentifiers = new ArrayList<String>();
		for (NativeIdentifyBy prop: NativeIdentifyBy.class.getEnumConstants()){
			allowedNativeIdentifiers.add(prop.toString());
		}

		allowedMobileNativeIdentifiers = new ArrayList<String>();
		for (MobileNativeIdentifyBy prop: MobileNativeIdentifyBy.class.getEnumConstants()){
			allowedMobileNativeIdentifiers.add(prop.toString());
		}

		allowedWebIdentifiers = new ArrayList<String>();
		for (WebIdentifyBy prop: WebIdentifyBy.class.getEnumConstants()){
			allowedWebIdentifiers.add(prop.toString());
		}

		allowedMobileWebIdentifiers = new ArrayList<String>();
		for (MobileWebIdentifyBy prop: MobileWebIdentifyBy.class.getEnumConstants()){
			allowedMobileWebIdentifiers.add(prop.toString());		
		}
	}

	/*
	 * UI Automator
	 */

	public String getAutomationContextName(UiAutomationContext type) {
		return automationContextNames.get(type);
	}

	public List<String> getAllowedGenericIdentifiers(){
		return allowedGenericIdentifiers;
	}

	public List<String> getAllAllowedUiElementTypes(){
		return allAllowedUiElementTypes;
	}

	public List<String> getAllowedScreenIdentifiers(){
		return allowedScreenIdentifiers;
	}

	public List<String> getAllowedNativeIdentifiers() {
		return allowedNativeIdentifiers;
	}

	public List<String> getAllowedMobileNativeIdentifiers() {
		return allowedMobileNativeIdentifiers;
	}

	public List<String> getAllowedWebIdentifiers() {
		return allowedWebIdentifiers;
	}

	public List<String> getAllowedMobileWebIdentifiers() {
		return allowedMobileWebIdentifiers;
	}

	public List<String> getAllowedIdentifiers(UiAutomationContext context) throws Exception{
		switch(context){
		case PC_WEB: return getAllowedWebIdentifiers();
		case PC_NATIVE: return getAllowedNativeIdentifiers();
		case MOBILE_WEB: return getAllowedMobileWebIdentifiers();
		case MOBILE_NATIVE: return getAllowedMobileNativeIdentifiers();
		case SCREEN: return getAllowedScreenIdentifiers();
		case GENERIC: return getAllowedGenericIdentifiers();
		default: throw new Exception("Unknown id context.");
		}
	}
	
	/*
	 * Appium Inquiry Methods
	 */

	private List<String> getAllowedPlatformsForAppium(){
		return allowedAppiumPlatforms;
	}

	private List<String> getAllowedBrowsersForAppium(AppiumMobilePlatformType platform) throws Exception{
		switch (platform){
		case ANDROID: return allowedAppiumAndroidBrowsers;
		case IOS: return allowedAppiumIosBrowsers;
		default: throw new Exception("Unknown platform: " + platform);
		}
	}

	public boolean isAllowedAppiumPlatform(String platformName){
		return getAllowedPlatformsForAppium().contains(platformName.toUpperCase());
	}

	public boolean isAllowedAppiumBrowser(AppiumMobilePlatformType platform, String browserName) throws Exception{
		return getAllowedBrowsersForAppium(platform).contains(browserName.toUpperCase());
	}

	public String getAppiumPlatformString(AppiumMobilePlatformType platform) throws Exception{
		switch(platform){
		case ANDROID: return "Android";
		case IOS: return "iOS";
		default: return platform.toString();
		}
	}

	public String getAppiumBrowserString(String rawName) throws Exception{
		return DataUtils.toTitleCase(rawName);
	}

	public CentralPageDefMap getCentralMap() {
		return this.uicentralMap;
	}

}
