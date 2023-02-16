public class World {
    static {
        System.out.println("Hello world");
    }

    public static void main(String[] args) {
        System.out.println(new World());
    }

    public String toString() {
        return "My Name";
    }
}