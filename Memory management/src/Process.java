public class Process {
    private String processName;
    private int processSize;
    public Process(String processName,  int processSize){
        this.processSize=processSize;
        this.processName=processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setProcessSize(int processSize) {
        this.processSize = processSize;
    }

    public String getProcessName() {
        return processName;
    }

    public int getProcessSize() {
        return processSize;
    }

}
