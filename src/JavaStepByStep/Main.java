package JavaStepByStep;


import java.util.*;
import static java.util.Collections.swap;

public class Main {

    public static void main(String[] args) {
        //Instantiate big bottle and small bottle
        Bottle bigBottle = new Bottle(5);
        Bottle smallBottle = new Bottle(3);

        //Input the target volume
        int targetVolume = getValidNumberInput();

        //To store result
        List<String> results = searchSolutions(bigBottle,smallBottle,targetVolume);

        System.out.println("Solutions are:");
        bubbleSort(results);

        showList(results);
        System.out.println("Simplest solution would be:");

        System.out.println(results.get(0));
    }

    public static void bubbleSort (List<String>  list){
        for(int i = 0; i<list.size()-1; i++){
            for(int j = 0; j < list.size()-i-1;j++)
            {
                if(list.get(j).length()>list.get(j+1).length())
                    swap(list,j,j+1);
            }
        }
    }

    public static void showList(List<String> list){
        for(String str : list){
            System.out.println(str);
        }
    }

    public static List<String> searchSolutions(Bottle bigBottle, Bottle smallBottle,int targetVolume){
        List<String> results = new ArrayList<>();
        int round = 100;
        System.out.println("Check would be "+round+" times!");
        while(round > 0) {
            round--;
            System.out.println("Under checking and "+round+" times left.");
            resetBottles(bigBottle, smallBottle);
            while ((bigBottle.getCurrentVolume() != targetVolume) && (smallBottle.getCurrentVolume() != targetVolume)) {
                //To record result
                StringBuilder resultDetail = new StringBuilder();
                resetBottles(bigBottle, smallBottle);
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        bigBottle.fillBottle();
                        resultDetail.append("Begin with filling big bottle:  [" + bigBottle.getCurrentVolume() + "," +
                                smallBottle.getCurrentVolume() + "]" + "-->");
                        String str1 = tryAllPossibilities(bigBottle, smallBottle, targetVolume);
                        if (str1 != null) {
                            resultDetail.append(str1);
                            if (!results.contains(resultDetail.toString())) {
                                results.add(resultDetail.toString());
                            }
                        }
                        break;

                    case 1:
                        smallBottle.fillBottle();
                        resultDetail.append("Begin with filling small bottle:[" + bigBottle.getCurrentVolume() + "," +
                                smallBottle.getCurrentVolume() + "]" + "-->");
                        String str2 = tryAllPossibilities(bigBottle, smallBottle, targetVolume);
                        if (str2 != null) {
                            resultDetail.append(str2);
                            if (!results.contains(resultDetail.toString())) {
                                results.add(resultDetail.toString());
                            }
                        }
                        break;
                }

            }

        }
        return results;
    }

    //Rule 1 : To the same bottle, except for transfer , behavior can not just same as the previous one
    //Rule 2 : To the same bottle, can not empty after it just filled up
    //Rule 3 : To the same bottle, if there is no water then empty behavior can not happen
    //Rule 4 : To the same bottle, if there is no water then transfer behavior can not happen

    public static String tryAllPossibilities(Bottle bigBottle,Bottle smallBottle,int targetVolume){
        int counter = 1;
        StringBuilder resultDetail = new StringBuilder();
        while ((bigBottle.getCurrentVolume() != targetVolume) && (smallBottle.getCurrentVolume() != targetVolume) &&
                counter < 10) {
            switch ((int) (Math.random() * 5)) {
                case 0:
                    if(bigBottle.isJustFilled()) //Rule 1
                        {

                            continue;
                        }
                    bigBottle.fillBottle();
                    //System.out.println("fill big bottle");
                    resultDetail.append("fill big["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+""
                            + "]"+"-->");
                    counter++;
                    break;

                case 1:
                    if(smallBottle.isJustFilled()) //Rule 1
                    {

                        continue;
                    }

                    smallBottle.fillBottle();
                    //System.out.println("fill small bottle");
                    resultDetail.append("fill small["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+
                            "]"+"-->");
                    counter++;
                    break;

                case 2:
                    if( bigBottle.getCurrentVolume() == 0) //Rule 1 and Rule 4
                    {

                        continue;
                    }
                    bigBottle.transferVolume(smallBottle);
                    //System.out.println("big to small");
                    resultDetail.append("big to small["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()
                            +"]"+"-->");
                    counter++;
                    break;

                case 3:
                    if( smallBottle.getCurrentVolume() == 0) //Rule 1 and Rule 4
                    {
                        continue;
                    }
                    smallBottle.transferVolume(bigBottle);
                    //System.out.println("small to big");
                    resultDetail.append("small to big["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()
                            +"]"+"-->");
                    counter++;
                    break;

                case 4:
                    if(smallBottle.isJustEmptied() ||  smallBottle.isJustFilled() ||
                            smallBottle.getCurrentVolume() == 0) //Rule 1 and Rule 2 and Rule 3
                    {

                        continue;
                    }
                    smallBottle.emptyBottle();
                    //System.out.println("empty small");
                    resultDetail.append("empty small["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()
                            +"]"+"-->");
                    counter++;
                    break;

                case 5:
                    if(bigBottle.isJustEmptied() ||bigBottle.getCurrentVolume() == 0 ||
                            bigBottle.isJustFilled()) //Rule 1 and Rule 2 and Rule 3
                    {

                        continue;
                    }
                    bigBottle.emptyBottle();
                    //System.out.println("empty big");
                    resultDetail.append("empty big["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()
                            +"]"+"-->");
                    counter++;
                    break;
            }

            if ((bigBottle.getCurrentVolume() == targetVolume) || (smallBottle.getCurrentVolume() == targetVolume)) {
                resultDetail.append("Steps: "+counter);
                return resultDetail.toString();
            }
           /* if(counter == 10)
                System.out.println("----------------------");
                */
        }
        return null;
    }

    private static void resetBottles(Bottle bigBottle,Bottle smallBottle){
        bigBottle.resetBottle();
        smallBottle.resetBottle();
    }

    private static int getValidNumberInput(){
        while(true) {
            try {
                System.out.println("Input how much liter water you would like to have, please.(1/4)");
                Scanner scanner = new Scanner(System.in);
                int targetVolume = scanner.nextInt();
                if (targetVolume == 1 || targetVolume == 4){
                    System.out.println("Target volume is: "+ targetVolume+" Liter.");
                    return targetVolume;
                }
                else
                    throw new Exception();
            }catch (InputMismatchException e) {
                System.out.println("Your input is not a number");
            } catch (Exception e) {
                System.out.println("Please input the number 1 or 4.");
            }
        }
    }

}


