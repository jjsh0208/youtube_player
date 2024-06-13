package com.example.youtube_player.service;


import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class YoutubeService {


    //@Value 어노테이션을 사용하여 application.properties 에서 설정한 key 가져와서 apikey에 주입
    @Value("${youtube.api.key}")
    private String apiKey;

    // JSON 데이터를 처리하기 위한 JsonFactory 객체 생성
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    // YouTube 객체를 빌드하여 API에 접근할 수 있는 YouTube 클라이언트 생성
    private static final YouTube YOUTUBE = new YouTube.Builder(
            new com.google.api.client.http.javanet.NetHttpTransport(),
            JSON_FACTORY,
            request -> {}).setApplicationName("youtube-caching-example").build();

    @Cacheable("youtubeSearchCache")
    public HashMap<String,String> searchVideo(String query) throws IOException {
        log.info("test"); //캐싱 확인용

        // YouTube Search API를 사용하여 동영상 검색을 위한 요청 객체 생성
        YouTube.Search.List search = YOUTUBE.search().list(Collections.singletonList("id,snippet"));

        // API 키 설정
        search.setKey(apiKey);

        // 검색어 설정
        search.setQ(query);

        //조회되는 타입 (채널,리스트 제외)
        search.setType(Collections.singletonList("video"));
        
        //카테고리 id 10은 음악 (음악 동영상만 가져온다.)
        search.setVideoCategoryId("10");
        
        //검색 결과 개수 3개로 설정
        search.setMaxResults(3L);

        // 검색 요청 실행 및 응답 받아오기
        SearchListResponse searchResponse = search.execute();

        // 검색 결과에서 동영상 목록 가져오기
        List<SearchResult> searchResultList = searchResponse.getItems();

        //뷰로 전달할 해시맵
        HashMap<String,String> youtubeInfo = new HashMap<>(); //제목 : 동영상 url

            if (searchResultList != null && searchResultList.size() > 0) {
                //검색 결과 중 첫 번째 동영상 정보 가져오기
                for (SearchResult s : searchResultList) {
                    String videoTitle = s.getSnippet().getTitle();
                    String videoId = s.getId().getVideoId();
                    //채널 자체를 가져오면 url VideoId가 null 로 반환된다. 건너뛰기
                    if (videoId == null) continue;
                    youtubeInfo.put(videoTitle, videoId);
                }
            }

        return youtubeInfo;
    }
}