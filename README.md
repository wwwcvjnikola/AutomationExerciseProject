# Automation Exercise – UI Automation Project

This project is a UI test automation framework created for practicing and demonstrating automation testing skills using **Java**, **Selenium WebDriver**, and **TestNG**, based on the website:

👉 https://automationexercise.com/

The framework follows the **Page Object Model (POM)** design pattern and includes data-driven testing using **Excel files**.

---

## 🧰 Tech Stack

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **WebDriverManager**
- **Apache POI (ExcelReader)**
- **Page Object Model (POM)**

---

## 📂 Project Structure

The project is structured according to the **Page Object Model (POM)** pattern and is divided into logical layers:

- **BaseTest**
  - `BaseTest` – handles WebDriver setup, common configuration, scrolling utilities, and shared helper methods
  - `ExcelReader` – utility class for reading test data from Excel files using Apache POI

- **Pages**
  - `HomePage` – contains locators and methods related to the home page, navigation bar, categories, and product listings
  - `CartPage` – handles cart-related actions such as adding products, verifying cart content, and deleting products
  - `ContactUsPage` – contains elements and actions for the Contact Us form, including file upload and submission
  - `WriteReviewPage` – manages product review functionality and confirmation message validation

- **Tests**
  - `HomePageTest` – verifies home page elements, navigation, category filtering, and Add to Cart button behavior
  - `CartTest` – tests adding products to the cart, cart validation, and product removal scenarios
  - `ContactUsTest` – validates Contact Us page UI, form submission using Excel-driven data, file upload, and alert handling
  - `WriteReviewTest` – tests writing and submitting a product review and validating the success message

- **Resources**
  - Excel files containing test data used for data-driven testing:
    - product data
    - contact form input values
    - file upload resources

- **Build & Configuration**
  - `pom.xml` – Maven configuration file containing project dependencies and build settings
  - `.gitignore` – excludes generated files and IDE-specific configuration from version control


## 🧪 Test Coverage

### ✅ Home Page Tests
- Page URL and title verification
- "FEATURES ITEMS" section validation
- Add to Cart button visibility and clickability
- Category filtering:
  - Women → Dress
  - Verification that products match selected category

---

### 🛒 Cart Tests
- Add product to cart
- Verify product presence in cart
- Delete product from cart
- Verify empty cart message

---

### 📩 Contact Us Tests
- Page navigation and title validation
- Form submission using **data-driven testing (Excel)**
- File upload functionality
- Alert handling
- Success message verification
- Navigation back to Home page

---

### ✍️ Write Review Tests
- Open product details page
- Submit product review
- Verify confirmation message

---

## 📊 Data-Driven Testing

Test data is stored in Excel files and accessed via a custom **ExcelReader** utility using Apache POI.

- `ProjekatTabela.xlsx` – form inputs and product data
- `UploadFile.xlsx` – file upload testing

---



## ▶️ How to Run Tests

### Prerequisites
- Java JDK 21
- Maven
- Chrome browser
- IntelliJ IDEA 2025.2.3 (Community Edition)

### Run from IDE
- Open project in IntelliJ IDEA
- Run any test class or `testng.xml` (if added)

### Run via Maven
```bash
mvn clean test


