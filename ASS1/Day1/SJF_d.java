import java.io.*;
import java.util.*;

public class SJF_d{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int bt[] = new int[n];
        int at[] = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = (i+1);
        }

        System.out.println("Enter the Burst Time for each process.");
        for (int i = 0; i < n; i++) {
            System.out.print("For Process " + (i + 1) + ":");
            bt[i] = sc.nextInt();
        }

        System.out.println("Enter the arrival time for each process.");
        for (int j = 0; j < n; j++) {
            System.out.print("For Process " + (j + 1) + ":");
            at[j] = sc.nextInt();
        }

        //Bubble sort on arrival time
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i-1;j++){
                if(at[j]>at[j+1]){
                    int t1,t2,t3;
                    t1=pid[j];
                    pid[j]=pid[j+1];
                    pid[j+1]=t1;

                    t2=at[j];
                    at[j]=at[j+1];
                    at[j+1]=t2;

                    t3=bt[j];
                    bt[j]=bt[j+1];
                    bt[j+1]=t3;
                }
            }
        }
        //Bubble sort on burst time
        for(int k=0;k<n;){
            int i,j;
            i=k;j=k;
            while(i==j && j<=k){
                j++;
            }
            j--;
            for(int p=i;p<j;p++){
                for(int q=i;q<j-1;q++){
                if(bt[q]>bt[q+1]){
                    int t1,t2,t3;
                    t1=pid[q];
                    pid[q]=pid[q+1];
                    pid[q+1]=t1;

                    t2=at[q];
                    at[q]=at[q+1];
                    at[q+1]=t2;

                    t3=bt[q];
                    bt[q]=bt[q+1];
                    bt[q+1]=t3;
                }
                }
            }
            k=j+1;
        }

		// main algo
        int wt[] = new int[n];
        int tt[] = new int[n];  
        int ct[] = new int[n+1];

        // calculation of tat ct and wt ; ct[i+1]--> ct of i (exception)
        int id=0;   // id-> idle time
        ct[0]=0;
        int ptr=0;  // ptr is to find out after ct which are the programs present
        if(at[0]>ct[0]){
            id=at[0]-ct[0];
        }
        for(int i=0;i<n;i++){
                ct[i+1]=ct[i]+bt[i]+id;
                tt[i]=ct[i+1]-at[i];
                wt[i]=tt[i]-bt[i];
                boolean t = false;
                id=0;
                for(int j=i+1;j<n;j++){
                    if(at[j]<=ct[i+1]){
                        ptr=j;
                        t=true;
                    }
                    else if(at[j]>ct[i+1] && t!=true){
                        id++;
                        break;
                    }
                    else if(at[j]>ct[i+1]){
                        break;
                    }
                }
                for(int p=i+1;p<=ptr;p++){
                for(int q=i+1;q<=ptr-1;q++){
                if(bt[q]>bt[q+1]){
                    int t1,t2,t3;
                    t1=pid[q];
                    pid[q]=pid[q+1];
                    pid[q+1]=t1;

                    t2=at[q];
                    at[q]=at[q+1];
                    at[q+1]=t2;

                    t3=bt[q];
                    bt[q]=bt[q+1];
                    bt[q+1]=t3;
                }
                }
                }
            }
            
        System.out.println("\nProcesses ||" + " Burst Time ||" + " Arrival Time ||" + " Completion Time ||"  + " Turn-Around Time  ||" + " Waiting Time ");
        float awt = 0;
        float att = 0;
        for (int i = 0; i < n; i++) {
            awt = awt + wt[i];
            att = att + tt[i];
            System.out.println("   " + pid[i] + "\t  ||\t" + bt[i] + "\t||\t" + at[i] + "\t||\t  " + ct[i+1] + "\t   ||\t   " + tt[i] + "\t\t||\t " + wt[i]);
        }

        awt = awt / n;
        att = att / n;

        System.out.println("\nAverage waiting time = " + awt);
        System.out.println("\nAverage turn around time = " + att);
    }

}

