package com.arkea.asyncapi.v2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.arkea.asyncapi.v2.models.AsyncAPI;
import com.arkea.asyncapi.v2.parser.AsyncAPIV2Parser;
import com.arkea.asyncapi.v2.parser.models.AsyncParseResult;
import com.arkea.asyncapi.v2.parser.models.ParseOptions;

public class AsycnApiV2ParserTest {

	@Test
	public void testMinimalAsyncAPIV2Parser() {
		AsyncAPIV2Parser asyncAPIV2Parser = new AsyncAPIV2Parser();
		ParseOptions options = new ParseOptions();

		InputStream myStream = null;
		String myString = null;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			myStream = classloader.getResourceAsStream("asyncapi.yaml");

			myString = IOUtils.toString(myStream, Charset.defaultCharset()).trim();
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}finally {
			if(myStream != null) {
				IOUtils.closeQuietly(myStream);
			}
		}

		options.setResolve(true);
		AsyncParseResult parseResult = asyncAPIV2Parser.readContents(myString);
		AsyncAPI asyncAPI = parseResult.getAsyncAPI();
		assertNotNull(asyncAPI);
		assertTrue(parseResult.getMessages().size() == 0);
	}

	@Test
	public void testM2AsyncAPIV2Parser() {
		AsyncAPIV2Parser asyncAPIV2Parser = new AsyncAPIV2Parser();
		ParseOptions options = new ParseOptions();

		InputStream myStream = null;
		String myString = null;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			myStream = classloader.getResourceAsStream("application-headers.yml");
			myString = IOUtils.toString(myStream, Charset.defaultCharset()).trim();
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}finally {
			if(myStream != null) {
				IOUtils.closeQuietly(myStream);
			}
		}
		
		IOUtils.closeQuietly(myStream);
		

		options.setResolve(true);
		AsyncParseResult parseResult = asyncAPIV2Parser.readContents(myString);
		AsyncAPI asyncAPI = parseResult.getAsyncAPI();
		assertNotNull(asyncAPI);
		assertTrue(parseResult.getMessages().size() == 0);
		assertTrue(asyncAPI.getChannels().get("smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measured").getExtensions().size() > 0);
	}

	@Test
	public void testJsonAsyncAPIV2Parser() {
		AsyncAPIV2Parser asyncAPIV2Parser = new AsyncAPIV2Parser();
		ParseOptions options = new ParseOptions();

		InputStream myStream = null;
		String myString = null;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			myStream = classloader.getResourceAsStream("TestJson.json");
			myString = IOUtils.toString(myStream, Charset.defaultCharset()).trim();
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			if(myStream != null) {
				IOUtils.closeQuietly(myStream);
			}
		}

		options.setResolve(true);
		AsyncParseResult parseResult = asyncAPIV2Parser.readContents(myString);
		AsyncAPI asyncAPI = parseResult.getAsyncAPI();
		assertNotNull(asyncAPI);
		assertTrue(parseResult.getMessages().size() == 0);
	}

	@Test
	public void testCloudEventsAsyncAPIV2Parser() {
		AsyncAPIV2Parser asyncAPIV2Parser = new AsyncAPIV2Parser();
		ParseOptions options = new ParseOptions();

		InputStream myStream = null;
		String myString = null;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			myStream = classloader.getResourceAsStream("cloudEventsTest.yml");
			myString = IOUtils.toString(myStream, Charset.defaultCharset()).trim();
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}finally {
			if(myStream != null) {
				IOUtils.closeQuietly(myStream);
			}
		}

		options.setResolve(true);
		AsyncParseResult parseResult = asyncAPIV2Parser.readContents(myString);
		AsyncAPI asyncAPI = parseResult.getAsyncAPI();
		assertNotNull(asyncAPI);
		assertTrue(parseResult.getMessages().size() == 0);
		assertTrue(asyncAPI.getChannels().get("user/signedup").getExtensions().size() > 0);
		assertTrue(asyncAPI.getChannels().get("user/signedup").getExtensions().get("x-arkea") != null);

	}
	@Test
	public void testOneOfAsyncAPIV2Parser() {
		AsyncAPIV2Parser asyncAPIV2Parser = new AsyncAPIV2Parser();
		ParseOptions options = new ParseOptions();

		InputStream myStream = null;
		String myString = null;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			myStream = classloader.getResourceAsStream("oneof.yml");
			myString = IOUtils.toString(myStream, Charset.defaultCharset()).trim();
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}finally {
			if(myStream != null) {
				IOUtils.closeQuietly(myStream);
			}
		}

		options.setResolve(true);
		AsyncParseResult parseResult = asyncAPIV2Parser.readContents(myString);
		AsyncAPI asyncAPI = parseResult.getAsyncAPI();
		assertNotNull(asyncAPI);
		assertTrue(parseResult.getMessages().size() == 0);
	}
}
