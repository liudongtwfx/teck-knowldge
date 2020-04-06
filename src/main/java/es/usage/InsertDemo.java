package es.usage;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.Constants;
import es.entity.EsEntity;
import es.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class InsertDemo {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws Exception {
        BulkRequest bulk = new BulkRequest();
        for (int i = 0; i < 100; i++) {
            EXECUTOR_SERVICE.submit(new RequestThread(new Teacher(i, "liudong" + i, "liudong" + i), Teacher.INDEX));
        }
        Constants.LOCALHOST_CLIENT.bulk(bulk, RequestOptions.DEFAULT);
    }


    private static class RequestThread implements Runnable {
        private final EsEntity entity;
        private final String index;

        public RequestThread(EsEntity entity, String index) {
            this.entity = entity;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                IndexRequest indexRequest = new IndexRequest(index);
                indexRequest.id(entity.getId());
                indexRequest.source(OBJECT_MAPPER.writeValueAsString(entity), XContentType.JSON);
                Constants.LOCALHOST_CLIENT.index(indexRequest, RequestOptions.DEFAULT);
            } catch (Exception e) {
                log.error("exception", e);
            }
        }
    }
}


