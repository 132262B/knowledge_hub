package com.example.domain;

import com.example.domain.listeners.UsersEntityListeners;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EntityListeners(value = UsersEntityListeners.class)
@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private List<UsersHistory> userHistories = new ArrayList<>();

    @Transient
    private String testData;


}
