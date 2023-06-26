import java.util.ArrayList;

public class FirstFitPolicy {
    ArrayList<Process> processes ;
    ArrayList<Partition> partitions ;
    protected int partitionsIdx;
    protected ArrayList<Process> notAllocatedProcess=new ArrayList<Process>();

    public  FirstFitPolicy(ArrayList<Process> processes,ArrayList<Partition> partitions){
        this.processes=processes;
        this.partitions=partitions;
        partitionsIdx=partitions.size()-1;
    }

    public void manageMemory(){

        for (Process process : processes) {
            int firstPartitionIdx=getFirstPartitionIdx(process.getProcessSize());
            if (firstPartitionIdx==-1){
                notAllocatedProcess.add(process);
            }
            else{
                if (process.getProcessSize() < partitions.get(firstPartitionIdx).getPartitionSize()) {
                    partitionsIdx++;
                    int newPartitionSize = partitions.get(firstPartitionIdx).getPartitionSize() - process.getProcessSize();
                    String newPartitionName = "partition" + Integer.toString(partitionsIdx);
                    partitions.add(firstPartitionIdx+1,new Partition(newPartitionName, newPartitionSize));
                }
                partitions.get(firstPartitionIdx).setPartitionSize(process.getProcessSize());
                partitions.get(firstPartitionIdx).setProcessName(process.getProcessName());
            }
        }
    }


    public void displayPartitions(){
        for (Partition partition : partitions) {
            System.out.println(partition.getPartitionName() + " (" +partition.getPartitionSize()+" KB) "+"=> "+partition.getProcessName());
        }
    }

    public void displayNotAllocatedPartition(){
        for ( Process process : notAllocatedProcess) {
            System.out.println(process.getProcessName()+ " can not be allocated");
        }
    }

    public int getFirstPartitionIdx(int processSize){
        for (int i=0 ; i< partitions.size()  ;i++){
            if (partitions.get(i).isEmpty()&&partitions.get(i).getPartitionSize()>=processSize){
                return i;
            }
        }
        return -1;
    }

}

