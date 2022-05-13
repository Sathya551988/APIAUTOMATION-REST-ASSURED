package com.runner;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.junit.Cucumber;


public class SkillsApi {

		@RunWith(Cucumber.class)
	@CucumberOptions (
			 plugin= { "pretty", "html:target/cucumber.html" },
			 monochrome = true,//single color
			 tags = "@feature12",//test excution
			 features = {"src/test/resources/SkillsApiFeatures"},
			 glue = "skillsapi.stepdefinitions" )// gule Scenarios and Stepdefinitions

		public class TestRunner extends AbstractTestNGCucumberTests{

			@Override
			@DataProvider(parallel = false)
			public Object[][] scenarios() {
				// TODO Auto-generated method stub
				return super.scenarios();
	}
		}
}
