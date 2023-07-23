package hello.hellospring.dto;

public class MemberJoinDTO {
    private String name;
    private String id;
    private String pwd;
    // 다른 필요한 회원 정보들도 추가 가능

    // 생성자, 게터/세터, toString 등을 포함할 수 있음

    // 기본 생성자
    public MemberJoinDTO() {}

    // 파라미터가 있는 생성자
    public MemberJoinDTO(String name, String id, String pwd) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
    }

    // Getter 및 Setter 메서드
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
