import java.util.Iterator;

public class Sequence implements Iterator<Double> {

    private double element;
    private int index;

    public Sequence()
    {
        reset();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Double next() {
        element = (element / ++index) + 1;
        return element;
    }

    public void reset()
    {
        element = 0;
        index = 0;
    }

    public double getCurrentElement()
    {
        return element;
    }

    public int getCurrentIndex()
    {
        return index;
    }

    @Override
    public String toString()
    {
        return index == 0 ? "sequence is not started" : String.format("U(%d) = %.6f", index, element);
    }
}
