package JavaStepByStep;

public class Bottle {

    private int currentVolume;
    private int fullVolume;


    public Bottle() {

    }
    public Bottle(int fullVolume) {
        this.fullVolume = fullVolume;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public int getFullVolume(){
        return fullVolume;
    }

    public void fillBottle(){
         currentVolume = fullVolume;
    }

    public void emptyBottle(){
         currentVolume = 0;
    }

    public void updateVolume(int volume){
        if(currentVolume + volume >= fullVolume )
            currentVolume = fullVolume;
        else
            currentVolume += volume;
    }

    public void transferredVolume(Bottle theOtherBottle){
       theOtherBottle.updateVolume(currentVolume);
       currentVolume = 0;
    }
}
