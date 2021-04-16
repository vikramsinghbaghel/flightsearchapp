package com.nagarro.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.nagarro.entities.CSVData;
import com.nagarro.entities.UserRequest;

public class RequestCheck {
	public List<CSVData> checkFlight(UserRequest userRqst, List<CSVData> list) throws ParseException {
		List<CSVData> result = new ArrayList<CSVData>();

		for (CSVData row : list) {
			Date requestedDate = new SimpleDateFormat("dd/MM/yyyy").parse(userRqst.getFlightDate());
			Date validTill = new SimpleDateFormat("dd-MM-yyyy").parse(row.getValidTill());

			if ((userRqst.getArrivalLoc().equals(row.getArrLoc()))
					&& (userRqst.getDepartureLoc().equals(row.getDeptLoc()))
					&& (validTill.compareTo(requestedDate) >= 0) && (row.getSeatAvailable().equalsIgnoreCase("Y"))
					&& ((userRqst.getFlightClass().equalsIgnoreCase(row.getClassAvailable()))
							|| ("EB".contains(row.getClassAvailable())))) {
				result.add(row);
			}
		}

		if (userRqst.getOutputPreference().equalsIgnoreCase("price")) {
			Collections.sort(result, new Comparator<CSVData>() {
				public int compare(CSVData o1, CSVData o2) {
					return o1.getFare() - o2.getFare();
				}
			});
		} else {
			Collections.sort(result, new Comparator<CSVData>() {
				public int compare(CSVData o1, CSVData o2) {
					int rslt = ((o1.getFlightDur() - o2.getFlightDur()) > 0) ? 1
							: ((o1.getFlightDur() - o2.getFlightDur()) < 0) ? -1 : 0;
					return rslt;
				}

			});
		}

		return result;
	}

}
