# study-jpa
JPA 공부 및 기록



# Hibernate 란?

JPA는 interface로 이루어져 있는데, 그 interface를 구현하는 implements 역할을 해주는 것 중 한 종류가 hibernate 입니다.

외에도 Eclipse Link 같은 구현체가 존재합니다.

![jap image](https://user-images.githubusercontent.com/75984011/208085584-4d126061-6805-4c47-bc65-9f27a2ef7d35.png)


# QueryMethodKeywords

[JPA 공식문서](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject)


# GenerationType

1. IDENTITY

__@GeneratedValue(strategy = GenerationType.IDENTITY)__

id값이 null로 입력되는 경우 트랜잭션이 끝나기 전에 id값을 생성 시킨다. (AUTO_INCREMENT)

IDENTITY 전략은 entityManager.persist() 시점에 즉시 INSERT SQL을 실행하고 DB에서 식별자를 조회한다.

2. SEQUENCE

@GeneratedValue(strategy = GenerationType.SEQUNCE)
```java
@Entity
@SequenceGenerator(
  name = "MEMBER_SEQ_GENERATOR", 
  sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름 
  initialValue = 1,
  allocationSize = 1)
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
                  generator = "MEMBER_SEQ_GENERATOR")
  private Long id; 
}
  ```

SEQUENCE는 Oracle 등의 nextval 로 pk 값을 생성시킨다.

3. TABLE 

@GeneratedValue(strategy = GenerationType.TABLE)

KEY만 관리하는 별도의 테이블을 생성하여, 해당 테이블에서 ID값을 불러오는 방식.


<br>

# @Table

초기 생성되는 테이블의 설정을 돕는 어노테이션.

1. name

entity와 table 이름이 같은게 좋지만, 레거시 시스템을 시스템에 JPA를 추가하거나 DB가 있는경우는 별도의 이름을 설정할 수 있음.

```java
@Table(name = "tb_users")
```

2. indexes()

index 생성을 도와줌

```java
@Table(indexes = { @Index(columnList = "name")})
```
로그 :  Hibernate: create index IDX3g1j96g94xpk3lpxl2qbl985x on users (name)


3. uniqueConstraints

유니크 키 생성을 도와줍니다.

```java
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
```

로그 :  alter table users 
       add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email)

# @Column

1. name

```java
@Column(name = "crtat")
private LocalDateTime createdAt;
```

컬럼의 이름을 변경할때 사용.


2. nullable

```java
@Column(nullable = false)
private LocalDateTime updatedAt;
```

테이블 생성시 아래와같이 not null을 처리하기 위해 사용.
기본값은 true 입니다.

로그 : created_at timestamp not null,

3. insertable, updatable

둘다 기본값은 true입니다.

insertable이 적용되 있는값은 insert시 값이 들어가지 않습니다.

updatable이 적용되 있는값은 update시 값이 들어가지 않습니다.


```java

    // entity
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private LocalDateTime updatedAt;


    // test
    @Test
    void updatableAndInsertableTest() {

        Users users = Users.builder()
                .name("jack")
                .email("jack@gmail.com")
                .build();

        // insert
        Users users2 = usersRepository.save(users);

        System.out.println(users2);

        users2.setName("jackson");

        // id값이 존재하여 update
        usersRepository.save(users2);

        usersRepository.findAll().forEach(System.out::println);

    }
```

로그
```sql
-- insert에는 updatedAt 가 없는 상태.
insert 
    into
        users
        (id, created_at, email, name) 
    values
        (default, ?, ?, ?)

-- Users(id=6, name=jack, email=jack@gmail.com, createdAt=null, updatedAt=null)

-- 이후 update sql에는 created_at가 없다.
update
        users 
    set
        email=?,
        name=?,
        updated_at=? 
    where
        id=?
```

# @Transient

영속성 처리에서 제외되서 DB데이터에 반영되지 않고 해당 객체의 생명주기를 같이하는 값이 된다.

# Entity에서의 Enum처리

아래와 같이 성별에 대한 enum type 객체를 만들어 성별로 사용하게 됬을때 해당값은 enum객체의 순서에 따라 DB에 0,1 로 등록되게 됩니다.

```java
public enum Gender {
    MALE, // 0
    FEMALE // 1
}
```

리팩토링을 진행하던 도중, MALE 아래에 다른 값이 들어가게 된다면 큰 에러가 발생할 수 있습니다.

```java
@Enumerated(value = EnumType.STRING)
private Gender gender;
```

