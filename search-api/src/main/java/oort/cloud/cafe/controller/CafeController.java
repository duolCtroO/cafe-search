package oort.cloud.cafe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.*;
import oort.cloud.cafe.service.CafeApplicationService;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cafe")
@Tag(name = "Cafe", description = "Cafe api")
public class CafeController {
    private final CafeApplicationService cafeService;

    @Operation(summary = "search API", description = "Cafe 게시글 제공", tags = {"Cafe"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PageResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))})
    @GetMapping
    public PageResult<CafePost> cafeContents(@Valid CafeSearchRequest cafeSearchRequest){
        return cafeService.search(
                cafeSearchRequest.getQuery()
                , cafeSearchRequest.getPage()
                , cafeSearchRequest.getSize());
    }

    @Operation(summary = "Stats API", description = "조회된 게시글 통계 제공", tags = {"Cafe"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = StatsResponse.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))})
    @GetMapping("/stats")
    public StatsResponse findQueryStats(
                @RequestParam(name = "query") String query,
                @RequestParam(name = "date") LocalDate date
            ){
        return cafeService.findQueryCount(query, date);
    }

    @Operation(summary = "Stats API", description = "조회된 게시글 통계 제공", tags = {"Cafe"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StatsResponse.class)))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))})
    @GetMapping("/stats/top")
    public List<StatsResponse> findQueryTop(){
        return cafeService.findDailyTopQuery();
    }
}
