package com.learn.test;

import org.junit.Test;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

public class TranslateTest {
	
	private String baseURL = "https://gateway.watsonplatform.net/language-translation/api";
	private String username = "";
	private String password = "";
	
	@Test
	public void testTranslate(){
		
		LanguageTranslation service = new LanguageTranslation();
		service.setUsernameAndPassword(username, password);
		TranslationResult translationResult = service.translate("hello", "en", "es");
		System.out.println(translationResult.getTranslations());
		
	}
	


}
