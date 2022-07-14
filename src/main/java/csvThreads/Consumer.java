package csvThreads;

import entity.Customer;
import entity.CustomerType;
import repository.CustomerRepository;

import java.util.concurrent.BlockingQueue;

import static csvThreads.CsvParser.lineToCustomer;

public class Consumer implements Runnable {
    private final BlockingQueue<String> blockingQueue;
    private final CustomerRepository customerRepository = new CustomerRepository();

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
//                if (blockingQueue.peek() == null) {
//                    break;
//                }
                String line = blockingQueue.take();
                customerRepository.addCustomer(lineToCustomer(line));

            } catch (InterruptedException e) {
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
