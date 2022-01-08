package algorithme.design;

public class IteratorForCombination1286 {
    public static void main(String[] args) {

    }

    class CombinationIterator {
        private String characters;
        private int combinationLength;

        public CombinationIterator(String characters, int combinationLength) {
            this.characters = characters;
            this.combinationLength = combinationLength;
        }

        public String next() {
            return null;
        }

        public boolean hasNext() {
            return false;
        }
    }
}
