package org.mbc.czo.function.product.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import org.mbc.czo.function.product.domain.ItemImg;
import org.mbc.czo.function.product.repository.ItemImgRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}") // application.properties 파일에 등록한 itemImgLocation 값을 불러와서 itemImgLocation에 넣음
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){ // 이미지 파일이 있으면
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes()); // UUID+확장자가 리턴됨
            imgUrl = "/images/item/" + imgName;
        }

        // 상품 이미지 정보 저장
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
        // imgName: 실제 로컬에 저장된 상품 이미지 파일의 이름
        // oriImgName: 업로드했던 상품 이미지 파일의 원래 이름
        // imgUrl: 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로
    }


}
