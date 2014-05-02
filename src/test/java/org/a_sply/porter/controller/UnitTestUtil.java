package org.a_sply.porter.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.a_sply.porter.domain.Article;
import org.a_sply.porter.domain.Description;
import org.a_sply.porter.domain.Image;
import org.a_sply.porter.domain.MessageList;
import org.a_sply.porter.domain.Part;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article.ArticleDTO;
import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;
import org.a_sply.porter.dto.article_list.ArticleListDTO;
import org.a_sply.porter.dto.article_list.ArticleListsDTO;
import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;
import org.a_sply.porter.dto.article_list.SearchArticleListDTO;
import org.a_sply.porter.dto.email.CheckEmailDTO;
import org.a_sply.porter.dto.message.MessageListDTO;
import org.a_sply.porter.dto.message.MessageListsDTO;
import org.a_sply.porter.dto.message.SendMessageDTO;
import org.a_sply.porter.dto.part.CreatedPartDTO;
import org.a_sply.porter.dto.part.PartDTO;
import org.a_sply.porter.dto.user.CheckNameDTO;
import org.a_sply.porter.dto.user.CreateUserDTO;
import org.a_sply.porter.dto.user.LoginUserDTO;
import org.a_sply.porter.dto.user.UserDTO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Petri Kainulainen
 */
public class UnitTestUtil {

	private static final String CHARACTER = "C";

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	public static final String IMAGES_A_TEST_JPG = "src\\test\\resources\\images\\test_A.jpg";
	public static final String IMAGES_B_TEST_JPG = "src\\test\\resources\\images\\test_B.jpg";

	public static final String TEST_STORAGE = "src\\test\\resources\\test_storage";
	public static final String TEST_STORAGE_ORIGINAL_DIR = TEST_STORAGE + "\\original";
	public static final String TEST_STORAGE_ARTICLE_THUMBNAIL_DIR = TEST_STORAGE + "\\article_thumbnail";
	public static final String TEST_STORAGE_ARTICLE_LIST_THUMBNAIL_DIR = TEST_STORAGE + "\\article_list_thumbnail";

