#!/bin/bash

# 에러 발생 시 스크립트 중단
set -e 

# Maven 빌드
echo "Running Maven build..."
mvn clean package || { echo "Maven build failed"; exit 1; }

# WAR 파일 복사
echo "Copying WAR file to Tomcat..."
sudo cp target/phoneappspring.war /opt/tomcat/webapps/ || { echo "Failed to copy WAR file"; exit 1; }

# 완료 메시지
echo "Deployment completed successfully."