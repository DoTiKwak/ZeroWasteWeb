package org.mbc.czo.cart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mbc.czo.function.cart.domain.Cart;
import org.mbc.czo.function.cart.repository.CartRepository;
import org.mbc.czo.function.member.domain.Member;
import org.mbc.czo.function.member.dto.MemberJoinDTO;
import org.mbc.czo.function.member.repository.MemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@Log4j2
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // 🔥 주입

    @PersistenceContext
    EntityManager em;


//    private Member createMember() {
//        MemberJoinDTO memberJoinDTO = new MemberJoinDTO();
//        memberJoinDTO.setMid("testId");
//        memberJoinDTO.setMemail("test@email.com");
//        memberJoinDTO.setMname("홍길동");
//        memberJoinDTO.setMaddress("수원시 팔달구");
//        memberJoinDTO.setMpassword("1234");
//        memberJoinDTO.setMphoneNumber("01012345678");
//
//        return Member.createMember(memberJoinDTO, passwordEncoder);
//    }
//
//    @Test
//    @DisplayName("회원 더미데이터 생성 테스트")
//    public void createMemberTest() {
//        Member member = createMember();
//        log.info("생성된 멤버: {}", member);
//    }

   /* @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        Member member = createMember();
        memberJpaRepository.saveAndFlush(member);  //  즉시 flush

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMember().getMemail(), member.getMemail());
    }
*/


}
