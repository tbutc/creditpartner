package hello.hellospring.domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter @Setter
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가하도록 지정
    @Column(name = "PID") // 컬럼명 지정
    private Long pid;

    private int sid; //학생
    private int cid; //과목(화작, 수학1)
    private int credit;
    private int semester;

}
