package exercise;

// BEGIN
class ReversedSequence implements CharSequence {
    private final String str;

    public ReversedSequence(String str) {
        this.str = new StringBuilder(str).reverse().toString();
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int index) {
        return str.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return str.substring(start, end);
    }

    @Override
    public String toString() {
        return str;
    }
}
// END
