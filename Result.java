/**
 * 
 */
package com.oracle.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ravindra_Ghetia
 *
 */
public class Result implements IResult {

	List<String> strList = new ArrayList<String>();
	String[] input;
	Map<String, String> UniqueCustContractMap = new HashMap<String, String>();
	Map<String, String> UniqueCustGeoZoneMap = new HashMap<String, String>();
	Map<String, String> averagedurationGeoZoneMap = new HashMap<String, String>();

	/*
	 * The data is organized into columns delimited by a comma (,) in the following
	 * order: customerId,contractId,geozone,teamcode,projectcode,buildduration
	 * 
	 * Set Input Values
	 */
	@Override
	public void setInut(final String inputString) {
		strList.clear();
		if (inputString != null) {
			input = inputString.split("\n");
			for (String inputValue : input) {
				strList.add(inputValue);
			}
		}

	}

	/*
	 * This method clear and process input values.
	 */
	@Override
	public void processInputs() {
		UniqueCustContractMap.clear();
		UniqueCustGeoZoneMap.clear();
		averagedurationGeoZoneMap.clear();

		for (final String str : strList) {

			String strLineArray[] = str.split(",");
			String customerId = strLineArray[0];
			String contractId = strLineArray[1];
			String geoZone = strLineArray[2];
			String buildDuration = strLineArray[5].trim();
			StringBuffer customerIdList = new StringBuffer();

			if (UniqueCustContractMap.containsKey(contractId)) {
				String customerIdFromMap = UniqueCustContractMap.get(contractId);
				if (customerIdFromMap != null && !customerIdFromMap.contains(customerId)) {
					customerIdList.append(customerIdFromMap);
					customerIdList.append("," + customerId);
					UniqueCustContractMap.put(contractId, customerIdList.toString());
				}
			} else {
				UniqueCustContractMap.put(contractId, customerId);
			}

			customerIdList.delete(0, customerIdList.length());

			if (UniqueCustGeoZoneMap.containsKey(geoZone)) {
				String customerIdFromMap = UniqueCustGeoZoneMap.get(geoZone);
				if (customerIdFromMap != null) {
					customerIdList.append(customerIdFromMap);
					customerIdList.append("," + customerId);
					UniqueCustGeoZoneMap.put(geoZone, customerIdList.toString());
				}
			} else {
				UniqueCustGeoZoneMap.put(geoZone, customerId);
			}

			StringBuffer buildDurationStrBuffer = new StringBuffer();

			if (averagedurationGeoZoneMap.containsKey(geoZone)) {
				buildDurationStrBuffer.append(averagedurationGeoZoneMap.get(geoZone));
				buildDurationStrBuffer.append("," + buildDuration.substring(0, buildDuration.length() - 1));
				averagedurationGeoZoneMap.put(geoZone, buildDurationStrBuffer.toString());
			} else {
				averagedurationGeoZoneMap.put(geoZone, buildDuration.substring(0, buildDuration.length() - 1));
			}

		}
	}

	/*
	 * This method generates result
	 */
	@Override
	public void generateResult() {
		// The number of unique customerId for each contractId
		for (String contractId : UniqueCustContractMap.keySet()) {
			String customerList = UniqueCustContractMap.get(contractId);
			String customerArray[] = customerList.split(",");
			System.out.println(
					"Number of Unique Customer IDs are : " + customerArray.length + " For Contract ID: " + contractId);
		}

		System.out.println("\n");

		// The number of unique customerId for each geoZone
		for (String geoZone : UniqueCustGeoZoneMap.keySet()) {
			String customerList = UniqueCustGeoZoneMap.get(geoZone);
			String customerArray[] = customerList.split(",");
			System.out.println(
					"Number of Unique Customer IDs are : " + customerArray.length + " For Geo Zone: " + geoZone);
		}

		System.out.println("\n");

		// The list of unique customerId for each geoZone
		for (String geoZone : UniqueCustGeoZoneMap.keySet()) {
			String customerList = UniqueCustGeoZoneMap.get(geoZone);
			System.out.println("List of unique Customer IDs are : " + customerList + " For Geo Zone: " + geoZone);
		}
		
		System.out.println("\n");
		
		// The average buildduration for each geozone
		for (String geoZone : averagedurationGeoZoneMap.keySet()) {
			int totalDuration = 0;
			String buildDurationList = averagedurationGeoZoneMap.get(geoZone);
			String buildDurationArray[] = buildDurationList.split(",");
			for (String buildDuration : buildDurationArray) {
				totalDuration = totalDuration + Integer.parseInt(buildDuration);
			}
			int averageDuration = totalDuration / buildDurationArray.length;
			System.out.println("Average Build Duration is : " + averageDuration + " For Geo Zone: " + geoZone);
		}
	}

}
