package daksha.tpi.ddauto.lib;

import daksha.core.datarecord.BaseDataRecordContainer;

public class ListDataRecordContainer extends BaseDataRecordContainer {

	@Override
	public void setHeaders(String[] names) throws Exception {
		throw new Exception("setHeaders() method is not supported for List Data Record Container.");
	}
}
