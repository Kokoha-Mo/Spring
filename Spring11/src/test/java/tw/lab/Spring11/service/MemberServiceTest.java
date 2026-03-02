package tw.lab.Spring11.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
//assertEquals,assertThrows,assertNotNull,assertNotEquals
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
public class MemberServiceTest {

    @Mock
    MemberRepo memberRepo; // 虛構的，不會真的連資料庫

    @InjectMocks // 把上面虛構的東西注入使用
    MemberService service; // 真的

    @Test // 方法_條件_動作; sevice.register() => throw IllegalArgumentException
    void register_emailExists_shouldThrow() {
        // 設定/假設:memberrepo.existByEmail("tony@lab.tw") => true
        when(memberRepo.existsByEmail("tony@lab.tw")).thenReturn(true);
        // 執行
        assertThrows(IllegalArgumentException.class, () -> service.register("tony@lab.tw", "12345678", "b1"));
        // 驗證
        verify(memberRepo, never()).save(any());
    }

    @Test
    void register_success() {
        when(memberRepo.existsByEmail("nami@lab.tw")).thenReturn(false);

        Member saved = new Member();
        saved.setId(1L);
        when(memberRepo.save(any(Member.class))).thenReturn(saved);

        // ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);

        Member member = service.register("nami@lab.tw", "12345678", "nami");
        assertEquals(1L, member.getId());

        // verify(memberRepo).existsByEmail("nami@lab.tw");
        // verify(memberRepo).save(captor.capture());
        // Member memberArg = captor.getValue();

        // assertEquals("nami@lab.tw", memberArg.getEmail());
    }
}
