# template_queryDsl

## 스프링부트 + JPA + QueryDsl 템플릿 프로젝트


### 개발환경
- Thymeleaf
- Java8
- SpringBoot
- JPA
- QueryDsl
- Gradle
- Mysql Database
- Lombok.. etc

### 설계
- 토픽 설정 > 관련주 설정 > (관련주 주식 시세 데이터 입력 with Python) > 일별 종가 출력
- 관련주에 대한 주식 시세는 파이썬 노트북으로 구현
  - 키움증권 API 활용
  - 아나콘다 32비트 가상환경 위에서 구현
  - 관련주의 종목코드 리스트 읽기 > 해당 종목에 대한 최근 주식 시세 데이터 입력
- ※ QueryDsl 사용 목표로 테이블 간 Mapping은 사용하지 않음

### 기능
- 토픽
  - 토픽 저장 (auto-increment id)
  - 토픽 출력
  - 토픽 삭제 (update useYn)
- 관련주
  - 관련주 저장(토픽Id, 종목코드)
  - 관련주 출력
  - 관련주 삭제 (update useYn)
- 일별 종가 출력
  - 날짜순 desc
  
### 예정 기능
- 토픽, 관련주, 종가 리스트에 대한 Paging 기능 추가
  - Controller, Repo, RepoImpl 구현 완료
  - Front Page 기능 구현 필요
  
### 추가 예정 기술
- 종가에 대한 데이터는 Update와 Delete가 거의 일어나지 않으므로
  MongoDB를 이용한 Nosql로 이관 예정(Python 기능 구현 완료)
- 파이썬 Django를 이용한 파이썬 서버 구현
  스프링부트: 관련주 Select > Django: 서버 호출 > Django:데이터베이스 입력 > 스프링부트: 종가리스트 출력
- React.js 활용한 프론트 페이지 구현(?)


