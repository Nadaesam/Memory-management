import java.util.Objects;

public class Partition {
    private String partitionName;
    private int partitionSize;
    private String processName="External fragment";
    public Partition(String partitionName,  int partitionMemory){
        this.partitionSize=partitionMemory;
        this.partitionName=partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public void setPartitionSize(int partitionSize) {
        this.partitionSize = partitionSize;
    }

    public void setProcessName(String processName) { this.processName = processName; }

    public String getPartitionName() {
        return partitionName;
    }

    public int getPartitionSize() {
        return partitionSize;
    }

    public String getProcessName() { return processName; }


    boolean isEmpty(){
        if( Objects.equals(processName, "External fragment")){return true;}
        else {return false;}
    }


}
