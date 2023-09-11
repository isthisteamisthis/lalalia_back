package com.isthisteamisthis.umchiumtee.message.command.domain.aggregate.entity;

import com.isthisteamisthis.umchiumtee.message.command.domain.aggregate.vo.GetUserNoVO;
import com.isthisteamisthis.umchiumtee.message.command.domain.aggregate.vo.SendUserNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Table(name = "TBL_MESSAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageNo;

    @Embedded
    private SendUserNoVO sendUserNoVO;

    @Embedded
    private GetUserNoVO getUserNoVO;

    @Column
    private String content;

    @Column
    private Date date;

    @Builder
    public Message(Long messageNo, SendUserNoVO sendUserNoVO, GetUserNoVO getUserNoVO, String content, Date date) {
        this.messageNo = messageNo;
        this.sendUserNoVO = sendUserNoVO;
        this.getUserNoVO = getUserNoVO;
        this.content = content;
        this.date = date;
    }

}
