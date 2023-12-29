package org.sopt.lequuServer.domain.sticker.model;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.lequuServer.domain.rollingpaper.model.RollingPaper;
import org.sopt.lequuServer.global.common.model.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "posted_sticker")
public class PostedSticker extends BaseTimeEntity {

    @Id
    @Column(name = "posted_sticker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int positionX;

    private int positionY;

    private int degree;

    @ManyToOne
    @JoinColumn(name = "rolling_paper_id")
    private RollingPaper rollingPaper;

    @ManyToOne
    @JoinColumn(name = "sticker_id")
    private Sticker sticker;

    public PostedSticker(int positionX, int positionY, int degree, RollingPaper rollingPaper, Sticker sticker) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.degree = degree;
        this.rollingPaper = rollingPaper;
        this.sticker = sticker;
    }

    public static PostedSticker of(int positionX, int positionY, int degree, RollingPaper rollingPaper, Sticker sticker) {
        return new PostedSticker(positionX, positionY, degree, rollingPaper, sticker);
    }
}