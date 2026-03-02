package tw.lab.Spring11.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tw.lab.Spring11.entity.Member;
import tw.lab.Spring11.repo.MemberRepo;

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

    void register_emailExists_shouldThrow_andNeverSave() {
        when(repo.existsByEmail("lab@lab.tw")).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> service.register("lab@lab.tw", "12345678", "lab"));

    }
}
