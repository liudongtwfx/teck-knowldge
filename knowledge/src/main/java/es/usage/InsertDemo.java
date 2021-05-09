package es.usage;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.Constants;
import es.entity.EsEntity;
import es.entity.Student;
import es.entity.Teacher;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertDemo {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(100);
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(InsertDemo.class);

    public static void main(String[] args) throws Exception {
        BulkRequest bulk = new BulkRequest();
        for (int i = 0; i < 100; i++) {
            EXECUTOR_SERVICE.submit(new RequestThread(new Teacher(i, "liudong" + i, "liudong" + i), Teacher.INDEX));
            EXECUTOR_SERVICE.submit(new RequestThread(new Student(i, "liudong" + i, "liudong" + i), Student.INDEX));
        }
        // Constants.LOCALHOST_CLIENT.bulk(bulk, RequestOptions.DEFAULT);
        Thread.sleep(5000);
        EXECUTOR_SERVICE.shutdownNow();
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
                System.out.println(indexRequest);
                IndexResponse index = Constants.LOCALHOST_CLIENT.index(indexRequest, RequestOptions.DEFAULT);
            } catch (Exception e) {
                log.error("exception", e);
            }
        }
    }
}


