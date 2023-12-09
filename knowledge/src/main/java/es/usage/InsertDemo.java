//package es.usage;
//
//import co.elastic.clients.elasticsearch._types.BulkIndexByScrollFailure;
//import co.elastic.clients.elasticsearch.core.DeleteByQueryRequest;
//import co.elastic.clients.elasticsearch.core.DeleteByQueryResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import es.Constants;
//import es.entity.EsEntity;
//import es.entity.Teacher;
//import lombok.SneakyThrows;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.TermQueryBuilder;
//import org.slf4j.Logger;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class InsertDemo {
//
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(100);
//    private static final Logger log = org.slf4j.LoggerFactory.getLogger(InsertDemo.class);
//
//    public static void main(String[] args) throws Exception {
//        BulkRequest bulk = new BulkRequest();
//        for (int i = 0; i < 100; i++) {
//            Map<String, Object> deleteKeyValue = new HashMap<>();
//            deleteKeyValue.put("teacherName", "liudong");
//            new DeleteByQueryThread("teacher", deleteKeyValue).run();
//            new RequestThread(new Teacher(i, "liudong" + i, "liudong"), Teacher.INDEX).run();
//            // EXECUTOR_SERVICE.submit(new RequestThread(new Student(i, "liudong" + i, "liudong" + i), Student.INDEX));
//        }
//        Map<String, Object> deleteKeyValue = new HashMap<>();
//        deleteKeyValue.put("teacherName", "liudong1");
//        EXECUTOR_SERVICE.submit(new DeleteByQueryThread("teacher", deleteKeyValue));
//        // Constants.LOCALHOST_CLIENT.bulk(bulk, RequestOptions.DEFAULT);
//        Thread.sleep(5000);
//        EXECUTOR_SERVICE.shutdownNow();
//    }
//
//
//    private static class RequestThread implements Runnable {
//        private final EsEntity entity;
//        private final String index;
//
//        public RequestThread(EsEntity entity, String index) {
//            this.entity = entity;
//            this.index = index;
//        }
//
//        @Override
//        public void run() {
//            try {
//            } catch (Exception e) {
//                log.error("exception", e);
//            }
//        }
//    }
//
//    private static class DeleteByQueryThread implements Runnable {
//        private final Map<String, Object> deleteQuery;
//        private final String index;
//
//        public DeleteByQueryThread(String index, Map<String, Object> deleteQuery) {
//            this.deleteQuery = deleteQuery;
//            this.index = index;
//        }
//
//        @SneakyThrows
//        @Override
//        public void run() {
//            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//            for (Map.Entry<String, Object> keyValue : deleteQuery.entrySet()) {
//                boolQueryBuilder.must(new TermQueryBuilder(keyValue.getKey(), keyValue.getValue()));
//            }
//            DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest.Builder()
//                    .refresh(true).index(index).build();
//            // deleteByQueryRequest.setRefresh(true);
//            // deleteByQueryRequest.setAbortOnVersionConflict(false);
//            // deleteByQueryRequest.indices(index);
//            // deleteByQueryRequest.setQuery(boolQueryBuilder);
//            System.out.println(deleteByQueryRequest.toString());
//            DeleteByQueryResponse bulkByScrollResponse = Constants.LOCALHOST_CLIENT.deleteByQuery(deleteByQueryRequest);
//            System.out.println(bulkByScrollResponse.toString());
//            for (BulkIndexByScrollFailure bulkFailure : bulkByScrollResponse.failures()) {
//                System.out.println(bulkFailure.cause().stackTrace());
//            }
//        }
//    }
//}
//
//
