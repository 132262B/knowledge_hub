package jpabook.jpashop.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateMemberResponse {

    @NotEmpty
    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
