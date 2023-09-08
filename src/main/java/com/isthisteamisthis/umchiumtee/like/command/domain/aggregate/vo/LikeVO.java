package com.isthisteamisthis.umchiumtee.like.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@EqualsAndHashCode
public class LikeVO implements Serializable {

    @Column
    private Long postNo;

    @Column
    private Long userNo;

    public LikeVO(Long postNo, Long userNo) {
        this.postNo = postNo;
        this.userNo = userNo;
    }
}
