# JakartaEE CDI 2.0
![Java CI with Maven](https://github.com/hakdogan/JakartaEE-CDI-2.0-Spec/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3ACDI2.0&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3ACDI2.0)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3ACDI2.0&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3ACDI2.0)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3ACDI2.0&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3ACDI2.0)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3ACDI2.0&metric=code_smells)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3ACDI2.0)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3ACDI2.0&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3ACDI2.0)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3ACDI2.0&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3ACDI2.0)

This repository contains some implementations of the `JakartaEE CDI 2.0 specification`. Details of each application can be found in its own `readme` file.

## How do I run the examples?

```shell script
mvn liberty:dev #or mvn liberty:run
```
## How do I run tests?

Examples of this repository use Arquillian for tests. Therefore, you must run the liberty-maven-plugin goals to create the application server, install the features, and deploy the application to the server if you haven't run the following commands before.

```shell script
mvn clean package
mvn liberty:create liberty:install-feature liberty:deploy
```

After that, you can run the Arquillian tests with the following goal

```shell script
mvn verify
```