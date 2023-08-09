package hello.hellospring.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreditListDTO {

    private List<CreditEditDTO> list;

    public CreditListDTO(List<CreditEditDTO> creditList) {
        this.list = creditList;
    }
}
