package IntruderDetection.SRC.DataCollectors;

public abstract class DataCollector<T> {
    public DataCollector(){

    }

    public abstract boolean insertData(T data);
}
