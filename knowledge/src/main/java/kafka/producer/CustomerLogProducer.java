package kafka.producer;

import kafka.CustomerDto;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class CustomerLogProducer {
    public static void main(String[] args) {
        System.out.println((double) 1024 * 1024 / (double) (60 * 60) * 8);
        System.out.println(277 * 8);
        // new ProducerProcesser(new Properties());
    }

    private static class ProducerProcesser implements Runnable {
        private static final String TOPIC = "customer";
        private final Properties properties;
        private final Producer<String, CustomerDto> producer;

        ProducerProcesser(Properties properties) {
            this.properties = properties;
            this.producer = new KafkaProducer<>(properties);
        }

        @Override
        public void run() {
            CustomerDto customer = new CustomerDto();
            ProducerRecord<String, CustomerDto> record = new ProducerRecord<>(TOPIC, customer);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {

                }
            });
        }
    }
}
