package oort.cloud.cafe.repository;

import oort.cloud.cafe.data.CafeItem;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.CafeResponse;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.feign.NaverFeignClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NaverCafeRepositoryTest {

    private NaverFeignClient naverFeignClient = mock(NaverFeignClient.class);
    private NaverCafeRepository naverCafeRepository;

    @BeforeEach
    void setUp(){
        naverCafeRepository = new NaverCafeRepository(naverFeignClient);
    }


    @Test
    void 적절한_데이터형식으로_반환하는지(){
        //given
        int size = 1;
        int page = 2;
        String query = "test";

        String title = "test";
        String link = "test";
        String description = "test";
        String cafename = "test";
        String cafeurl = "test";

        String title2 = "test2";
        String link2 = "test2";
        String description2 = "test2";
        String cafename2 = "test2";
        String cafeurl2 = "test2";

        CafeItem cafeItem1 = new CafeItem(title, link, description, cafename, cafeurl);
        CafeItem cafeItem2 = new CafeItem(title2, link2, description2, cafename2, cafeurl2);
        List<CafeItem> cafeItemList
                = List.of(cafeItem1, cafeItem2);

        CafeResponse response = new CafeResponse(new Date(), 4, 1, 2, cafeItemList);
        //when
        when(naverFeignClient.search(query, 1, 2)).thenReturn(response);
        PageResult<CafePost> result = naverCafeRepository.searchContents(query, size, page);

        //then
        assertThat(result.getContents().size()).isSameAs(2);

        for (CafePost content : result.getContents()) {
            assertThat(content.getCafeName()).satisfiesAnyOf(
                    cafeName -> assertThat(cafeName).contains(cafename),
                    cafeName -> assertThat(cafeName).contains(cafename2)
            );
        }


    }

}