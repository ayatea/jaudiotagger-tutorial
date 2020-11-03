package com.ayatea.jaudiotagger_tutorial.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ayatea.jaudiotagger_tutorial.models.config.FileConfiguration;

/**
 * ファイルに関するサービスクラス
 * @author ayatea
 */
@Service
public class FileService {
	
	// ===================================================================================
	//                                                                           Attribute
	//                                                                           =========
	@Autowired
	private FileConfiguration fileConf;
	
	// ===================================================================================
	//                                                                              Action
	//                                                                             =======
	/**
	 * Config指定のディレクトリに一時ファイルを作成する
	 * @param file ファイル
	 * @return 指定先のディレクトリに作成されたファイル
	 * @throws IOException 
	 */
	public File createTmpFile(MultipartFile file) throws IOException {
		StringBuffer filePath = new StringBuffer(fileConf.getTmp());
		File tmpDir = mkdir(filePath);
		File tmpFile = new File(tmpDir.getPath() + "/" + file.getOriginalFilename());
		byte[] bytes = file.getBytes();	
		BufferedOutputStream tmpFileStream = new BufferedOutputStream(new FileOutputStream(tmpFile));
		tmpFileStream.write(bytes);
		tmpFileStream.close();
		return tmpFile;
	}

	// ===================================================================================
	//                                                                        Small Helper
	//                                                                        ============  
    /**
     * ファイル名から拡張子を取得する
     * @param fileName ファイル名
     * @return ファイルの拡張子(拡張子が取得できない場合ファイル名を返却)
     */
    public String getSuffix(String fileName) {
        if (StringUtils.isEmpty(fileName)) return null;
        
        int idx = fileName.lastIndexOf(".");
        
        if (idx != -1) {
            return fileName.substring(idx + 1);
        } else {
        	return fileName;
        }
    }

    /**
    * ディレクトリを作成する
    * @param path パス
    * @return 作成したディレクトリ
    */
   private File mkdir(StringBuffer path) {
       File dir = new File(path.toString());
       dir.mkdirs();
       return dir;
   }
}
