package com.mcp.crispy.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@PropertySource("classpath:image.properties")
public class MyFileUtils {
    @Value("${file.board-dir.window}")
    public String UP_DIR_WINDOW;

    @Value("${file.board-dir.mac}")
    public String UP_DIR_MAC;

    // 현재 날짜
    public static final LocalDate TODAY = LocalDate.now();

    // 업로드 경로 반환
    public String getBoardPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String baseDir;
        if (os.contains("win")) {
            baseDir = UP_DIR_WINDOW;
        } else {
            baseDir = UP_DIR_MAC;
        }

        return baseDir+"board" + DateTimeFormatter.ofPattern("/yyyy/MM/dd").format(TODAY);
    }

    // 저장될 파일명 반환
    public String getBoardRename(String boardOrigin) {
        String extName = null;
        if(boardOrigin.endsWith(".tar.gz")) {
            extName = ".tar.gz";
        } else {
            extName = boardOrigin.substring(boardOrigin.lastIndexOf("."));
        }
        return UUID.randomUUID().toString().replace("-", "") + extName;
    }

    // 임시 파일 경로 반환
    public String getTempPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "c:/temporary";
        } else if (os.contains("mac")) {
            return "/Users/Shared/temporary";
        } else {
            // 기본 경로, 다른 Unix 기반 시스템에 대해
            return "/tmp";
        }
    }

    // 임시 파일 이름 반환 (확장자 제외)
    public String getTempFilename() {
        return System.currentTimeMillis() + "";
    }

}