package ots.com.test.ydata;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public abstract class AbstractTest {
	
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public
	@interface TestCaseId
	{
	    String value();
	}
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public
	@interface CheckValues
	{
	    //String value();
	}
	
	

	/*@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public
	@interface RecordsData
	{
	    String value();
	}*/
	

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface WebDriverTest {
	   String value();
	   //char separator() default ',';
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface RecordsXmlData {
	   String value();
	   //char separator() default ',';
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface RecordsXlsData {
	   String value();
	   //char separator() default ',';
	}

	
	/*@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface  TestClass{
	   String value();
	   //char separator() default ',';
	}*/

	
	
}


