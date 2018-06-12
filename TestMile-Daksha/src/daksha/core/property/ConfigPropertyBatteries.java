package daksha.core.property;

import java.io.File;
import java.lang.reflect.Method;

import daksha.core.batteries.config.Batteries;
import daksha.core.lib.UserDefinedProperty;
import daksha.core.value.StringListValue;
import daksha.core.value.StringValue;
import daksha.core.value.ValueFactory;
import daksha.tpi.batteries.interfaces.Value;
import daksha.tpi.enums.ValueType;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public class ConfigPropertyBatteries {

	public static String pathToCoreAbsolutePath(String configuredDir) throws Exception {
		String retPath = null;
		if (configuredDir.startsWith("*")){
			if (configuredDir.startsWith("*/") || configuredDir.startsWith("*\\")){
				retPath = FileSystemUtils.getCanonicalPath(Batteries.getBaseDir() + configuredDir.substring(1));
			} else {
				retPath = FileSystemUtils.getCanonicalPath(Batteries.getBaseDir() + File.separator + configuredDir.substring(1));
			}
			
		} else {
			retPath = configuredDir;
		}
//		if (FileSystemBatteries.isAbsolutePath(configuredDir)) {
//			retPath = configuredDir;
//		} else {
//			retPath = FileSystemBatteries.getCanonicalPath(Batteries.getBaseDir() + File.separator + configuredDir);
//		}
		return retPath;
	}
	
	public static String pathToProjectAbsolutePath(String configuredDir) throws Exception {
		String retPath = null;
		if (Batteries.getProjectDir() == null){
			retPath = pathToCoreAbsolutePath(configuredDir);
		} else {
			if (FileSystemUtils.isAbsolutePath(configuredDir)) {
				retPath = configuredDir;
			} else {
				retPath = FileSystemUtils.getCanonicalPath(Batteries.getProjectDir() + File.separator + configuredDir);
			}
		}
		return retPath;
	}

	public static <T extends Enum<T>> String enumToPropPath(T enumObj) {
		return enumObj.toString().toLowerCase().replace("_", ".");
	}

	public static boolean isNotSet(Value configValue) throws Exception {
		if (configValue.valueType() == ValueType.NULL) {
			return true;
		}

		if (configValue.valueType() == ValueType.STRING) {
			String val = configValue.asString();
			if (val.toLowerCase().equals("not_set")) {
				return true;
			} else if (val.toLowerCase().equals("na")) {
				return true;
			}
		}
		return false;
	}

	public static <T extends Enum<T>> ConfigProperty createStringProperty(T code, String propPath, Value configValue,
			String purpose, Object formatterObject, Method formatter, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNullProperty(code, ValueType.STRING, propPath, purpose, visible);
		}
		StringValue sValue = ValueFactory.createStringValueFrom(configValue);
		sValue.process(formatterObject, formatter);

		return createProperty(code, ValueType.STRING, propPath, sValue, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createStringListProperty(T code, String propPath,
			Value configValue, String purpose, Object formatterObject, Method formatter, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNullProperty(code, ValueType.STRING_LIST, propPath, purpose, visible);
		}
		StringListValue sList = ValueFactory.createStringListValueFrom(configValue);
		sList.process(formatterObject, formatter);
		return createProperty(code, ValueType.STRING_LIST, propPath, sList, purpose, visible);
	}

	public static String getSame(String input) {
		return input;
	}

	public static <T extends Enum<T>> ConfigProperty createCoreDirPath(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createStringProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("pathToCoreAbsolutePath", "abc".getClass()), visible);
	}
	
	public static <T extends Enum<T>> ConfigProperty createProjectDirPath(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createStringProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("pathToProjectAbsolutePath", "abc".getClass()), visible);
	}

	public static <T extends Enum<T>> ConfigProperty createStringListProperty(T code, String propPath,
			Value configValue, String purpose, boolean visible) throws Exception {
		return createStringListProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("getSame", "abc".getClass()), visible);
	}

	public static <T extends Enum<T>> ConfigProperty createDirectoriesProperty(T code, String propPath,
			Value configValue, String purpose, boolean visible) throws Exception {
		return createStringListProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("pathToAbsolutePath", "abc".getClass()), visible);
	}

	public <T extends Enum<T>> ConfigProperty createStringProperty(T code, String propPath, String stringConfigValue,
			String purpose, boolean visible) throws Exception {
		return ConfigPropertyBatteries.createProperty(code, ValueType.STRING, propPath,
				new StringValue(stringConfigValue), purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createStringProperty(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createStringProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("getSame", "abc".getClass()), visible);
	}

	public static <T1 extends Enum<T1>, T2 extends Enum<T2>> ConfigProperty createEnumProperty(T1 code, String propPath,
			Class<T2> klass, Value configValue, String purpose, boolean visible) throws Exception {
		Value value = ValueFactory.creatEnumValue(klass, configValue);
		return createProperty(code, ValueType.ENUM, propPath, value, purpose, visible);
	}

	public static <T1 extends Enum<T1>, T2 extends Enum<T2>> ConfigProperty createEnumListProperty(T1 code,
			String propPath, Class<T2> klass, Value configValue, String purpose, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNullProperty(code, ValueType.ENUM_LIST, propPath, purpose, visible);
		}
		Value value = ValueFactory.createEnumListValue(klass, configValue);
		return ConfigPropertyBatteries.createProperty(code, ValueType.ENUM_LIST, propPath, value, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createBooleanProperty(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNullProperty(code, ValueType.BOOLEAN, propPath, purpose, visible);
		}

		Value value = ValueFactory.createBooleanValue(configValue);
		return ConfigPropertyBatteries.createProperty(code, ValueType.BOOLEAN, propPath, value, purpose, visible);
	}

	private static <T extends Enum<T>> ConfigProperty createNumberProperty(T code, ValueType expectedType,
			String propPath, Value configValue, String purpose, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNullProperty(code, expectedType, propPath, purpose, visible);
		}

		Value value = ValueFactory.createNumberValueFrom(configValue);
		return ConfigPropertyBatteries.createProperty(code, expectedType, propPath, value, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createIntProperty(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.INTEGER, propPath, configValue, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createLongProperty(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.LONG, propPath, configValue, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createFloatProperty(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.FLOAT, propPath, configValue, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createDoubleProperty(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.DOUBLE, propPath, configValue, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createNumberProperty(T code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.NUMBER, propPath, configValue, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createProperty(T code, ValueType expectedType, String propPath,
			Value value, String purpose, boolean visible) throws Exception {
		ConfigPropertyBuilder<T> builder = new ConfigPropertyBuilder<T>();
		return builder.codeName(code).path(propPath).text(purpose).value(value).visible(visible).expectedValueType(expectedType).build();
	}

	public static <T extends Enum<T>> ConfigProperty createNullProperty(T code, ValueType expectedType, String propPath,
			String purpose, boolean visible) throws Exception {
		ConfigPropertyBuilder<T> builder = new ConfigPropertyBuilder<T>();
		return builder.codeName(code).path(propPath).text(purpose).value(ValueFactory.createNullValue()).visible(visible)
				.expectedValueType(expectedType).build();
	}

	public static ConfigProperty createSimpleProperty(String propPath, Value value, String purpose) throws Exception {
		ConfigPropertyBuilder<UserDefinedProperty> builder = new ConfigPropertyBuilder<UserDefinedProperty>();
		return builder.codeName(UserDefinedProperty.UNKNOWN).path(propPath).text(purpose).value(value).build();
	}

	public static ConfigProperty createSimpleProperty(String propPath, Value value) throws Exception {
		ConfigPropertyBuilder<UserDefinedProperty> builder = new ConfigPropertyBuilder<UserDefinedProperty>();
		return builder.codeName(UserDefinedProperty.UNKNOWN).path(propPath).text("NOT_SET").value(value).build();
	}

}