	public static byte[] convertObjectToJsonBytes(Object object)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	public static String createStringWithLength(int length) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index <= length; index++) {
			builder.append(CHARACTER);
		}
		return builder.toString();
	}

	public static CreateArticleDTO createArticleDTO() {
		CreateArticleDTO createArticleDTO = new CreateArticleDTO();
		createArticleDTO.setName("나사");
		createArticleDTO.setMiddleCategory("1001");
		createArticleDTO.setLargeCategory("1000");
		String keywords[] = new String[] { "키워드1", "키워드2" };
		createArticleDTO.setKeywords(keywords);
		createArticleDTO.setPrice("1000");
		createArticleDTO.setMaker("현대");
		createArticleDTO.setState("좋음");
		createArticleDTO.setQuantity("100");
		createArticleDTO.setDescription("상세설명입니다.");
		MultipartFile imageFiles[] = new MultipartFile[] { multipartFile(IMAGES_A_TEST_JPG), multipartFile(IMAGES_B_TEST_JPG) };
		createArticleDTO.setImageFiles(imageFiles);
		createArticleDTO.setRegion("1");
		return createArticleDTO;
	}

	public static CreatedArticleDTO createdArticleDTO() {
		CreatedArticleDTO createdArticleDTO = new CreatedArticleDTO();
		createdArticleDTO.setId(0);
		createdArticleDTO.setPart(new CreatedPartDTO());
		createdArticleDTO.setUser(new UserDTO());
		createdArticleDTO.setIsSold(false);
		return createdArticleDTO;
	}

	public static ArticleDTO articleDTO() {
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setPart(new PartDTO());
		articleDTO.setUser(new UserDTO());
		articleDTO.setIsSold(false);
		return articleDTO;
	}

	public static Article article() {
		Article article = new Article();
		article.setIsSold(false);
		article.setUser(userA());
		article.setPart(partA());
		return article;
	}

	public static Article article(User user, Part part) {
		Article article = new Article();
		article.setIsSold(false);
		article.setUser(user);
		article.setPart(part);
		return article;
	}

	public static Part partA() {
		Part part = new Part();
		part.setDescription(new Description("상세설명어어어어어어ㅓ어어어ㅓ엉어어어ㅓ어엉"));
		List<Image> images = new ArrayList<Image>();
		images.add(new Image("aa", "bb", "cc"));
		images.add(new Image("aa", "bb", "cc"));
		part.setImages(images);
		part.setKeywords(Arrays.asList("keywords 1", "keywords 2"));
		part.setLargeCategory("1000");
		part.setMaker("현태");
		part.setMiddleCategory("1001");
		part.setName("존나 좋은");
		part.setPrice("1000");
		part.setQuantity("10000");
		part.setState("좋음");
		part.setRegion("1");
		return part;
	}

	public static Part partB() {
		Part part = new Part();
		part.setDescription(new Description("상세설명어어어어어어ㅓ어어어ㅓ엉어어어ㅓ어엉"));
		List<Image> images = new ArrayList<Image>();
		images.add(new Image("aa", "bb", "cc"));
		images.add(new Image("aa", "bb", "cc"));
		part.setImages(images);
		part.setKeywords(Arrays.asList("key words3", "keyw ords4"));
		part.setLargeCategory("1000");
		part.setMaker("현태");
		part.setMiddleCategory("1001");
		part.setName("존너 좋은");
		part.setPrice("1000");
		part.setQuantity("10000");
		part.setState("좋음");
		part.setRegion("1");
		return part;
	}

	public static SearchArticleListDTO searchArticleListDTO() {
		SearchArticleListDTO searchPartListDTO = new SearchArticleListDTO();
		searchPartListDTO.setCount(2);
		searchPartListDTO.setKeyword("me");
		searchPartListDTO.setMiddleCategory("1002");
		searchPartListDTO.setLargeCategory("1001");
		return searchPartListDTO;
	}

	public static SearchArticleListDTO searchArticleListDTO(String keyword) {
		SearchArticleListDTO searchArticleListDTO = searchArticleListDTO();
		searchArticleListDTO.setKeyword(keyword);
		return searchArticleListDTO;
	}

	public static SearchArticleListDTO searchArticleListDTO(CreatedPartDTO part) {
		SearchArticleListDTO searchPartListDTO = new SearchArticleListDTO();
		searchPartListDTO.setCount(2);
		searchPartListDTO.setKeyword(part.getName());
		searchPartListDTO.setMiddleCategory(part.getMiddleCategory());
		searchPartListDTO.setLargeCategory(part.getLargeCategory());
		return searchPartListDTO;
	}

	public static ArticleListsDTO partListsDTO(int count) {
		ArticleListsDTO partListsDTO = new ArticleListsDTO();
		List<ArticleListDTO> articleLists = new ArrayList<ArticleListDTO>();
		for (int i = 0; i < count; i++)
			articleLists.add(new ArticleListDTO());
		partListsDTO.setArticleLists(articleLists);
		return partListsDTO;
	}

	public static MessageListsDTO messageListsDTO(int count) {
		MessageListsDTO messageListsDTO = new MessageListsDTO();
		List<MessageListDTO> messageLists = new ArrayList<MessageListDTO>();
		for (int i = 0; i < count; i++) {
			messageLists.add(new MessageListDTO());
		}
		messageListsDTO.setMessageLists(messageLists);
		return messageListsDTO;
	}

	public static CreateUserDTO createUserADTO() {
		CreateUserDTO createUserDTO = new CreateUserDTO();
		createUserDTO.setEmail("kd980311@naver.com");
		createUserDTO.setPassword("12345678");
		createUserDTO.setTelephone("010-5215-2341");
		createUserDTO.setName("LEE");
		return createUserDTO;
	}

	public static CreateUserDTO createUserDTO(User user) {
		CreateUserDTO createUserDTO = new CreateUserDTO();
		createUserDTO.setEmail(user.getEmail());
		createUserDTO.setPassword(user.getPassword());
		createUserDTO.setTelephone(user.getTelephone());
		createUserDTO.setName(user.getName());
		return createUserDTO;
	}

	public static CreateUserDTO createUserBDTO() {
		CreateUserDTO createUserDTO = new CreateUserDTO();
		createUserDTO.setEmail("kd980311@nate.com");
		createUserDTO.setPassword("1234567899");
		createUserDTO.setTelephone("010-5215-1111");
		return createUserDTO;
	}

	public static CheckEmailDTO checkEmailDTO() {
		CheckEmailDTO checkEmailDTO = new CheckEmailDTO();
		checkEmailDTO.setEmail("kd980311@naver.com");
		return checkEmailDTO;
	}

	public static LoginUserDTO loginUserDTO() {
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		loginUserDTO.setEmail("kd980311@naver.com");
		loginUserDTO.setPassword("12345678");
		return loginUserDTO;
	}

	public static LoginUserDTO loginUserDTO(User userA) {
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		loginUserDTO.setEmail(userA.getEmail());
		loginUserDTO.setPassword(userA.getPassword());
		return loginUserDTO;
	}

	public static User userA() {
		return new User("chan", "kd980311@naver.com", "010-5215-2222",
				"12345678");
	}

	public static User userB() {
		return new User("Lee", "aassee123@nate.com", "011-1333-2333",
				"12345678");
	}

	public static User userC() {
		return new User("Hee", "kd6480@daum.com", "011-1333-1111", "12345678");
	}

	public static User userD() {
		return new User("She", "ggdd@nate.com", "02-1111-3333", "12345678");
	}

	public static User userE() {
		return new User("Call", "ggdd12@nate.com", "02-4444-2222", "12345678");
	}

	public static User userF() {
		return new User("Tom", "qwe123e@nate.com", "02-4444-2222", "12345678");
	}

	public static MultipartFile multipartFile(String path) {
		File file = new File(path);
		try {
			DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory()
					.createItem("fileData", "image/jpeg", true, file.getName());
			InputStream input = new FileInputStream(file);
			OutputStream os = fileItem.getOutputStream();
			int ret = input.read();
			while (ret != -1) {
				os.write(ret);
				ret = input.read();
			}
			os.flush();
			return new CommonsMultipartFile(fileItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static RequestArticleListsDTO getArticleListsDTO() {
		RequestArticleListsDTO getArticleListsDTO = new RequestArticleListsDTO();
		getArticleListsDTO.setCount(3);
		getArticleListsDTO.setLargeCategory(1000);
		getArticleListsDTO.setMiddleCategory(1001);
		return getArticleListsDTO;
	}

	public static RequestArticleListsDTO getArticleListsDTO(
			String middleCategory, String largeCategory, int category) {
		RequestArticleListsDTO getArticleListsDTO = new RequestArticleListsDTO();
		getArticleListsDTO.setCount(category);
		getArticleListsDTO.setLargeCategory(Integer.parseInt(largeCategory));
		getArticleListsDTO.setMiddleCategory(Integer.parseInt(middleCategory));
		return getArticleListsDTO;
	}

	public static SendMessageDTO sendMessageDTO() {
		SendMessageDTO sendMessageDTO = new SendMessageDTO();
		sendMessageDTO.setContent("문의 드립니다. 상태는 어떤가요 ?");
		sendMessageDTO.setTo("kd980311");
		return sendMessageDTO;
	}

	public static SendMessageDTO sendMessageDTO(String to) {
		SendMessageDTO sendMessageDTO = new SendMessageDTO();
		sendMessageDTO.setContent("문의 드립니다. 상태는 어떤가요 ?");
		sendMessageDTO.setTo(to);
		return sendMessageDTO;
	}

	public static CheckNameDTO CheckNameDTO() {
		CheckNameDTO checkNameDTO = new CheckNameDTO();
		checkNameDTO.setName("LEE");
		return checkNameDTO;
	}

	public static MessageList messageList() {
		MessageList messageList = new MessageList();
		messageList.setFrom("Lee");
		messageList.setMessageId(1);
		messageList.setPreview("Hello...");
		messageList.setSending(new Date());
		return messageList;
	}

	public static final String buildBasicAuthHeaderValue(String username, String password) throws Exception {
        String authHeaderFormat = "Basic ";
        String encodingRawData = String.format("%s:%s", username, password);
        String encodingData = authHeaderFormat + new String(Base64.encodeBase64(encodingRawData.getBytes("utf-8")));
        return encodingData;
    }

	public static String buildBasicAuthHeaderValue(User user) throws Exception {
		return buildBasicAuthHeaderValue(user.getEmail(), user.getPassword());
	}

}
