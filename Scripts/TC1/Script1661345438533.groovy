import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
String htmlPath = projectDir.resolve("page.html").toFile().toURI().toURL().toExternalForm()
WebUI.openBrowser("")
WebUI.navigateToUrl(htmlPath)
TestObject tObj = makeTestObject("//div[@id='Matter_search_window_list_view']/div[@class='panel-actions']/span")
WebUI.verifyElementPresent(tObj, 10)
String text = WebUI.getText(tObj)
println "text=" + text

// List<String> parts = ["Showing", "of", "Results"]
List<String> parts = ["Showing", "of", "Fakes"]      //

boolean result = assertTextContains(text, parts)
if (!result) {
	KeywordUtil.markFailed("text '${text}' does not contain one or more parts of ${parts}")
}

WebUI.closeBrowser()

TestObject makeTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}

boolean assertTextContains(String text, List<String> parts) {
	for (part in parts) { part 
		if (!text.contains(part)) {
			println "'${part}' is not contained in the text '${text}'"
			return false
		}
	}
	return true
}
