package com.livenow.week5.test;

import com.livenow.week5.domain.Member;
import com.livenow.week5.domain.Order;
import com.livenow.week5.repository.ItemRepository;
import com.livenow.week5.repository.MemberJpaRepository;
import com.livenow.week5.repository.MemberRepository;
import com.livenow.week5.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class Week5Test {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private EntityManager em;

    @Test
    void findMember() {
        //given
        Member member = new Member();
        member.setName("멤버");

        memberRepository.save(member);
        //when
        List<Member> all = memberRepository.findAll();

        for (Member member1 : all) {
            System.out.println("member1 = " + member1.getName());
        }

        Member one = memberRepository.findOne(member.getId());
        System.out.println("one = " + one.getName() );

        List<Member> byName = memberRepository.findByName(member.getName());
        for (Member member1 : byName) {
            System.out.println("member1.getName() = " + member1.getName());
        }
        //then
    }

    @Test
    void findMemberByJpaRepo() {
        //given
        Member member = new Member();
        member.setName("멤버");

        memberJpaRepository.save(member);

        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Member member2 = new Member();
            member2.setName("멤버" + i);
            members.add(memberJpaRepository.save(member2));
        }

        List<Member> guests = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Member guest = new Member();
            guest.setName("게스트" + i);
            guests.add(memberJpaRepository.save(guest));
        }

        //List<Member> all = memberJpaRepository.findByNameLike("%게스트%");
        List<Member> all = memberJpaRepository.findByNameContainingOrderByNameDesc("게스트");
        for (Member member1 : all) {
            System.out.println("member1.getName() = " + member1.getName());
        }


        //when
/*        List<Member> all = memberJpaRepository.findAll();
        for (Member member1 : all) {
            System.out.println("member1 = " + member1.getName());
        }
        Member one = memberJpaRepository.findById(member.getId()).get();
        System.out.println("one = " + one.getName() );
        Queue<Member> byName = memberJpaRepository.findByName(member.getName());
        for (Member member1 : byName) {
            System.out.println("member1.getName() = " + member1.getName());
        }*/
        //then
    }
}
