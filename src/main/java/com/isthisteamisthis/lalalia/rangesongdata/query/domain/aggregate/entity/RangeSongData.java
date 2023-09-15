package com.isthisteamisthis.lalalia.rangesongdata.query.domain.aggregate.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_RANGE_SONG_DATA")
public class RangeSongData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataSongNo;

    @Column
    private String songName;

    @Column
    private String artistName;
}
