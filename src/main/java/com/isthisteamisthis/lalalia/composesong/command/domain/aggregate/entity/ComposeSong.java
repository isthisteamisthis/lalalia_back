package com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.vo.UserNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_COMPOSE_SONG")
public class ComposeSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long composeSongNo;

    @Column(columnDefinition = "BINARY(16)")
    private UUID identifier;

    @Column
    private String title;

    @Column String imgFile;

    @Column
    private String originalFile;

    @Column
    private String aiSongFile;

    @Embedded
    private UserNoVO userNoVO;
    @Builder
    public ComposeSong(UUID identifier, String title, String imgFile, String originalFile, UserNoVO userNoVO) {
        this. identifier = identifier;
        this.title = title;
        this.imgFile = imgFile;
        this.originalFile = originalFile;
        this.userNoVO = userNoVO;
    }
    public void addAiSongFile(String aiSongFile) { this.aiSongFile = aiSongFile; }

}