그래서 @Enumerated 어노테이션을 활용해 DB에 데이터 저장을 String 형태로 저장할 수 있습니다.

# Entity Listener란


## 설명
Entity Listener는 엔티티의 변화를 감지하고 데이블의 데이터를 조작하는 일을 한다.

이전에는 Column값이 수정되는 것에 대해서 반복된 코드를 추가해야했으며 개발자가 직접 추가를 하보니 실수가 발생하는 경우가 종종 발생하였다.
 하지만 이러한 것은 EntityListener를 사용하면 쉽게 개선할 수 있다.

- @PrePersist : Persist(insert)메서드가 호출되기 전에 실행되는 메서드

- @PreUpdate : merge메서드가 호출되기 전에 실행되는 메서드
- @PreRemove : Delete메서드가 호출되기 전에 실행되는 메서드
- @PostPersist : Persist(insert)메서드가 호출된 이후에 실행되는 메서드
- @PostUpdate : merge메서드가 호출된 후에 실행되는 메서드
- @PostRemove : Delete메서드가 호출된 후에 실행되는 메서드
- @PostLoad : Select조회가 일어난 직후에 실행되는 메서드

## 활용1

data를 insert할때 생성일,업데이트일 같이 자동적으로 넣어줘야하는 내용을 추가할때 사용할 수 있습니다.

```java
    @PrePersist
    public void PrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
```

## 중복코드 문제점.

하지만 이렇게 하는경우 ENTITY 마다 똑같은 코드를 작성해야하는 문제점이 생깁니다.

이 문제를 __@EntityListeners__ 를 통해 중복코드 없이 개발할 수 있습니다.

```java
@EntityListeners(value = MyEntityListeners.class)
@Entity
public class Book implements Auditable {
    // ... 생략
}
```

```java
public interface Auditable {

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);

}
```

```java
public class MyEntityListeners {

    @PrePersist
    public void prePersist(Object o) {
        dateTimeInsertAndUpdate(o);
    }

    @PreUpdate
    public void preUpdate(Object o) {
        dateTimeInsertAndUpdate(o);
    }

    private void dateTimeInsertAndUpdate(Object o) {
        if (o instanceof Auditable) {
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
```

## 하지만 윗부분은 스프링에서 제공해줌

Application class에 __@EnableJpaAuditing__ 추가.

생성일,업데이트 일이 필요한 Entity에 __@EntityListeners(value = AuditingEntityListener.class)__ 추가

insert date 값은 __@CreatedDate__ , update date 값은 __@LastModifiedDate__  을 사용하여 처리하면 위 코드가 똑같이 작동됩니다.


## 한번 더 반복코드를 줄임.

__@MappedSuperclass__ 를 사용하면 한번 더 반복적인 코드를 줄일 수 있습니다.

```java
@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

와 같은 날짜만 관리하는 ENTITY를 생성 후 

```java
public class Book extends BaseEntity {}
```
와 같이 상속 해당 ENTITY를 상속받아 사용하게 되면 __BaseEntity__ 안에 선언된 createdAt, updatedAt에 대해 사용할 수 있습니다.


# 영속성 컨텍스트(Persistence context)

## 영속화이란?
영속성(persistence)은 데이터를 생성한 프로그램의 실행이 종료되더라도 사라지지 않는 데이터의 특성을 의미한다.

영속성은 파일 또는 데이터베이스에 저장하여 구현 할 수 있다.

## EntityManager
EntityManager는 JPA에서 정의하고 있는 Interface 이다. persist merge remove find 등등 정의되어있다. 그리고 구현체를 빈으로 등록하고 있기 때문에 Autowire를 이용해 사용할수 있다.

```
@SpringBootTest
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    void entityManagerTest() {
        System.out.println(entityManager.createQuery("select u from User u").getResultList()); // entityManager에서 직접 쿼리를 만들서 사용
        // userRepository.findAll(); 이거와 같다.
    }

