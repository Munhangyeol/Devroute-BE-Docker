FROM openjdk:17-jdk-slim

# 필수 패키지 설치
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    unzip \
    xvfb \
    libxi6 \
    libgconf-2-4 \
    libnss3 \
    libxss1 \
    libappindicator1 \
    fonts-liberation \
    libasound2 \
    xdg-utils \
    && rm -rf /var/lib/apt/lists/* \

RUN apt-get update && apt-get install -y mysql-client \
    && rm -rf /var/lib/apt/lists/* \

# Google Chrome 설치
RUN wget -q -O /tmp/google-chrome-stable_current_amd64.deb https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && apt-get update \
    && apt-get install -y /tmp/google-chrome-stable_current_amd64.deb \
    && rm /tmp/google-chrome-stable_current_amd64.deb \
    && rm -rf /var/lib/apt/lists/*

# ChromeDriver 설치
RUN CHROMEDRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) \
    && wget -q -O /tmp/chromedriver.zip "https://chromedriver.storage.googleapis.com/${CHROMEDRIVER_VERSION}/chromedriver_linux64.zip" \
    && unzip /tmp/chromedriver.zip -d /usr/local/bin/ \
    && rm /tmp/chromedriver.zip

# JAR 파일 복사
ARG JAR_FILE=build/libs/Api-Practice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar

# Java 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
