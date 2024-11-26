# base 이미지 지정하기
FROM openjdk:17-jdk-slim

# 이미지 만들때 실제 프로젝트 jar파일을 이미지내 파일로 복사하기
COPY build/libs/*.jar app.jar

EXPOSE 8081

# 이미지가 컨테이너로 실행될 때 수행할 java, -jar, app.jar 명령어 설정하기
ENTRYPOINT ["java", "-jar", "app.jar"]


