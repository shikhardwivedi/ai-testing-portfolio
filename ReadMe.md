# 🤖 AI-Powered Test Automation Framework

![Java](https://img.shields.io/badge/Java-11-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.18-green)
![TestNG](https://img.shields.io/badge/TestNG-7.9-blue)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-brightgreen)
![RestAssured](https://img.shields.io/badge/RestAssured-API-yellow)
![CI/CD](https://img.shields.io/badge/CI/CD-GitHub_Actions-black)

> Built by **Shikhar Dubey** | SDET | 4+ Years Experience

---

## 📋 About

A production-grade AI-powered test automation
framework built from scratch demonstrating
end-to-end SDET capabilities including UI, API,
BDD, Data Driven, Visual and AI-assisted testing.

---

## 🛠️ Tech Stack

| Category | Tools |
|----------|-------|
| UI Automation | Selenium WebDriver 4.x |
| Language | Java 11 |
| Test Framework | TestNG |
| BDD | Cucumber + Gherkin |
| API Testing | RestAssured |
| Design Pattern | Page Factory POM |
| Reports | ExtentReports |
| CI/CD | GitHub Actions |
| AI Integration | Claude API |
| Build Tool | Maven |

---

## 📁 Project Structure
src/test/java/com/shikhar/
├── pages/
│   ├── LoginPage.java
│   ├── ProductsPage.java
│   ├── CartPage.java
│   └── CheckoutPage.java
├── tests/
│   ├── LoginTest.java
│   ├── E2ETest.java
│   ├── DataDrivenTest.java
│   ├── ApiTest.java
│   └── AITestGenerator.java
├── stepdefs/
│   └── LoginSteps.java
└── utils/
├── BaseTest.java
├── DataProviders.java
├── ApiUtils.java
├── AITestGenerator.java
└── ExtentReportListener.java

---

## ✅ Test Coverage

### UI Tests — SauceDemo
| Test | Description |
|------|-------------|
| validLoginTest | Valid credentials login |
| invalidLoginTest | Invalid password error |
| emptyFieldsTest | Empty fields validation |
| addToCartTest | Add product to cart |
| removeFromCartTest | Remove from cart |
| productSortingTest | Sort products Z-A |
| completeCheckoutTest | Full E2E checkout |

### Data Driven Tests
| User | Expected |
|------|----------|
| standard_user | Login pass |
| locked_out_user | Login blocked |
| problem_user | Login pass |
| invalid_user | Login blocked |
| empty fields | Login blocked |

### API Tests — Restful Booker
| Test | Method | Endpoint |
|------|--------|----------|
| getAllBookingsTest | GET | /booking |
| createBookingTest | POST | /booking |
| getBookingByIdTest | GET | /booking/{id} |
| updateBookingTest | PUT | /booking/{id} |
| partialUpdateTest | PATCH | /booking/{id} |
| deleteBookingTest | DELETE | /booking/{id} |
| verifyDeletedTest | GET | /booking/{id} |

---

## 🤖 AI Test Generator

Automatically generates Selenium Java test cases
from user stories using Claude/OpenAI API.

**How it works:**
1. Input a user story
2. AI generates complete TestNG test class
3. File saved automatically to tests package
4. Run immediately with zero manual effort

```java
String userStory = "As a user I want to login...";
String generatedCode = 
    AITestGenerator.generateTestCases(userStory);
AITestGenerator.saveToFile(generatedCode, "MyTest");
```

---

## ⚡ Parallel Execution

Tests run in parallel with 3 threads using
TestNG parallel configuration — reducing
total execution time by ~60%.

```xml
<suite parallel="tests" thread-count="3">
```

---

## 📊 Reports

After test run, open these reports:

target/
├── ExtentReport.html     ← Main dashboard
├── cucumber-report.html  ← BDD report
└── screenshots/          ← Auto on failure

---

## 🚀 How to Run

**Run all tests:**
Right click testng.xml → Run

**Run specific test:**
Right click any test class → Run

**Run AI Generator:**
Right click AITestGenerator → Run AITestGenerator.main()

---

## 🔐 Auth Token Chaining

API tests automatically extract auth token
from login endpoint and reuse across all
authenticated requests — zero manual token
management.

---

## 👨‍💻 Author

**Shikhar Dubey**
SDET | 4+ Years | Noida, India

- 📧 dubeyshikhar1998@gmail.com
- 💼 linkedin.com/in/shikhar-dubey372423178
- 🐙 github.com/shikhardwivedi

---

## 🏆 Key Highlights

- ✅ 19+ automated test cases
- ✅ Page Factory POM design pattern
- ✅ Full CRUD API test coverage
- ✅ Auth token chaining
- ✅ Data driven with 5 user combinations
- ✅ Parallel execution — 3 threads
- ✅ Auto screenshot on failure
- ✅ AI-powered test generation
- ✅ BDD with Cucumber + Gherkin
- ✅ CI/CD with GitHub Actions

