package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {
	
	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
	
	//파일 이름과 경로를 받아서 삭제하는 메서드
	public int deleteFile(String imageFileName, String savePath) {
		boolean isDel= true;//삭제가 잘 이루어졌는지 체크하는 변수
		log.info(">>> deleteFile Method 접근");
		log.info(">>> imageFileName "+imageFileName);
		
		File fileDir = new File(savePath);//파일 경로를 알아보기 위함
		File removeFile= new File(fileDir+File.separator+imageFileName);//파일 경로+구분자+파일명
		File removeThumbFile = new File(fileDir+File.separator+"th_"+imageFileName);
		
		//파일이 있어야(존재해야) 삭제
		//exist(): 해당하는 파일이 있는지 없는지 true/false 값으로 알려줌
		if(removeFile.exists()||removeThumbFile.exists()) {
			isDel=removeFile.delete();//boolean 형태로 리턴
			log.info(">>> FileHandler remove"+(isDel?"ok":"fail"));
			if(isDel) {
				isDel = removeThumbFile.delete();//원본이 삭제되어야 thumbfile도 지우겠다.
				log.info(">>> FileHandler remove Thumbnail: "+(isDel?"ok":"fail"));
			}
		}
		log.info(">> FileHandler remove OK");
		return isDel?1:0;
	}

}
