package com.shikhar.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AITestGenerator {

    public static String generateTestCases(
            String userStory) {

        // Simulated AI generated test code
        // In production this calls Claude/OpenAI API
        return "package com.shikhar.tests;\n\n" +
                "import org.openqa.selenium.WebDriver;\n" +
                "import org.openqa.selenium.chrome" +
                ".ChromeDriver;\n" +
                "import org.openqa.selenium.By;\n" +
                "import org.openqa.selenium.support.ui" +
                ".WebDriverWait;\n" +
                "import org.openqa.selenium.support.ui" +
                ".ExpectedConditions;\n" +
                "import org.testng.Assert;\n" +
                "import org.testng.annotations.*;\n" +
                "import io.github.bonigarcia.wdm" +
                ".WebDriverManager;\n" +
                "import java.time.Duration;\n\n" +
                "// AI Generated Test\n" +
                "// User Story: " + userStory + "\n\n" +
                "public class AIGeneratedLoginTest {\n\n" +
                "    WebDriver driver;\n" +
                "    WebDriverWait wait;\n\n" +
                "    @BeforeMethod\n" +
                "    public void setUp() {\n" +
                "        WebDriverManager" +
                ".chromedriver().setup();\n" +
                "        driver = new ChromeDriver();\n" +
                "        driver.manage().window()" +
                ".maximize();\n" +
                "        wait = new WebDriverWait" +
                "(driver, Duration.ofSeconds(10));\n" +
                "        driver.get(\"https://www" +
                ".saucedemo.com\");\n" +
                "    }\n\n" +
                "    @Test\n" +
                "    public void validLoginTest() {\n" +
                "        wait.until(ExpectedConditions\n" +
                "            .visibilityOfElementLocated" +
                "(By.id(\"user-name\")))\n" +
                "            .sendKeys(\"standard_user\");\n" +
                "        driver.findElement(" +
                "By.id(\"password\"))\n" +
                "            .sendKeys(\"secret_sauce\");\n" +
                "        driver.findElement(" +
                "By.id(\"login-button\")).click();\n" +
                "        Assert.assertTrue(\n" +
                "            driver.getCurrentUrl()" +
                ".contains(\"inventory\"));\n" +
                "    }\n\n" +
                "    @Test\n" +
                "    public void invalidLoginTest() {\n" +
                "        wait.until(ExpectedConditions\n" +
                "            .visibilityOfElementLocated" +
                "(By.id(\"user-name\")))\n" +
                "            .sendKeys(\"standard_user\");\n" +
                "        driver.findElement(" +
                "By.id(\"password\"))\n" +
                "            .sendKeys(\"wrongpass\");\n" +
                "        driver.findElement(" +
                "By.id(\"login-button\")).click();\n" +
                "        Assert.assertTrue(\n" +
                "            driver.findElement(By.css" +
                "Selector(\".error-message-container" +
                " h3\"))\n" +
                "            .isDisplayed());\n" +
                "    }\n\n" +
                "    @Test\n" +
                "    public void emptyLoginTest() {\n" +
                "        driver.findElement(" +
                "By.id(\"login-button\")).click();\n" +
                "        Assert.assertTrue(\n" +
                "            driver.findElement(By.css" +
                "Selector(\".error-message-container" +
                " h3\"))\n" +
                "            .isDisplayed());\n" +
                "    }\n\n" +
                "    @AfterMethod\n" +
                "    public void tearDown() {\n" +
                "        if (driver != null)\n" +
                "            driver.quit();\n" +
                "    }\n" +
                "}\n";
    }

    public static void saveToFile(
            String code,
            String fileName) throws IOException {

        String path = "src/test/java/com/shikhar/" +
                "tests/" + fileName + ".java";

        new java.io.File("src/test/java/com/" +
                "shikhar/tests/").mkdirs();

        try (FileWriter writer =
                     new FileWriter(path)) {
            writer.write(code);
            System.out.println(
                    "✅ Test saved to: " + path);
        }
    }

    public static void main(String[] args)
            throws Exception {

        System.out.println(
                "🤖 AI Test Generator starting...");

        String userStory =
                "As a user I want to login to " +
                        "SauceDemo so that I can see products";

        System.out.println(
                "📝 User Story: " + userStory);
        System.out.println(
                "⚙️  Generating test cases...");

        String generatedCode =
                generateTestCases(userStory);

        System.out.println(
                "\n🤖 Generated Test Code:");
        System.out.println(
                "================================");
        System.out.println(generatedCode);
        System.out.println(
                "================================");

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());
        String fileName =
                "AIGeneratedTest_" + timestamp;

        saveToFile(generatedCode, fileName);

        System.out.println(
                "\n✅ AI Test Generator complete!");
        System.out.println(
                "📁 File: src/test/java/com/" +
                        "shikhar/tests/" + fileName + ".java");
        System.out.println(
                "\n💡 Note: In production this calls" +
                        " Claude/OpenAI API to generate tests" +
                        " from any user story automatically.");
    }
}