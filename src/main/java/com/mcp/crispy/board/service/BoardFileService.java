package com.mcp.crispy.board.service;

import com.mcp.crispy.board.dto.BoardFileDto;
import com.mcp.crispy.board.mapper.BoardMapper;
import com.mcp.crispy.common.utils.MyFileUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardFileService {

    private final BoardMapper boardMapper;
    private final MyFileUtils myFileUtils;

    public BoardFileDto getFileDownloadInfo(int boardFileNo, HttpServletRequest request) throws IOException {
        BoardFileDto boardFile = boardMapper.getBoardFileByNo(boardFileNo);

        if (boardFile == null) {
            return null;
        }

        File file = new File(boardFile.getBoardPath(), boardFile.getBoardRename());
        Resource resource = new FileSystemResource(file);

        if (!resource.exists()) {
            return null;
        }

        String boardOrigin = boardFile.getBoardOrigin();
        String employeeAgent = request.getHeader("User-Agent");

        if (employeeAgent != null) {
            if (employeeAgent.contains("Trident")) {
                boardOrigin = URLEncoder.encode(boardOrigin, "UTF-8").replace("+", " ");
            } else if (employeeAgent.contains("Edg")) {
                boardOrigin = URLEncoder.encode(boardOrigin, "UTF-8");
            } else {
                boardOrigin = new String(boardOrigin.getBytes("UTF-8"), "ISO-8859-1");
            }
        }

        return new BoardFileDto(resource, boardOrigin, file.length());
    }

    public BoardFileDto getAllFilesDownloadInfo(int boardNo) throws IOException {
        List<BoardFileDto> boardFileList = boardMapper.getBoardFileList(boardNo);

        if (boardFileList.isEmpty()) {
            return null;
        }

        File tempDir = new File(myFileUtils.getTempPath());
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        String tempFilename = myFileUtils.getTempFilename() + ".zip";
        File tempFile = new File(tempDir, tempFilename);

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(tempFile))) {
            for (BoardFileDto boardFile : boardFileList) {
                ZipEntry zipEntry = new ZipEntry(boardFile.getBoardOrigin());
                zout.putNextEntry(zipEntry);

                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(boardFile.getBoardPath(), boardFile.getBoardRename())))) {
                    zout.write(in.readAllBytes());
                }

                zout.closeEntry();
            }
        }

        Resource resource = new FileSystemResource(tempFile);
        return new BoardFileDto(resource, tempFilename, tempFile.length());
    }
}
