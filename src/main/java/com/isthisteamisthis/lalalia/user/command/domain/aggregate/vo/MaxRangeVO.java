package com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class MaxRangeVO {

    @Column
    private Float maxFrequency;

    @Column
    private String maxNote;

    @Column
    private String maxOctave;
}
