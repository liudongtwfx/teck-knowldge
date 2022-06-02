package groovy

class Main {
    static void main(String[] args) {
        def clos = { println "Hello World" };
        clos.call();
    }
}
