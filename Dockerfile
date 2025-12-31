FROM maven:3.9.6-eclipse-temurin-17

ARG ALLURE_VERSION=2.27.0

WORKDIR /test_automation

# Copy pom first for caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source
COPY src ./src

# Install Allure
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://github.com/allure-framework/allure2/releases/download/${ALLURE_VERSION}/allure-${ALLURE_VERSION}.zip && \
    unzip allure-${ALLURE_VERSION}.zip && \
    rm allure-${ALLURE_VERSION}.zip && \
    chmod +x allure-${ALLURE_VERSION}/bin/allure

# Install Chrome dependencies
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    ca-certificates \
    fonts-liberation \
    libnss3 \
    libatk-bridge2.0-0 \
    libgtk-3-0 \
    libxss1 \
    libasound2 \
    libgbm1 \
    xdg-utils

# Install Google Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" \
    > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable

ENV ALLURE_HOME=/test_automation/allure-${ALLURE_VERSION}
ENV PATH=$PATH:$ALLURE_HOME/bin

# ✅ Run tests when container starts
CMD ["mvn", "clean", "test"]