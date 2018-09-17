package HelloTest;

import java.util.Arrays;

public class TLUTest {

    double[] myArr={1.1,3.1,-1,-2,0.5};

    int threshold=1;

    private void getTuple(int[] arr,int changeIndex){
        if(changeIndex<0){
            System.out.println(Arrays.toString(arr)+"--->"+ getRes(arr));
            return;
        }

        arr[changeIndex]=0;
        getTuple(arr,changeIndex-1);


        arr[changeIndex]=1;
        getTuple(arr,changeIndex-1);

    }


    private int getRes(int[] arr){
        double sum=0;

        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i]*myArr[i];
        }

        if(sum>=threshold){
            return 1;
        }else {
            return 0;
        }
    }

    public static void main(String[] args) {
        TLUTest test=new TLUTest();

        int[] arr=new int[test.myArr.length];

        test.getTuple(arr,arr.length-1);
    }
}
