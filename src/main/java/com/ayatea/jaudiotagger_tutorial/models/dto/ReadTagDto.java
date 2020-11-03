package com.ayatea.jaudiotagger_tutorial.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ファイルから読み込んだ楽曲タグ情報のDTOクラス
 * @author ayatea
 */
@Getter
@Setter
public class ReadTagDto {

	// ===================================================================================
	//                                                                           Attribute
	//                                                                           =========
	/*　楽曲名　*/
    private String title;
    /*　楽曲名(かな)　*/
    private String titleKana;
    /*　アーティスト名　*/
    private String artist;
    /*　アーティスト名()　*/
    private String artistKana;
    /*　アルバムアーティスト名　*/
    private String albumArtist;
    /*　アルバムアーティスト名()　*/
    private String albumArtistKana;
    /*　アルバム名　*/
    private String album;
    /*　アルバム名(かな) */
    private String albumKana;
    /*　リリース年 */
    private String releaseYear;
    /*　作曲者 */
    private String composer;
    /*　作曲者(かな) */
    private String composerKana;
    /*　ジャンル */
    private String genre;
}
