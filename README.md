# DT_S_MSA_Transaction

1. Postgresql 설치
   * port:6432, database:mydata, user:mydata, password:mydata
   * 또는 각 모듈들의 application.yaml 파일의 DB 정보 수정

2. JAVA 설치 (JDK 1.8 이상)

3. Docker 설치

4. Maven 설치

5. kafka 설치 및 실행
    * docker-compose-single-kafka.yml 파일 실행

6. CLI 또는 개발툴로 3개의 Spring boot 모듈 실행
   * mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dserver.port=포트번호'  
