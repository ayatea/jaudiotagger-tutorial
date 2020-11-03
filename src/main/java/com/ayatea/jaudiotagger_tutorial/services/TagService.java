package com.ayatea.jaudiotagger_tutorial.services;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Service;

import com.ayatea.jaudiotagger_tutorial.models.dto.ReadTagDto;


/**
 * タグ情報のサービスクラス
 * @author ayatea
 */
@Service
public class TagService {

	// ===================================================================================
	//                                                                              Action
	//                                                                           =========
	/**
	 * 引数で受け取ったファイルからタグ情報を読み込む
	 * @param file ファイル
	 * @return 読み込んだタグ情報をマッピングしたDTO
	 * @throws InvalidAudioFrameException 
	 * @throws ReadOnlyFileException 
	 * @throws TagException 
	 * @throws IOException 
	 * @throws CannotReadException 
	 */
	public ReadTagDto read(File file) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		ReadTagDto readTag = new ReadTagDto();
		AudioFile audioFile = AudioFileIO.read(file);
		Tag tag = audioFile.getTag();
		
		readTag.setTitle(tag.getFirst(FieldKey.TITLE));
		readTag.setTitleKana(tag.getFirst(FieldKey.TITLE_SORT));
		readTag.setArtist(tag.getFirst(FieldKey.ARTIST));
		readTag.setArtistKana(tag.getFirst(FieldKey.ARTIST_SORT));
		readTag.setAlbumArtist(tag.getFirst(FieldKey.ALBUM_ARTIST));
		readTag.setAlbumArtistKana(tag.getFirst(FieldKey.ALBUM_ARTIST_SORT));
		readTag.setAlbum(tag.getFirst(FieldKey.ALBUM));
		readTag.setAlbumKana(tag.getFirst(FieldKey.ALBUM_SORT));
		readTag.setReleaseYear(tag.getFirst(FieldKey.YEAR));
		readTag.setComposer(tag.getFirst(FieldKey.COMPOSER));
		readTag.setComposerKana(tag.getFirst(FieldKey.COMPOSER_SORT));
		readTag.setGenre(tag.getFirst(FieldKey.GENRE));

		return readTag;
	}
}
