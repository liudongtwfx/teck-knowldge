package groovy

class Main {
    static void main(String[] args) {
        def clos = { println "Hello World" }
        clos.call()
        String name = a(10)
        println name
    }

    static def a(int num) {
        if (num % 2 == 1) {
            return "odd"
        }
        return "even"
    }
}
