package com.porter.web.serivce;


public class PartSerivceTest {
	
////	2. 부품 등록
////
////	As a 유저
////	I want to 자신의 부품을 등록하기 위해
////	so that 자신의 부품 정보와 쿠키 정보를 알려준다.
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void 등록하다_실패_인자모두NULL() {
//		PartSerivce partSerivce = new PartSerivce();
//		partSerivce.register(null, null);
//	}
//	
//	@Test
//	public void 등록하다_실패_인자모두NULL1() {
//		PartSerivce partSerivce = new PartSerivce();
//		assertRegisterException(partSerivce, "part : null, cookieValue : null", null, null);
//	}
//
//	private void assertRegisterException(PartSerivce partSerivce, String message, Part part, CookieValue cookieValue) {
//		Exception exception = null;
//		try{
//			partSerivce.register(part, cookieValue);
//		}catch(IllegalArgumentException e){
//			exception = e;
//		}
//		assertThat(exception.getMessage(), is(message));
//		assertThat(exception, is(notNullValue()));
//	}
//	
//	@Test
//	public void 등록하다_실패_쿠키값빈값() {
//		PartSerivce partSerivce = new PartSerivce();
//		assertRegisterException(partSerivce, "part : null, cookieValue : ", null, new CookieValue(""));
//	}
//	
//	@Test
//	public void 등록하다_성공(){
//		PartSerivce partSerivce = new PartSerivce();
//		PartRepository mockPartRepository = mock(PartRepository.class);
//		partSerivce.setPartRepository(mockPartRepository);
//		
//		Part part = PartMather.create();
//		when(mockPartRepository.save(part)).thenReturn((int)1);
//		
//		CookieValue cookieValue = new CookieValue("1fe20108c28e7052495e3d0b11ab5958");
//		Long partId = partSerivce.register(part, cookieValue);
//		
//		verify(mockPartRepository).save(part);
//		assertThat(partId, is((long)1));
//		assertThat(part.getUserId(), is(notNullValue()));
//		assertThat(part.getUserCrcEmail(), is(not(0)));
//	}
//	
////	4. 부품 목록보기
////
////	As a 유저
////	I want to 등록된 부품 목록을 보기 위해
////	so that 카테고리 정보(현재 카테고리, 목록 개수, 게시판 id)를 알려준다.	
//
//	@Test(expected = IllegalArgumentException.class)
//	public void 목록보기_실패_인자모두NULL(){
//		PartSerivce partSerivce = new PartSerivce();
//		partSerivce.getList(new Search(null, null, null, null, null));
//	}
//	
//	@Test
//	public void 목록보기_실패_인자모두NULL2(){
//		PartSerivce partSerivce = new PartSerivce();
//		assertGetListException(partSerivce, "largeCategory : null, middleCategory : null, smallCategory : null, beginPartId : null, count : null", new Search(null, null, null, null, null));
//	}
//
//	private void assertGetListException(PartSerivce partSerivce,
//			String message, Search search) {
//		Exception exception = null;
//		try{
//			partSerivce.getList(search);
//		}catch(IllegalArgumentException e){
//			exception = e;
//		}
//		assertThat(exception, notNullValue());
//		assertThat(exception.getMessage(), is(message));
//	}
//	
//	@Test
//	public void 목록보기_실패_요청인자음수(){
//		PartSerivce partSerivce = new PartSerivce();
//		assertGetListException(partSerivce, "largeCategory : null, middleCategory : null, smallCategory : null, beginPartId : null, count : -1", new Search(null, null, null, null, -1));
//	}
//	
//	@Test
//	public void 목록보기_성공(){
//		PartSerivce partSerivce = new PartSerivce();
//		PartRepository mockPartRepository = mock(PartRepository.class);
//		partSerivce.setPartRepository(mockPartRepository);
//		
//		Search mockSearch = mock(Search.class);
//		partSerivce.getList(mockSearch);
//		verify(mockPartRepository).find(mockSearch);
//	}
//	
//
////	5. 부품 상세보기
////
////	As a 유저
////	I want to 등록된 부품 상세 내용을 보기 위해
////	so that 게시물의 id를 알려준다.
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void 상세보기_실패_null(){
//		PartSerivce partSerivce = new PartSerivce();
//		PartRepository mockPartRepository = mock(PartRepository.class);
//		partSerivce.setPartRepository(mockPartRepository);
//		
//		Part part = partSerivce.get(null);
//	}
//	
//	@Test
//	public void 상세보기_실패_null2(){
//		PartSerivce partSerivce = new PartSerivce();
//		PartRepository mockPartRepository = mock(PartRepository.class);
//		partSerivce.setPartRepository(mockPartRepository);
//		
//		Exception exception = null;
//		Part part = null;
//		try{
//			part = partSerivce.get(null);
//		}catch(IllegalArgumentException e){
//			exception = e;
//		}
//		
//		assertThat(exception.getMessage(), is("partId : null"));
//	}
//	
//	@Test
//	public void 상세보기_성공(){
//		PartSerivce partSerivce = new PartSerivce();
//		PartRepository mockPartRepository = mock(PartRepository.class);
//		partSerivce.setPartRepository(mockPartRepository);
//		long partId = 1;
//		when(mockPartRepository.get(partId)).thenReturn(new Part());
//		
//		Part part = partSerivce.get(partId);
//		
//		assertThat(part, notNullValue());
//		verify(mockPartRepository).get(partId);
//	}
//	
////	3. 부품 검색
////
////	As a 유저
////	I want to 자신이 원하는 부품을 찾기 위해
////	so that 원하는 부품명과 카테고리 정보를 알려준다.
//	
//	@Test
//	public void 검색_성공(){
//		PartSerivce partSerivce = new PartSerivce();
//		PartRepository mockPartRepository = mock(PartRepository.class);
//		partSerivce.setPartRepository(mockPartRepository);
//		
//		Search mockSearch = mock(Search.class);
//		partSerivce.search(mockSearch);
//		verify(mockPartRepository).find(mockSearch);
//	}
}
