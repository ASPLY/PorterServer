package org.a_sply.porter.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/categorys")
public class CategoryController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> gatAll() {
		Map<String, String> result = new HashMap<String, String>();

		result.putAll(map(new String[] { "1000", "1001", "1002", "1003",
				"1004", "1005", "1006", "1007", "1008", "1009", "1010", "1011",
				"1012", "1013", "1014", "1099" }, new String[] { "외장부품", "그릴",
				"도어핸들", "램프(전조등)", "램프(안개등)", "램프(후미등)", "벌브", "범퍼(앞)",
				"범퍼(뒤)", "본넷", "사이드미러", "스포일러", "엠블럼", "트렁크", "휀더", "기타" }));

		result.putAll(map(new String[] { "2000", "2001", "2002", "2003",
				"2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011",
				"2012", "2013", "2099" }, new String[] { "실내부품", "계기판", "공조기",
				"기어노브", "대쉬보드", "룸미러", "바닥매트", "시트(운전석)", "시트(조수석)", "시트(뒷좌석)",
				"윈도우 스위치", "카오디오", "콘솔", "핸들", "기타" }));

		result.putAll(map(
				new String[] { "3000", "3001", "3002", "3003", "3004", "3005",
						"3006", "3007", "3008", "3009", "3010", "3011", "3012",
						"3013", "3014", "3015", "3016", "3017", "3018", "3019",
						"3020", "3021", "3022", "3023", "3024", "3025", "3026",
						"3099" }, new String[] { "엔진/미션/배기", "DPF", "ECU",
						"가스켓", "경적기", "등속조인트", "라디에이터", "로어암", "매니폴드", "미션",
						"미션쿨러", "배터리", "산소센서", "스타트모터", "에어컨 컴프레셔", "에어필터",
						"엔진", "엔진멤버", "엔진커버", "오일팬", "오일필터", "인젝터", "점화플러그",
						"제너레이터", "촉매", "클러치", "터빈", "기타" }));

		result.putAll(map(new String[] { "4000", "4001", "4002", "4003",
				"4004", "4005", "4006", "4007", "4008", "4099" }, new String[] {
				"휠/타이어", "타이어(12~16inch)", "타이어(17~21inch)", "타이어(22~26inch)",
				"휠(12~16inch)", "휠(17~21inch)", "휠(22~26inch)", "기타 타이어 사이즈",
				"기타 휠 사이즈", "기타" }));

		result.putAll(map(new String[] { "5000", "5001", "5002", "5003",
				"5004", "5005", "5006", "5007", "5008", "5099" }, new String[] {
				"브레이크/서스펜션", "브레이크 드럼", "브레이크 디스크", "브레이크 패드", "브레이크 호스",
				"서스펜션", "스프링", "캘리퍼", "허브", "기타" }));

		result.putAll(map(new String[] { "6000", "6001", "6002", "6003",
				"6004", "6005", "6006", "6007", "6008", "6009", "6010", "6011",
				"6012", "6013", "6099" }, new String[] { "튜닝부품", "ECU Chip",
				"게이지", "다운스프링", "레이싱 용품", "머플러", "바디킷", "버킷시트", "브레이크 시스템",
				"스포일러", "에어필터", "터빈", "팁", "하체보강", "기타" }));

		result.putAll(map(new String[] { "9000", "9001", "9002", "9003",
				"9004", "9005", "9006", "9007", "9099" },
				new String[] { "기타", "네비게이션", "방향제", "보호필름", "블랙박스", "세차용품",
						"카오디오 시스템", "쿠션", "기타" }));

		return result;
	}

	private Map<String, String> map(String[] keys, String[] values) {
		Map<String, String> result = new HashMap<String, String>();

		for (int i = 0; i < keys.length && i < values.length; i++)
			result.put(keys[i], values[i]);

		return result;
	}

}
