package com.arton.app.service;

import com.arton.app.domain.dto.PerfDto;
import com.arton.app.domain.dto.ViewSeatInvtDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMethods {

	/** 날짜 처리 */
	public static List stringToDateFormat(List<?> list) {
		StringBuffer sb;
		if (list.get(0).getClass() == PerfDto.class) {
			List<PerfDto> sbList = (List<PerfDto>) list;
			for (PerfDto pd : sbList) {
				sb = new StringBuffer(pd.getPerfStart());
				sb.insert(6, ".").insert(4, ".");
				pd.setPerfStart(sb.toString());

				sb = new StringBuffer(pd.getPerfEnd());
				sb.insert(6, ".").insert(4, ".");
				pd.setPerfEnd(sb.toString());
			}
			return sbList;
		}

		if (list.get(0).getClass() == ViewSeatInvtDto.class) {
			List<ViewSeatInvtDto> sbList = (List<ViewSeatInvtDto>) list;
			for (ViewSeatInvtDto v : sbList) {
				sb = new StringBuffer(v.getShowDate());
				sb.insert(6, ".").insert(4, ".");
				v.setShowDate(sb.toString());
			}

			return sbList;
		}

		return null;
	}
}
