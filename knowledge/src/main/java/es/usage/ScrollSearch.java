//package es.usage;
//
//import es.Constants;
//import es.entity.Student;
//import org.apache.commons.lang3.tuple.Pair;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchScrollRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.core.TimeValue;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.slf4j.Logger;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
//public class ScrollSearch {
//
//    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
//    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ScrollSearch.class);
//
//
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Future<Pair<String, List<Student>>> submit = EXECUTOR_SERVICE.submit(new SearchThread<>(Student.class));
//        while (!submit.isDone()) {
//            //do nothing
//        }
//        Pair<String, List<Student>> stringObjectMap = submit.get();
//        stringObjectMap.getRight().forEach(t -> log.info("student:{}", t));
//
//        log.info("-------------");
//        String scrollId = stringObjectMap.getLeft();
//        List<Future<List<Student>>> futures = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            log.info("scrollId:{}", scrollId);
//            Future<Pair<String, List<Student>>> result = EXECUTOR_SERVICE.submit(new ScrollSearchThread<>(scrollId, Student.class));
//            //futures.add(result);
//            result.get().getRight().forEach(s -> log.info("student:{}", s));
//            log.info("-------------");
//            scrollId = result.get().getLeft();
//        }
//        EXECUTOR_SERVICE.shutdownNow();
//    }
//
//    private static class SearchThread<T> implements Callable<Pair<String, List<T>>> {
//        private final Class<T> type;
//
//        private SearchThread(Class<T> clazz) {
//            this.type = clazz;
//        }
//
//        @Override
//        public Pair<String, List<T>> call() throws Exception {
//            SearchSourceBuilder builder = SearchSourceBuilder.searchSource().size(10000).sort("age", SortOrder.ASC);
//            SearchRequest searchRequest = new SearchRequest("student");
//            searchRequest.source(builder).scroll(TimeValue.timeValueMinutes(5));
//            SearchResponse response = new SearchResponse(null);
//            response.getScrollId();
//            List<T> results = new ArrayList<>();
//            for (SearchHit hit : response.getHits().getHits()) {
//                log.info("info:{}", hit.getSourceAsMap());
//                results.add(Constants.OBJECT_MAPPER.readValue(hit.getSourceAsString(), type));
//            }
//            return Pair.of(response.getScrollId(), results);
//        }
//    }
//
//    private static final class ScrollSearchThread<T> implements Callable<Pair<String, List<T>>> {
//        private final Class<T> type;
//        private final String scrollId;
//
//        private ScrollSearchThread(String scrollId, Class<T> type) {
//            this.type = type;
//            this.scrollId = scrollId;
//        }
//
//        @Override
//        public Pair<String, List<T>> call() throws Exception {
//            SearchScrollRequest searchRequest = new SearchScrollRequest(scrollId).scroll(TimeValue.timeValueMinutes(5));
//            SearchResponse response = new SearchResponse(null);
//            List<T> results = new ArrayList<>();
//            for (SearchHit hit : response.getHits().getHits()) {
//                log.info("info:{}", hit.getSourceAsMap());
//                results.add(Constants.OBJECT_MAPPER.readValue(hit.getSourceAsString(), type));
//            }
//            return Pair.of(response.getScrollId(), results);
//        }
//    }
//}
