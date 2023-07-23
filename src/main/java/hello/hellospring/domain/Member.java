package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가하도록 지정
    @Column(name = "PID") // 컬럼명 지정
    private Long pid;

    @Column(name = "ID") // 컬럼명 지정
    private String id;

    @Column(name = "NAME") // 컬럼명 지정
    private String name;

    @Column(name = "PWD") // 컬럼명 지정
    private String pwd;

}
