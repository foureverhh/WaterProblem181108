package JavaStepByStep;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //Instantiate big bottle and small bottle
        Bottle bigBottle = new Bottle(3);
        Bottle smallBottle = new Bottle(5);

        //Step counter and desired volumes
        int desiredVolumeOne = 1;
        int desiredVolumeTwo = 2;
        int stepCounter = 0;

        bigBottle.fillBottle();
        System.out.println("Big bottle after fill,volume is: "+bigBottle.getCurrentVolume() );

        bigBottle.transferredVolume(smallBottle);
        System.out.println("Big bottle after transfer, volume is: "+ bigBottle.getCurrentVolume()+"\n"
        +"Small bottle after transfer, volume is:"+ smallBottle.getCurrentVolume());


    }


    private int randomNumber(){
       return (int) (Math.random()*3);
    }
/*
    private void makeDesiredVolume(Bottle smallBottle, Bottle bigBottle,int desiredVolume,int stepCounter){
        while ((smallBottle.getVolume() != desiredVolume || bigBottle.getVolume() != desiredVolume) && stepCounter <= 10){




            }

        }
    }
    */
}
