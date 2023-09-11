package com.isthisteamisthis.umchiumtee.like.command.domain.aggregate.entity;

import com.isthisteamisthis.umchiumtee.like.command.domain.aggregate.vo.LikeVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "TBL_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Like implements Serializable {

    @EmbeddedId
    private LikeVO likeVO;

    @Builder
    public Like(LikeVO likeVO) {
        this.likeVO = likeVO;
    }

}
