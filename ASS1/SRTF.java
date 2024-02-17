import java.util.*;
class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.completionTime = -1; // Initialized to -1, indicating not completed
    }
}

public class SRTF {
    public static int cpuIdleTime=0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();
        System.out.print("Enter number of process:");
		int n = input.nextInt();
        System.out.print("Enter Time Quantum:");
		int timeQuantum = input.nextInt();

		System.out.println("Enter Arrival Time and Burst Time:");

		for (int i = 0; i < n; i++) {
			System.out.print("P" + (i + 1) + ": ");
            int at = input.nextInt();
            int bt = input.nextInt();
            processes.add(new Process((i), at, bt));
		}

        runSRTF(processes, timeQuantum);
        calculateTurnaroundTime(processes);
        calculateWaitingTime(processes);
        print(processes,n);
    }

    public static void runSRTF(List<Process> processes, int timeQuantum) {
        int currentTime = 0;
        int completedProcesses = 0;
        List<Process> readyQueue = new ArrayList<>();

        while (completedProcesses < processes.size()) {
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && process.remainingTime > 0) {
                    readyQueue.add(process);
                }
            }

            if (!readyQueue.isEmpty()) {
                Collections.sort(readyQueue, Comparator.comparingInt(p -> p.remainingTime));
                Process currentProcess = readyQueue.get(0);
                readyQueue.clear();

                if (currentProcess.remainingTime > timeQuantum) {
                    currentProcess.remainingTime -= timeQuantum;
                    currentTime += timeQuantum;
                } else {
                    currentTime += currentProcess.remainingTime;
                    currentProcess.remainingTime = 0;
                    currentProcess.completionTime = currentTime;
                    completedProcesses++;
                }
            } else {
                cpuIdleTime++;
                currentTime++;
            }
        }
    }

    public static void calculateTurnaroundTime(List<Process> processes) {
        for (Process process : processes) {
            process.turnaroundTime = process.completionTime - process.arrivalTime;
        }
    }

    public static void calculateWaitingTime(List<Process> processes) {
        for (Process process : processes) {
            process.waitingTime = process.turnaroundTime - process.burstTime;
        }
    }

    public static void print(List<Process> processes, int n) {
        System.out.println("P\tAT\tBT\tCT\tWT\tTAT");
		for (Process process : processes) {
			System.out.println(process.pid + "\t" + process.arrivalTime + "\t" + process.burstTime + "\t" + process.completionTime + "\t" +process.waitingTime + "\t" + process.turnaroundTime);
		}
        int avg_wt = 0;
        int avg_tat = 0;

        for (Process element : processes) {
            avg_wt += element.waitingTime;
        }
        for (Process element : processes) {
            avg_tat += element.turnaroundTime;
        }
		avg_wt /= n;
		avg_tat /= n;
		System.out.println("Average Waiting Time = " + avg_wt);
		System.out.println("Average Turnaround Time = " + avg_tat);
		System.out.println("CPU idle Time = " + cpuIdleTime);
    }
}
