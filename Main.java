import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] arr = {1,3,5,7,9};
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length - i; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}