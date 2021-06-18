package com.example.supringboot.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Criteria {

	/** 현재 페이지 번호 */
	private int currentPageNo;

	/** 페이지당 출력할 데이터 개수 */
	private int recordsPerPage;

	/** 화면 하단에 출력할 페이지 사이즈 */
	private int pageSize;

	/** 검색 키워드 */
	private String searchKeyword;
	
	private int cat_id;
	/** 검색 유형 */
	private String searchType;

	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 6;
		this.pageSize = 10;
		this.cat_id = -1;
	}

//	public int getStartPage() {
//		return (currentPageNo - 1) * recordsPerPage;
//	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public String makeQueryString(int pageNo) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("currentPageNo", pageNo)
				.queryParam("recordsPerPage", recordsPerPage)
				.queryParam("pageSize", pageSize)
				.queryParam("searchType", searchType)
				.queryParam("searchKeyword", searchKeyword)
				.queryParam("cat_id", cat_id)
				.build()
				.encode();

		return uriComponents.toUriString();
	}
	
	public String makeQueryString(int pageNo, String queryString) {
		System.out.println("[makeQueryString] " + queryString);
		UriComponents uriComponents;
		
//		String key = "";
//		String value = "";
//		
//		if(queryString != null) {
//			String [] splited = queryString.split("&");
//			String [] query = splited[splited.length - 1].split("=");
////			cat_id는 맨뒤 파라미터로 전달 되므로..
//			if(query.length == 2) {
//				key = query[0];
//				value = query[1];
//			}
//		}
//		
//		if(queryString != null && key.equals("cat_id")) {
//			uriComponents = UriComponentsBuilder.newInstance()
//						.queryParam("currentPageNo", pageNo)
//						.queryParam("recordsPerPage", recordsPerPage)
//						.queryParam("pageSize", pageSize)
//						.queryParam("searchType", searchType)
//						.queryParam("searchKeyword", searchKeyword)
//						.queryParam("cat_id", cat_id)
//						.build()
//						.encode();
//			
//		}else {
//			uriComponents = UriComponentsBuilder.newInstance()
//					.queryParam("currentPageNo", pageNo)
//					.queryParam("recordsPerPage", recordsPerPage)
//					.queryParam("pageSize", pageSize)
//					.queryParam("searchType", searchType)
//					.queryParam("searchKeyword", searchKeyword)
//					.build()
//					.encode();
//		}
		
		uriComponents = UriComponentsBuilder.newInstance()
		.queryParam("currentPageNo", pageNo)
		.queryParam("recordsPerPage", recordsPerPage)
		.queryParam("pageSize", pageSize)
		.queryParam("searchType", searchType)
		.queryParam("searchKeyword", searchKeyword)
		.queryParam("cat_id", cat_id)
		.build()
		.encode();

		return uriComponents.toUriString();
	}
}
