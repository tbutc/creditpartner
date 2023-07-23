package hello.hellospring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private Long pid;
    private String id;
    private String name;
    private String pwd;
    private String pwdConfirm;

    public MemberDTO(Long pid, String id, String name, String pwd, String pwdConfirm) {
        this.pid = pid;
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.pwdConfirm = pwdConfirm;
    }
}