package algorithme.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class SeatReservationManager1845 {
    public static void main(String[] args) {

    }


    class SeatManager {
        private Queue<Integer> nums = new PriorityQueue<>();

        public SeatManager(int n) {
            for (int i = 1; i <= n; i++) {
                nums.offer(i);
            }
        }

        public int reserve() {
            if (nums.isEmpty()) {
                return 0;
            }
            return nums.poll();
        }

        public void unreserve(int seatNumber) {
            nums.offer(seatNumber);
        }
    }

}
