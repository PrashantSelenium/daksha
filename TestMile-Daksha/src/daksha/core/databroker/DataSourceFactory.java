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
package daksha.core.databroker;

import daksha.tpi.ddauto.interfaces.DataSource;

public class DataSourceFactory {
	public static DataSource getDataSource(String srcFilePath, String delimiter) throws Exception {
		String ext = srcFilePath.toLowerCase();
		if (ext.endsWith(".txt") || ext.endsWith(".csv")) {
			return new DsvFileDataSource(srcFilePath, delimiter);
		} else if (ext.endsWith(".xls")) {
			return new ExcelFileDataSource(srcFilePath);
		} else if (ext.endsWith(".ini")) {
			return new IniFileDataSource(srcFilePath);
		} else {
			throw new Exception("This is not a default file format supported as a data source: " + srcFilePath);
		}
	}
}
