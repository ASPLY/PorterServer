package com.porter.web.controller;

import static com.porter.web.UnitTestUtil.APPLICATION_JSON_UTF8;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.porter.web.serivce.UserService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader = WebContextLoader.class, locations = {"classpath:/context/servlet-context.xml", "classpath:/context/root-context.xml"})
public class UserControllerTest {
	
//	@Resource
//    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;
    
    @Mock
    private UserService userSerivce;
    private final static String EMAIL = "kd980311@naver.com";
    private final static String PASSWORD = "password";
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    @Test
    public void create_실패_email_null() throws Exception{
    	mockMvc.perform(post("/users").param("password", PASSWORD))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }
    
    @Test
    public void create_실패_email_invalidForm() throws Exception{
    	mockMvc.perform(post("/users").param("email", "kd980311").param("password",PASSWORD))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }
    
    @Test
    public void create_실패_password_null() throws Exception{
    	mockMvc.perform(post("/users").param("email", EMAIL))
    	.andDo(print())
    	.andExpect(status().isBadRequest())
    	.andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }
    
    @Test
    public void create_실패_password_tooShort() throws Exception{
    	mockMvc.perform(post("/users").param("email", EMAIL).param("password","aa"))
    		.andExpect(status().isBadRequest())
    		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
    		.andDo(print());
    }
    
    @Test
    public void create_실패_password_tooLong() throws Exception{
    	mockMvc.perform(post("/users").param("email", EMAIL).param("password","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
    		.andExpect(status().isBadRequest())
    		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
    		.andDo(print());
    }
    
    
    @Test
    public void create_성공() throws Exception{
    	mockMvc.perform(post("/users").param("email", EMAIL).param("password",PASSWORD))
			.andDo(print())
			.andExpect(status().isCreated());
    }    	
    
    @Test
    public void login_성공() throws Exception{
		Mockito.when(userSerivce.login(EMAIL, PASSWORD)).thenReturn(true);
        mockMvc.perform(post("/users/login").param("email", EMAIL).param("password", PASSWORD))
        			.andDo(print())
        			.andExpect(jsonPath("$.apiKey", notNullValue()))
        			.andExpect(status().isOk())
        			.andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }
    
    @Test
    public void login_실패_wrong_email_password() throws Exception{
		Mockito.when(userSerivce.login(EMAIL, PASSWORD)).thenReturn(false);
        mockMvc.perform(post("/users/login").param("email", EMAIL).param("password", PASSWORD))
        			.andDo(print())
        			.andExpect(status().isNotFound());
    }
    
    @Test
    public void login_실패_email_invalidForm() throws Exception{
		Mockito.when(userSerivce.login(EMAIL, PASSWORD)).thenReturn(false);
        mockMvc.perform(post("/users/login").param("email", "kd980311").param("password", PASSWORD))
        			.andExpect(status().isBadRequest())
        			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
        			.andDo(print());
    }
    
    @Test
    public void auth_실패_apiKey_null() throws Exception{
    	mockMvc.perform(post("/users/auth"))
    	.andExpect(status().isBadRequest())
    	.andExpect(content().contentType(APPLICATION_JSON_UTF8))
    	.andDo(print());
    }
    
    @Test
    public void auth_성공() throws Exception{
    	mockMvc.perform(post("/users/auth").param("apiKey","kd980311@naver.com-1394875851451-RZ5x7g/TDOE1K+A1JraEFDKo/CQw7heXXs5tkcjmWEM="))
    	.andExpect(status().isOk())
    	.andDo(print());
    }
}
