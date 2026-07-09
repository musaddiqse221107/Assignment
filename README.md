# Sauce Demo Playwright Tests

This repository contains Playwright test scripts for the Sauce Demo automation assignment.

## Application

- URL: https://www.saucedemo.com
- Username: `standard_user`
- Password: `secret_sauce`

## Project Structure

- `tests/` contains the Playwright test file.
- `pages/` contains Page Object Model classes.
- `package.json` contains the scripts and Playwright dependency.

## How to Run the Tests

Install dependencies:

```bash
npm install
```

Install Playwright browsers:

```bash
npx playwright install
```

Run all tests:

```bash
npm test
```

Run tests in headed mode:

```bash
npm run test:headed
```

Open the HTML test report:

```bash
npm run report
```
