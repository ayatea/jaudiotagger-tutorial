package com.ayatea.jaudiotagger_tutorial.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ayatea.jaudiotagger_tutorial.models.dto.ReadTagDto;
import com.ayatea.jaudiotagger_tutorial.services.FileService;
import com.ayatea.jaudiotagger_tutorial.services.TagService;


/**
 * タグ情報のコントローラークラス
 * @author ayatea
 */
@Controller
@RequestMapping("/tag")
public class TagsController {
	
	// ===================================================================================
	//                                                                          Definition
	//                                                                           =========
	public static final List<String> VALID_EXTENSIONS = new ArrayList<>(Arrays.asList("mp3", "m4a"));
	
	// ===================================================================================
	//                                                                           Attribute
	//                                                                           =========
	@Autowired
	private TagService tagService;
	@Autowired
	private FileService fileService;

	// ===================================================================================
	//                                                                               Index
	//                                                                           =========
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String readIndex(Model model) {
        return "tag/index";
	}
	
	// ===================================================================================
	//                                                                              Action
	//                                                                           =========
	/**
	 * 引数で指定された楽曲ファイルからタグ情報を読み込む
	 * @param model モデル
	 * @param file 画面でアップロードされたファイル
	 * @return タグ情報読み込み結果画面(エラーの場合はファイル選択画面)
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws CannotReadException
	 * @throws TagException
	 * @throws ReadOnlyFileException
	 * @throws InvalidAudioFrameException
	 */
	@RequestMapping(value="/read", method = RequestMethod.POST)
	public String readTag(Model model, @RequestParam("uploadFile") MultipartFile file) throws IllegalStateException, IOException, CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		String fileName = file.getOriginalFilename();
		if (VALID_EXTENSIONS.contains(fileService.getSuffix(fileName))) {
			File tmp = fileService.createTmpFile(file);
			try {
				ReadTagDto tag = tagService.read(tmp);
				model.addAttribute("tag", tag);
			} catch (Exception e) {
				if (tmp != null) tmp.delete();
			} finally {
				if (tmp != null) tmp.delete();
			}
	        return "tag/read/result";
		} else {
			model.addAttribute("error", "有効な拡張子は" + VALID_EXTENSIONS.toString() + "です。");
			return "tag/index";
		}
	}
}