```

## Entity 캐시

1차 캐시는 영속성 컨텍스트 내부에 있다. 엔티티 매니저로 조회하거나 변경하는 모든 엔티티는 1차 캐시에 저장된다.

트랜잭션을 커밋하거나 플러시를 호출하면 1차 캐시에 있는 엔티티의 변경 내역을 데이터베이스에 동기화한다.


# 영속성 전이

특정 엔티티를 영속 상태로 만들 때, 영관된 엔티티도 함께 영속 상태로 만드는 것이다

CASCADE옵션을 사용하면 부모 엔티티를 저장할 때, 자식 ENITIY도 함께 저장이 가능하다.

cascade 속성에 올 수 있는 값은 아래와 같다.
* PERSIST : EntityManager#persist() 실행시 연관된 엔티티를 함께 영속 객체로 추가한다.
* REMOVE : EntityManager#remove() 실행시 연관된 엔티티를 함께 삭제한다.
* DETACH : EntityManager#detach() 실행시 연관된 엔티티를 함께 분리 상태로 만든다.
* REFRESH : EntityManager#refresh() 실행시 연관된 엔티티를 함께 다시 읽어온다.
* MERGE : EntityManager#merge() 실행시 연관된 엔티티도 함께 관리 상태로 바꾼다.
* ALL : 모든 상태 변화에 대해 연관된 엔티티에 함께 적용한다.


## cascade 와 orphanRemoval 차이
[cascade 와 orphanRemoval 차이 - 우아한 테크코스 블로그](https://tecoble.techcourse.co.kr/post/2021-08-15-jpa-cascadetype-remove-vs-orphanremoval-true/)


## OSIV

* Open Session In View: 하이버네이트
* Open EntityManager In View: JPA (관례상 OSIV라 한다.)
  
<code>spring.jpa.open-in-view: true</code> 기본값

이 기본값을 뿌리면서 애플리케이션 시작 시점에 warn 로그를 남기는 것은 이유가 있다.
OSIV 전략은 트랜잭션 시작처럼 최초 데이터베이스 커넥션 시작 시점부터 API 응답이 끝날 때 까지 영속성 컨텍스트와 데이터베이스 커넥션을 유지한다. 그래서 지금까지 View Template이나 API 컨트롤러에서 지연 로딩이 가능했던 것이다.
지연 로딩은 영속성 컨텍스트가 살아있어야 가능하고, 영속성 컨텍스트는 기본적으로 데이터베이스 커넥션을 유지한다. 이것 자체가 큰 장점이다.

그런데 이 전략은 너무 오랜시간동안 데이터베이스 커넥션 리소스를 사용하기 때문에, 실시간 트래픽이 중요한 애플리케이션에서는 커넥션이 모자랄 수 있다. 이것은 결국 장애로 이어진다.
예를 들어서 컨트롤러에서 외부 API를 호출하면 외부 API 대기 시간 만큼 커넥션 리소스를 반환하지 못하고, 유지해야 한다.

<code>spring.jpa.open-in-view: false</code> OSIV 종료

OSIV를 끄면 트랜잭션을 종료할 때 영속성 컨텍스트를 닫고, 데이터베이스 커넥션도 반환한다. 따라서 커넥션 리소스를 낭비하지 않는다.
OSIV를 끄면 모든 지연로딩을 트랜잭션 안에서 처리해야 한다. 따라서 지금까지 작성한 많은 지연 로딩 코드를 트랜잭션 안으로 넣어야 하는 단점이 있다. 그리고 view template에서 지연로딩이 동작하지 않는다. 결론적으로 트랜잭션이 끝나기 전에 지연 로딩을 강제로 호출해 두어야 한다.

커멘드와 쿼리 분리
실무에서 OSIV를 끈 상태로 복잡성을 관리하는 좋은 방법이 있다. 바로 Command와 Query를 분리하는 것이다.
참고: https://en.wikipedia.org/wiki/Command–query_separation
보통 비즈니스 로직은 특정 엔티티 몇게를 등록하거나 수정하는 것이므로 성능이 크게 문제가 되지 않는다. 그런데 복잡한 화면을 출력하기 위한 쿼리는 화면에 맞추어 성능을 최적화 하는 것이 중요하다. 하지만 그 복잡성에 비해 핵심 비즈니스에 큰 영향을 주는 것은 아니다.
그래서 크고 복잡한 애플리케이션을 개발한다면, 이 둘의 관심사를 명확하게 분리하는 선택은 유지보수 관점에서 충분히 의미 있다.
단순하게 설명해서 다음처럼 분리하는 것이다.

* OrderService
* * OrderService: 핵심 비즈니스 로직
* * OrderQueryService: 화면이나 API에 맞춘 서비스 (주로 읽기 전용 트랜잭션 사용)
보통 서비스 계층에서 트랜잭션을 유지한다. 두 서비스 모두 트랜잭션을 유지하면서 지연 로딩을 사용할 수 있다.
