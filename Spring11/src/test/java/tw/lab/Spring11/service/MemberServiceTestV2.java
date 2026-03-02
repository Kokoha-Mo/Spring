package tw.lab.Spring11.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tw.lab.Spring11.entity.Member;
import tw.lab.Spring11.repo.MemberRepo;
import tw.lab.Spring11.util.BCrypt;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTestV2 {
    @Mock
    MemberRepo repo;

    @InjectMocks
    MemberService service;

    private Member saved;

    @BeforeEach
    void setUp() {
        saved = new Member();
        saved.setId(12L);
    }

    @Test
    void register_emailExists_shouldThrow_andNeverSave() {
        when(repo.existsByEmail("lab@lab.tw")).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> service.register("lab@lab.tw", "12345678", "lab"));

        // 帳號存在，不應存檔
        verify(repo).existsByEmail("lab@lab.tw");
        verify(repo, never()).save(any(Member.class));
    }

    @Test
    void register_success_shouldHashPassed_andReturnId() {
        // 假設
        when(repo.existsByEmail("lab@lab.tw")).thenReturn(false);
        when(repo.save(any(Member.class))).thenReturn(saved);
        // 擷取參數
        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        // 測試
        Member member = service.register("lab@lab.tw", "12345678", "lab");
        // 驗證結果
        assertEquals(12L, member.getId());
        // 驗證方法執行
        verify(repo).existsByEmail("lab@lab.tw");
        verify(repo).save(captor.capture());

        Member arg = captor.getValue();

        assertEquals("lab@lab.tw", arg.getEmail());
        assertNotNull(arg.getPasswd());
        assertNotEquals("12345678", arg.getPasswd());
        assertTrue(BCrypt.checkpw("12345678", arg.getPasswd()));
    }
}
