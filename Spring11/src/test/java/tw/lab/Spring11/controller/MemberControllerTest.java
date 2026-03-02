package tw.lab.Spring11.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import tools.jackson.databind.ObjectMapper;
import tw.lab.Spring11.dto.Register;
import tw.lab.Spring11.entity.Member;
import tw.lab.Spring11.service.MemberService;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("測試 register -> 200 + Member")
    void register_Success() throws Exception {
        // 假設
        Register register = new Register("lab@lab.tw", "12345678", "lab");
        Member mockMember = new Member();
        mockMember.setEmail("lab@lab.tw");

        when(memberService.register(anyString(), anyString(), anyString())).thenReturn(mockMember);
        // 測試
        mockMvc.perform(post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(register)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("lab@lab.tw"));
    }

    @Test
    @DisplayName("測試 register -> 200 + error")
    void register_Failure() throws Exception {
        // 假設
        Register register = new Register("lab@lab.tw", "12345678", "lab");

        when(memberService.register(anyString(), anyString(), anyString())).thenThrow(new RuntimeException("xxx"));
        // 測試
        mockMvc.perform(post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(register)))
                .andExpect(status().isOk())
                .andExpect(content().string("error:xxx"));
    }
}
