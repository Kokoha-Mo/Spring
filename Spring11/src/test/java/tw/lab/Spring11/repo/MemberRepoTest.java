package tw.lab.Spring11.repo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import tw.lab.Spring11.entity.Member;
import tw.lab.Spring11.util.BCrypt;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MemberRepoTest {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private TestEntityManager entityManager;

    private Member testMember;

    @BeforeEach
    void setUp() {
        testMember = new Member();
        testMember.setEmail("lab_test@lab.tw");
        testMember.setPasswd("12345678");
        testMember.setName("lab");

        entityManager.persist(testMember);
        entityManager.flush();
    }

    @Test
    @DisplayName("Email查詢帳號成功")
    void testFindByEmail_Found() {
        Member member = memberRepo.findByEmail("lab_test@lab.tw");

        assertNotNull(member);
        assertTrue(member.getEmail().equals("lab_test@lab.tw"));
        assertTrue(member.getPasswd().equals("12345678"));
        assertTrue(member.getName().equals("lab"));
    }

    @Test
    @DisplayName("Email查詢帳號失敗 => null")
    void testFindByEmail_NotFound() {
        Member member = memberRepo.findByEmail("lab888@lab.tw");

        assertNull(member);
    }

    @Test
    @DisplayName("Email重複")
    void testExistsByEmail_True() {
        boolean exists = memberRepo.existsByEmail("lab_test@lab.tw");
        assertTrue(exists);
    }

    @Test
    @DisplayName("Email不重複")
    void testExistsByEmail_False() {
        boolean exists = memberRepo.existsByEmail("lab999@lab.tw");
        assertFalse(exists);
    }
}
