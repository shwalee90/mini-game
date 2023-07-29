
import java.util.*;
public class testCom {

//    static int[] target = new int[] {0,1,2,3,4,5,6,7,8,9,10} ;
    static int [] submitArr = new int[3];
    static ArrayList<ArrayList<Integer>> rListGroup = new ArrayList<ArrayList<Integer>>();
    static int zeroCnt = 0;
    static int round = 1;

    static boolean[] banArr = new boolean[] {false , false, false};



    public static void main(String[] args) {
        int token = 10;
        findNum(0 , token);
        ArrayList<Integer> pick = randomPickNum(token);
        System.out.println(pick);

    }

    static private ArrayList<Integer> randomPickNum(int token) {

        ArrayList<ArrayList<Integer>> zeroGroup = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> etcGroup = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> comSub = new ArrayList<>();

        for (ArrayList<Integer> rList : rListGroup){
            if (rList.contains(0)){
                zeroGroup.add(rList);
                continue ;
            }
            etcGroup.add(rList);
        }
        boolean valCheck = true;
        while(valCheck) {

            Random random = new Random();

            int ranNum = (int) (Math.floor(Math.random() * 99) + 1);

            int chanceFactor = (round - zeroCnt - 1) * 15;

            int zeroChance = 65 + chanceFactor;

            if (zeroCnt == 2) {
                zeroChance = 0;
            }

            if (ranNum < zeroChance) {
                int randomIndex = random.nextInt(zeroGroup.size());
                comSub = zeroGroup.get(randomIndex);
                valCheck = validateSub(comSub);
            } else {
                int randomIndex = random.nextInt(etcGroup.size());
                comSub = etcGroup.get(randomIndex);
                valCheck =  false;
            }

        }

        return comSub;


    }

    private static boolean validateSub(ArrayList<Integer> comSub) {
        boolean vRst = false;
        boolean[] tempBan = banArr.clone();
        int tempZeroCnt = zeroCnt;

        for(int i = 0 ; i < comSub.size() ; i++){
            if(comSub.get(i) == 0){
                if(banArr[i]){
                    vRst = true;
                }else{
                    tempZeroCnt = zeroCnt+1;
                    tempBan[i] = true;
                }
            }
        }

        if(tempZeroCnt >2 ){
            vRst = true;
        }

        if(!vRst){
            banArr = tempBan;
            zeroCnt = tempZeroCnt;
        }

        return vRst;
    }

    private static void findNum(int cnt, int token) {
        int sum = 0;
        ArrayList<Integer> rList = new ArrayList<>();
        for (int i = 0; i < submitArr.length ; i++){
            sum = sum + submitArr[i];
            rList.add(submitArr[i]);
        }
        if (sum <= token) {
            if (cnt == 3) {
                if (sum == token) {
                    rListGroup.add(rList);
                }
                return;
            }
        }

        if(cnt == 3){
            return;
        }

        for (int i = 0; i <= token; i++) {
            // 숫자를 담는다.
            submitArr[cnt] = i;
            // 자신을 재귀 호출한다.
            findNum(cnt + 1 , token );
        }
    }
}