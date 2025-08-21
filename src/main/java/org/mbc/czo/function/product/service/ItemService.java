package org.mbc.czo.function.product.service;

import lombok.RequiredArgsConstructor;
import org.mbc.czo.function.product.domain.Item;
import org.mbc.czo.function.product.domain.ItemImg;
import org.mbc.czo.function.product.dto.ItemFormDto;
import org.mbc.czo.function.product.dto.ItemImgDto;
import org.mbc.czo.function.product.dto.ItemSearchDto;
import org.mbc.czo.function.product.repository.ItemImgRepository;
import org.mbc.czo.function.product.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        // 상품 등록
        Item item = itemFormDto.createItem(); // 아이템 생성하여
        itemRepository.save(item); // 저장

        // 이미지 등록
        for(int i=0; i<itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if(i==0)
                itemImg.setRepimgYn("Y"); // 대표 이미지 여부 값
                // 첫 번째 이미지일 경우 대표 상품 이미지 여부 값을 Y로 설정. 나머지 상품 이미지는 "N"로 설정
            else
                itemImg.setRepimgYn("N");
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }

    // 등록된 상품을 불러오는 메소드
/*    @Transactional(readOnly = true) // 데이터 일관성 보장, 불필요한 update sql 생성 방지 = 트랜잭션 안에서 조회하면 같은 트랜잭션 내에선 일관된 데이터를 볼 수 있음
    public ItemFormDto getItemDtl(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId); // 해당 상품 이미지를 조회
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for(ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg); // itemImg를 ItemImgDto로 변환

        }
    }*/

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        // 상품 조회
        return  itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }
}
