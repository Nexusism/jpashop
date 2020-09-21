package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 데이터 변경할땐 트랜잭셔널이 꼭 있어야함 class안에 public 메소드들은 다 트랜잭션이 걸리게됨
 // readOnly를 true로 하면 읽기전용이되서 조회만하는 메소드에서 속도를 최적화시킬수있음, 쓰기에서는 하면 쓸수없게됨
@RequiredArgsConstructor // final이 있는 필드만 가지고 생성자를 만들어줌
public class MemberService {


    //@Autowired 는 필드주입 인젝션, 수정하기 까다롭다
    private final MemberRepository memberRepository; //final로 해야 컴파일시점에 체크를 해줌

     // 생성자 인젝션, 중간에 바꿀수없어서 좋고, 테스트케이스작성때도 바꿀수있다, 생성자가 하나있을땐 자동으로 Autowired 적용되서 없어도됨
    // @RequiredArgsConstructor 를 쓰면 final이있는 필드를 자동으로 생성자 만들어줌
    /**public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; 
    }  
     **/
    /**
    @Autowired  // setter인젝션, 몫을 줄수있으나 돌아갈때 누가 바꿀수있다
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }**/

    //회원 가입
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //회원 전체 조회

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
