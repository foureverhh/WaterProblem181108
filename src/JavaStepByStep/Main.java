package JavaStepByStep;


import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Instantiate big bottle and small bottle
        Bottle bigBottle = new Bottle(5);
        Bottle smallBottle = new Bottle(3);
/*
        bigBottle.fillBottle();
        System.out.println("Big bottle after fill,volume is: "+bigBottle.getCurrentVolume() );


       bigBottle.transferVolume(smallBottle);
        System.out.println("Big bottle after transfer, volume is: "+ bigBottle.getCurrentVolume()+"\n"
        +"Small bottle after transfer, volume is:"+ smallBottle.getCurrentVolume());


        smallBottle.fillBottle();
        System.out.println("Small bottle after fill,volume is: "+smallBottle.getCurrentVolume());
 */

        while(true) {
            try {
                System.out.println("Input how much liter water you would like to have, please.(1/4)");
                Scanner scanner = new Scanner(System.in);
                int targetVolume = scanner.nextInt();
                if (targetVolume == 1 || targetVolume == 4){
                    System.out.println("Target volume is: "+ targetVolume);
                    getDesiredVolume(bigBottle,smallBottle,targetVolume);
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

    private static void getDesiredVolume(Bottle bigBottle, Bottle smallBottle,int targetVolume){

        while ((bigBottle.getCurrentVolume() != targetVolume) && (smallBottle.getCurrentVolume()!= targetVolume)){
            resetBottles(bigBottle,smallBottle);
            System.out.println("Reset done");
            switch ((int)(Math.random()*2)){
                case 0:
                    System.out.println("Begin with filling big Bottle:");
                    bigBottle.fillBottle();
                    System.out.println("Step 1 : Fill the big bottle");
                    System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    beginFillingBottle(bigBottle,smallBottle,targetVolume);
                    break;

                case 1:
                    System.out.println("Begin with filling small Bottle:");
                    smallBottle.fillBottle();
                    System.out.println("Step 1: Fill the small bottle");
                    System.out.println("Small bottle volume is: "+ smallBottle.getCurrentVolume()+". Big bottle volume is: "+bigBottle.getCurrentVolume());
                    beginFillingBottle(bigBottle,smallBottle,targetVolume);
                    break;
            }
        }
    }

    //Rule 1 : To the same bottle, behavior can not just same as the previous one
    //Rule 2 : To the same bottle, can not empty after it just filled up
    //Rule 3 : To the same bottle, if there is no water then empty behavior can not happen
    //Rule 4 : To the same bottle, if there is no water then transfer behavior can not happen
    //Rule 5 : Between two bottles, if one bottle just got water transferred in, it can not transfer back on next behavior

        private static void beginFillingBottle(Bottle bigBottle,Bottle smallBottle,int targetVolume){
        int counter = 1;
        while ((bigBottle.getCurrentVolume() != targetVolume) && (smallBottle.getCurrentVolume() != targetVolume) && counter < 10) {
            switch ((int) (Math.random() * 5)) {
                case 0:
                    if(bigBottle.isJustFilled()) //Rule 1
                        continue;
                    bigBottle.fillBottle();
                    System.out.println("Step "+(counter+1)+" : Fill the big bottle");
                    System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 1:
                    if(smallBottle.isJustFilled()) //Rule 1
                        continue;
                    smallBottle.fillBottle();
                    System.out.println("Step "+(counter+1)+" : Fill the small bottle");
                    System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 2:
                    if(bigBottle.isJustGotTransferred() || bigBottle.getCurrentVolume() == 0) //Rule 1 and Rule 4
                        continue;
                    bigBottle.transferVolume(smallBottle);
                    System.out.println("Step "+(counter+1)+" : Transfer from big bottle to small bottle");
                    System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 3:
                    if(smallBottle.isJustGotTransferred() || smallBottle.getCurrentVolume() == 0) //Rule 1 and Rule 4
                        continue;
                    smallBottle.transferVolume(bigBottle);
                    System.out.println("Step "+(counter+1)+" : Transfer from small bottle to big bottle");
                    System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 4:
                    if(smallBottle.isJustEmptied() || smallBottle.getCurrentVolume() == 0 || smallBottle.isJustFilled()) //Rule 1 and Rule 2 and Rule 3
                        continue;
                    smallBottle.emptyBottle();
                    System.out.println("Step "+(counter+1)+": Empty small bottle");
                    System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;

                case 5:
                    if(bigBottle.isJustEmptied() ||bigBottle.getCurrentVolume() == 0 || bigBottle.isJustFilled()) //Rule 1 and Rule 2 and Rule 3
                        continue;
                    bigBottle.emptyBottle();
                    System.out.println("Step "+(counter+1)+" : Empty big bottle");
                    System.out.println("Big bottle volume is: "+ bigBottle.getCurrentVolume()+". Small bottle volume is: "+smallBottle.getCurrentVolume());
                    counter++;
                    break;
            }

            if ((bigBottle.getCurrentVolume() == targetVolume) || (smallBottle.getCurrentVolume() == targetVolume) || counter == 10) {
                //System.out.println(counter);
                System.out.println("--------------------------------------------");
            }

        }
    }

    private static void resetBottles(Bottle bigBottle,Bottle smallBottle){
        bigBottle.resetBottle();
        smallBottle.resetBottle();
    }


}


/*
    private void makeDesiredVolume(Bottle smallBottle, Bottle bigBottle,int desiredVolume,int stepCounter){
        while ((smallBottle.getVolume() != desiredVolume || bigBottle.getVolume() != desiredVolume) && stepCounter <= 10){




            }

        }
    }
*/

