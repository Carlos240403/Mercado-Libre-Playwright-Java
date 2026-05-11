# Mercado Libre Automation

## 📌 Overview

End-to-end test automation framework for Mercado Libre using Playwright and Cucumber.

This project includes UI automation, API validation, HTML reporting, and CI/CD integration using GitHub Actions.

---

## 🚀 How to Run the Tests

### Prerequisites

- Java 17
- Maven installed

---

### Run in headless mode (CI / no browser)

mvn clean test -Dheadless=true

---

### Run in headed mode (visible browser)

mvn test -Dheadless=false

---

## ⚙️ Execution Modes

Headless mode is used for automated pipelines:

mvn clean test -Dheadless=true

Headed mode is recommended for debugging:

mvn test -Dheadless=false

---

## ✅ CI/CD Pipeline

GitHub Actions pipeline (passing run):

👉 PASTE YOUR LINK HERE 👈

---

## ✅ Features

- UI Automation using Playwright
