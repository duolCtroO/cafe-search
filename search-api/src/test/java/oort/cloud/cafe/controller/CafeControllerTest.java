package oort.cloud.cafe.controller;

import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.service.CafeApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CafeControllerTest {
    private MockMvc mockMvc;
    private CafeApplicationService cafeService;
    private CafeController cafeController;

    @BeforeEach
    void setUp(){
        cafeService = Mockito.mock(CafeApplicationService.class);
        cafeController = new CafeController(cafeService);
        mockMvc = MockMvcBuilders.standaloneSetup(cafeController).build();
    }

    @Test
    void 카페_컨트롤러_테스트() throws Exception {
        //given
        int size = 2;
        int page = 1;
        int total = 10;
        String query = "test";
        List<CafePost> mockPosts = List.of(new CafePost("title"
                ,"link"
                , "test"
                ,"testCafe"
                ,"testUrl"));
        PageResult<CafePost> mockPageResult = new PageResult<>(size, page, total, mockPosts);

        //when then
        when(cafeService.search(anyString(), anyInt(), anyInt())).thenReturn(mockPageResult);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe")
                                .param("query", query)
                                .param("page", "1")
                                .param("size", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content().json(
                                """
                                {
                                "size":2
                                ,"page":1
                                ,"total":10
                                ,"contents":[
                                        {
                                            "title":"title"
                                            ,"link":"link"
                                            ,"description":"test"
                                            ,"cafeName":"testCafe"
                                            ,"cafeUrl":"testUrl"
                                        }
                                 ]
                                }
                                """
                        )
                );
    }

    @Test
    void 잘못된_파라미터_예외처리_테스트() throws Exception {
        //given
        String query = "";
        String size = "0";
        String page = "0";

        //when
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe")
                                .param("query", query)
                                .param("page", "1")
                                .param("size", "2"))
        //then
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                ;


    }

}