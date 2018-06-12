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
package daksha.core.lib;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import daksha.core.batteries.config.Batteries;
import daksha.core.databroker.DataReferenceFactory;
import daksha.core.enums.BatteriesPropertyType;
import daksha.tpi.ddauto.interfaces.DataRecord;
import daksha.tpi.ddauto.interfaces.DataReference;

public enum BatteriesSingleton {
	INSTANCE;

	Map<String, DataReference> dataReferences = new HashMap<String, DataReference>();

	public void loadDataReferences() throws Exception {
		String mapRefDir = Batteries.getCentralProperty(BatteriesPropertyType.DIRECTORY_PROJECT_DATA_REFERENCES).asString();
		File mapFileDir = new File(mapRefDir);
		if (mapFileDir.exists() && mapFileDir.isDirectory()) {
			File[] listOfMapFiles = mapFileDir.listFiles();

			DataReference mRef = null;
			for (int i = 0; i < listOfMapFiles.length; i++) {
				File mf = listOfMapFiles[i];
				if ((mf.isFile()) && (!mf.getName().startsWith("."))) {
					String ext = FilenameUtils.getExtension(mf.getAbsolutePath());
					mRef = DataReferenceFactory.getReference(ext, mapRefDir, mf.getName(), null);
					dataReferences.put(FilenameUtils.getBaseName(mf.getName()).toUpperCase(), mRef);
				}
			}
		}
	}

	public synchronized DataRecord getDataRecord(String refFileName, String key) throws Exception {
		DataReference ref = this.dataReferences.get(refFileName.toUpperCase());
		if (ref == null) {
			throw new Exception(
					String.format("No data reference with %s name has been loaded by Unitee.", refFileName));
		}
		return ref.record(key);
	}

	public DataReference getDataReference(String refName) throws Exception {
		DataReference ref = this.dataReferences.get(refName.toUpperCase());
		if (ref == null) {
			throw new Exception(String.format("No data reference with %s name has been loaded by Unitee.", refName));
		}
		return ref;
	}

}

/*
 * 
	*/
