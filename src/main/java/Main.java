import java.util.Collections;
import java.util.List;

public class Main {
    private static final String name = "";

    public static void main(String[] args) {

    }


    static String findAddressFromIp(String ip, List<IpArea> address) {
        Collections.sort(address);
        return binarySearch(ip, address, 0, address.size());
    }

    static String binarySearch(String ip, List<IpArea> sortedIpAddress, int start, int end) {
        if (start > end || sortedIpAddress.size() <= end) {
            return null;
        }
        int half = (start + end) / 2;
        if (matchFrom(ip, sortedIpAddress.get(half))) {
            return sortedIpAddress.get(half).address;
        }
        // smaller than half
        if (ip.compareTo(sortedIpAddress.get(half).ipFrom) < 0) {
            return binarySearch(ip, sortedIpAddress, 0, half - 1);
        }
        // greater than half
        if (ip.compareTo(sortedIpAddress.get(half).ipTo) > 0) {
            return binarySearch(ip, sortedIpAddress, half + 1, end);
        }
        return null;
    }

    private static boolean matchFrom(String ip, IpArea ipArea) {
        return ip.compareTo(ipArea.ipFrom) >= 0 && ip.compareTo(ipArea.ipTo) <= 0;
    }

    static class IpArea implements Comparable<IpArea> {
        String ipFrom;
        String ipTo;
        String address;

        public IpArea(String ipFrom, String ipTo) {
            this.ipFrom = ipFrom;
            this.ipTo = ipTo;
        }

        @Override
        public int compareTo(Main.IpArea o) {
            return ipFrom.compareTo(o.ipFrom);
        }
    }
}
