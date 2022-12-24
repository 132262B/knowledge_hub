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

