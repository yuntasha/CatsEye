package capstone.replyRecoommend.marker.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;


@Slf4j
public class MapWebClient {
    private WebClient webClient;


    public MapWebClient(String mapKey, String mapUrl){
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(mapUrl+"?serviceKey="+mapUrl+"&numOfRows=1000");

        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        this.webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.ACCEPT, "*/*");
                })
                .baseUrl(mapUrl+"?serviceKey="+mapUrl+"&numOfRows=1000")
                .build();
    }

    public String getList(){
        return webClient.get()
                .retrieve()
                .bodyToMono(String.class).block();
    }




}
