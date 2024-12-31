package oort.cloud.cafe.controller;

import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.CafeSearchRequest;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.exception.ErrorType;
import oort.cloud.cafe.service.SearchQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class CafeControllerItTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchQueryService cafeService;

    @Test
    void 정상적인_파라미터_테스트() throws Exception {
        //given
        int size = 2;
        int page = 1;
        int total = 10;
        List<CafePost> mockPosts = List.of(new CafePost("title"
                ,"link"
                , "test"
                ,"testCafe"
                ,"testUrl"));
        PageResult<CafePost> mockPageResult = new PageResult<>(size, page, total, mockPosts);
        CafeSearchRequest req = new CafeSearchRequest("계엄", 1, 2);

        when(cafeService.search(anyString(), anyInt(), anyInt())).thenReturn(mockPageResult);
        //when
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe")
                                .param("query", req.getQuery())
                                .param("size", String.valueOf(req.getSize()))
                                .param("page", String.valueOf(req.getPage()))
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(10))
        ;
    }

    @Test
    void 쿼리가_비어있을_경우_응답() throws Exception {
        //given
        int size = 2;
        int page = 1000000;
        int total = 10;
        List<CafePost> mockPosts = List.of(new CafePost("title"
                ,"link"
                , "test"
                ,"testCafe"
                ,"testUrl"));
        PageResult<CafePost> mockPageResult = new PageResult<>(size, page, total, mockPosts);
        CafeSearchRequest req = new CafeSearchRequest("", 1, 2);

        when(cafeService.search(anyString(), anyInt(), anyInt())).thenReturn(mockPageResult);
        //when
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe")
                                .param("query", req.getQuery())
                                .param("size", String.valueOf(req.getSize()))
                                .param("page", String.valueOf(req.getPage()))
                )
                //then
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("query 파라미터는 필수 값 입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").value(ErrorType.INVALID_PARAMETER.name()));
    }

}
