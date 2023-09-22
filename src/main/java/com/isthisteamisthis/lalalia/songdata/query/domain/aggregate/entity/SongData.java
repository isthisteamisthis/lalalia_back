package com.isthisteamisthis.lalalia.songdata.query.domain.aggregate.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_SONG_DATA")
public class SongData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songDataNo;

    @Column
    private String songName;

    @Column
    private String artistName;

    @Column
    private String imageUrl;
}
