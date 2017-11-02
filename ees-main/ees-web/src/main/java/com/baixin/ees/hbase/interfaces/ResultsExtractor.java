package com.baixin.ees.hbase.interfaces;

import org.apache.hadoop.hbase.client.ResultScanner;

public interface ResultsExtractor<T> {

	/**
     * Implementations must implement this method to process the entire {@link ResultScanner}.
	 *  
	 * @param results {@link ResultScanner} to extract data from. Implementations should not close this; it will be closed
	 * automatically by the calling {@link HbaseTemplate}
	 * @return an arbitrary result object, or null if none (the extractor will typically be stateful in the latter case). 
	 * @throws Exception if an Hbase exception is encountered
	 */
	T extractData(ResultScanner results) throws Exception;
}
