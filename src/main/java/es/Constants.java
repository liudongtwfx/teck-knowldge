package es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class Constants {
    public static final RestHighLevelClient LOCALHOST_CLIENT = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Constants() {
    }
}
