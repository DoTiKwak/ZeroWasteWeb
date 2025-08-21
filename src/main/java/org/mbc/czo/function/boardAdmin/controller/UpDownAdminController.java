/*
package org.mbc.czo.function.boardAdmin.controller;


import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.mbc.czo.function.boardAdmin.dto.upload.UploadAdminFileDTO;
import org.mbc.czo.function.boardAdmin.dto.upload.UploadAdminResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@Log4j2
public class UpDownAdminController {


    @Value("${org.mbc.upload.path}")
    private String uploadPath;

    public UpDownAdminController() {
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadAdminResultDTO> upload (UploadAdminFileDTO uploadFileDTO){
        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles() != null){
            final List<UploadAdminResultDTO> list = new ArrayList<>();
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                String originalName = multipartFile.getOriginalFilename();

                log.info(originalName);
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" +originalName);
                boolean image = false;

                try {
                    multipartFile.transferTo(savePath);
                    if(Files.probeContentType(savePath).startsWith("image")){
                        image = true;
                        File thumbFile = new File(uploadPath, "c_" + uuid + "_" + originalName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }  // 트라이 종료
                } catch (IOException e){
                    e.printStackTrace();
                }  // 캐치 종료
                list.add(UploadAdminResultDTO.builder()
                                .uuid(uuid)
                                .fileName(originalName).img(image)
                        .build());
            }); // 엔드 이치
            return list;
        }  // 엔드 이프
        return null;
    }

*/
/*
    @GetMapping("/view/{fileName")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){
        Resource
    } *//*

}

*/
