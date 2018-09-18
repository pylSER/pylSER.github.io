package HelloTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TLUTest {

    double[] myArr={1.1,3.1,-1,-2,0.5};

    int threshold=1;

    ArrayList<ArrayList<Integer>> list=new ArrayList<>();


    private void getTuple(int[] arr,int changeIndex){
        if(changeIndex<0){
            String res=Arrays.toString(arr);
            res=res.replaceAll("\\[","");
            res=res.replaceAll("\\]","");

            int booleanRes=getRes(arr);

            res=res+", "+ booleanRes;

            if(booleanRes==1){
                ArrayList<Integer> temp=new ArrayList<>();

                for (int i:arr) {
                    temp.add(i);
                }

                list.add(temp);
            }

            System.out.println(res.replaceAll(" ",""));
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

    private String getLatex(){
        StringBuilder stringBuilder=new StringBuilder("f(a,b,c,d,e)=");

        for (int i = 0; i <list.size() ; i++) {
            ArrayList<Integer> arr=list.get(i);

            for (int j = 0; j <arr.size() ; j++) {
                char c= (char) ('a'+j);

                int res=arr.get(j);

                if(res==0){
                    //qu fan
                    stringBuilder.append(" \\bar{"+c+"}");
                }else{
                    stringBuilder.append(" "+c);
                }
            }


            if(i!=list.size()-1){
                stringBuilder.append("+");
            }
        }

        return stringBuilder.toString();
    }







    public static void main(String[] args) {
        TLUTest test=new TLUTest();

        int[] arr=new int[test.myArr.length];

        test.getTuple(arr,arr.length-1);

        System.out.println("=======Here is the boolean function:=======");
        System.out.println(test.getLatex());
    }
}
