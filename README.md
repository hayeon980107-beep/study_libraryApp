### < Spring Bean >
- 서버가 시작되면, 스프링 서버 내부에 거대한 **컨테이너**를 만들게 된다. 컨테이너 안에는 클래스가 들어간다.
  - 스프링 컨테이너 안에 컨트롤러가 들어감.
    - 이때 다양한 정보도 함께 들어있고, 인스턴스화도 이루어진다.
    - 스프링 컨테이너 안에 들어간 클래스를 **스프링 빈** 이라고 함
- 정리 : 서버가 시작되면?
  1. **스프링 컨테이너**(클래스 저장소)가 시작된다.
  2. 기본적으로 많은 **스프링 빈**들이 등록된다. (스프링 빈 : 컨테이너 안에 있는 클래스들 ex) JdbcTEMPLATE, 데이터 소스 등등...)
  3. 우리가 설정해준 **스프링 빈**이 등록된다. (ex) UserController)
    - 필요한 의존성이 있다면 자동으로 설정해줌

### < @Service, @Repository등의 어노테이션은 왜 필요한가? >
- UserRepository는 왜 JdbcTemplate을 가져오지 못하는지
  - JdbcTemplate을 가져오려면 UserRepository가 스프링 빈이어야 하는데, UserRepository는 스프링 빈이 아니다.
  - UserRepository를 Spring Bean으로 등록하면 된다!
  - 그래서 @Repository 어노테이션을 사용하는 것.

---

### 제어의 역전(IoC, Inversion of Control)
- new를 통해 직접 어떤 구현체가 쓰일지를 결정하는 것이 아니라 **컨테이너가 대신 결정해주는 방식**
- 어노테이션을 사용해 어떤걸 쓸 지 알려줌
### 의존성 주입 (DI, Dependency Injection)
- 컨테이너가 선택해 서비스에 넣어주는 과정
---

### 빈을 등록하는 방법
- @Configuration
  - **클래스**에 붙이는 어노테이션
  - @Bean을 사용할 때 함께 사용해야 한다.
- @Bean
  - **메소드**에 붙이는 어노테이션
  - 메소드에서 반환되는 객체를 스프링 빈에 등록한다
- 외부 라이브러리, 프레임워크에서 만든 클래스를 등록할 때 사용

### 스프링 빈을 주입 받는 방법
- 생성자를 이용해 주입
  - 원래는 생성자 위에 @Autowired를 붙여야 했음 최근엔 알아서 해준다.
  - 가장 권장되는 방법이다.


    public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
    }
- @Setter, @Autowired사용 
 

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }
- 필드에 바로 사용
---
### 어노테이션
- @Primary
  - 우선권을 결정하는 어노테이션
- @Component
  - 주어진 클래스를 '컴포넌트' 라고 간주
  - 스프링 서버가 뜰 때 자동으로 감지
- @Autowired
  - 해당 생성자에 있는 매개변수에 스프링 빈을 집어 넣고 자동으로 연결시켜줌
- @Qualifier
  - @Qualifier("appleService") 
  - 처럼 여러개의 후보군 중 하나를 가져오고 싶을 때 사용
  - 특별한 이름을 붙여, 특정 두개를 연결할 때 사용
  - primary < qualifier(의도적으로 명시한게 더 우세 함)

### JPA ( JAVA Persistence API )
- 자바 진영의 ORM (Object-Relational Mapping)
- 객체와 관계형 DB의 테이블을 짝지어 데이터를 영구적으로 저장할 수 있도록 정해진 Java 진영의 규칙
- HIBERNATE가 구현하고 있음 (Hibernate는 내부에 JDBC를 사용)
- ddl-auto
  - create: 기존 테이블이 있다면 삭제 후 다시 생성
  - create-drop : 스프링이 종료될 때 테이블 모두 제거
  - update : 객체와 테이블이 다른 부분만 변경
  - validate: 객체와 테이블이 동일한지 확인
  - none : 별다른 조치 안함
- function
  - save : 주어지는 객체를 저장하거나 업데이트 시켜준다.
  - findAll : 주어지는 객체가 매핑된 테이블의 모든 데이터를 가져온다.
  - findById : id를 기준으로 특정한 1개의 데이터를 가져온다.
- SQL을 작성하지 않아도 동작하는 이유
  - Spring Data JPA가 처리해줌

### Spring Data JPA
- 복잡한 JPA코드를 스프링과 함께 쉽게 사용할 수 있도록 도와주는 라이브러리
- JPA를 감쌈
  - Spring Data JPA > JPA(ORM) > Hibernate > JDBC