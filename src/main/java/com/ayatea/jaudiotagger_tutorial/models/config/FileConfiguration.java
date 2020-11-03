package com.ayatea.jaudiotagger_tutorial.models.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * ファイル設定クラス
 * @author ayatea
 */
@Component
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileConfiguration {

	// ===================================================================================
	//                                                                           Attribute
	//                                                                           =========
	private String tmp;
}
