package JavaStepByStep;


import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Instantiate big bottle and small bottle
        Bottle bigBottle = new Bottle(5);
        Bottle smallBottle = new Bottle(3);


        //Input the target volume
        int targetVolume = getValidNumberInput();

        //To store result
        List<String> results = new ArrayList<>();

        results = getDesiredVolume(bigBottle,smallBottle,targetVolume);
         for(int i = 0; i < results.size(); i++)
             System.out.println("Solution: "+results.get(i));

    }

    private static List<String> getDesiredVolume(Bottle bigBottle, Bottle smallBottle,int targetVolume){

        List<String> results = new ArrayList<>();

        while ((bigBottle.getCurrentVolume() != targetVolume) && (smallBottle.getCurrentVolume()!= targetVolume)){
            //To describe result
            StringBuilder resultDetail = new StringBuilder();

            resetBottles(bigBottle,smallBottle);
            //System.out.println("Reset done");
            switch ((int)(Math.random()*2)){
                case 0:
                    //System.out.println("Begin with filling big Bottle:");
                    bigBottle.fillBottle();
                    //System.out.println("Step 1 : Fill the big bottle");
                    //System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    resultDetail.append("["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+"]"+"-->");
                    String str1 = beginFillingBottle(bigBottle,smallBottle,targetVolume);
                    if(str1!=null){
                        resultDetail.append(str1);
                        System.out.println("ResultDetail first if is: "+resultDetail);
                        if(!results.contains(resultDetail.toString()) ){
                            results.add(resultDetail.toString());
                            System.out.println("ResultDetail upper is: "+resultDetail);
                        }
                    }
                    System.out.println("ResultDetail upper outside if: "+resultDetail);
                    break;

                case 1:
                    //System.out.println("Begin with filling small Bottle:");
                    smallBottle.fillBottle();
                    //System.out.println("Step 1: Fill the small bottle");
                    //System.out.println("Small bottle volume is: "+ smallBottle.getCurrentVolume()+". Big bottle volume is: "+bigBottle.getCurrentVolume());
                    resultDetail.append("["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+"]"+"-->");
                    String str2 = beginFillingBottle(bigBottle,smallBottle,targetVolume);
                    if(str2!=null){
                        resultDetail.append(str2);
                        if(!results.contains(resultDetail.toString()))
                            results.add(resultDetail.toString());
                    }
                    break;
            }
        }
        return results;
    }

    //Rule 1 : To the same bottle, behavior can not just same as the previous one
    //Rule 2 : To the same bottle, can not empty after it just filled up
    //Rule 3 : To the same bottle, if there is no water then empty behavior can not happen
    //Rule 4 : To the same bottle, if there is no water then transfer behavior can not happen
    //Rule 5 : Between two bottles, if one bottle just got water transferred in, it can not transfer back on next behavior

    private static String beginFillingBottle(Bottle bigBottle,Bottle smallBottle,int targetVolume){
        int counter = 1;
        StringBuilder resultDetail = new StringBuilder();
        while ((bigBottle.getCurrentVolume() != targetVolume) && (smallBottle.getCurrentVolume() != targetVolume) && counter < 10) {
            switch ((int) (Math.random() * 5)) {
                case 0:
                    if(bigBottle.isJustFilled()) //Rule 1
                        continue;
                    bigBottle.fillBottle();
                    resultDetail.append("["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+"]"+"-->");
                    //System.out.println("Step "+(counter+1)+" : Fill the big bottle");
                    //System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 1:
                    if(smallBottle.isJustFilled()) //Rule 1
                        continue;
                    smallBottle.fillBottle();
                    resultDetail.append("["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+"]"+"-->");
                    //System.out.println("Step "+(counter+1)+" : Fill the small bottle");
                    //System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 2:
                    if(bigBottle.isJustGotTransferred() || bigBottle.getCurrentVolume() == 0) //Rule 1 and Rule 4
                        continue;
                    bigBottle.transferVolume(smallBottle);
                    resultDetail.append("["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+"]"+"-->");
                    //System.out.println("Step "+(counter+1)+" : Transfer from big bottle to small bottle");
                    //System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 3:
                    if(smallBottle.isJustGotTransferred() || smallBottle.getCurrentVolume() == 0) //Rule 1 and Rule 4
                        continue;
                    smallBottle.transferVolume(bigBottle);
                    resultDetail.append("["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+"]"+"-->");
                    //System.out.println("Step "+(counter+1)+" : Transfer from small bottle to big bottle");
                    //System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 4:
                    if(smallBottle.isJustEmptied() || smallBottle.getCurrentVolume() == 0 || smallBottle.isJustFilled()) //Rule 1 and Rule 2 and Rule 3
                        continue;
                    smallBottle.emptyBottle();
                    resultDetail.append("["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+"]"+"-->");
                    //System.out.println("Step "+(counter+1)+": Empty small bottle");
                    //System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 5:
                    if(bigBottle.isJustEmptied() ||bigBottle.getCurrentVolume() == 0 || bigBottle.isJustFilled()) //Rule 1 and Rule 2 and Rule 3
                        continue;
                    bigBottle.emptyBottle();
                    resultDetail.append("["+bigBottle.getCurrentVolume()+","+smallBottle.getCurrentVolume()+"]"+"-->");
                    //System.out.println("Step "+(counter+1)+" : Empty big bottle");
                    //System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;
            }

            if ((bigBottle.getCurrentVolume() == targetVolume) || (smallBottle.getCurrentVolume() == targetVolume)) {
                //System.out.println(counter);
                System.out.println("--------------------------------------------");
                resultDetail.append("steps are "+ counter);
                System.out.println("find answer"+resultDetail.toString());
                return resultDetail.toString();
            }
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
                    System.out.println("Target volume is: "+ targetVolume);
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


