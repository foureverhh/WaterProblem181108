package JavaStepByStep;

public class Bottle {

    private int currentVolume;
    private int fullVolume;
    private int exceedVolume;
    private boolean justFilled;
    private boolean justEmptied;
    private boolean justTransferred;
    private boolean justGotTransferred;


    public Bottle(int fullVolume) {
        this.fullVolume = fullVolume;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void fillBottle(){
         currentVolume = fullVolume;
         justFilled = true;
         justEmptied = false;

    }

    public void emptyBottle(){
         currentVolume = 0;
         justEmptied =true;
         justFilled =false;
    }


    public void getTransferredVolume(int volume){
        if(currentVolume + volume >= fullVolume ){
            exceedVolume = currentVolume +volume - fullVolume;
            currentVolume = fullVolume;
        }
        else
            currentVolume += volume;
        justTransferred = false;
        justFilled = false;
        justEmptied = false;
        justGotTransferred = true;
    }

    public void transferVolume(Bottle theOtherBottle){
        //If a bottle has just transferred its water to the other bottle and made the other full
        //, then the second transfer would empty the bottle but the other bottle stay full.
        if(justTransferred  && theOtherBottle.getCurrentVolume() == theOtherBottle.getFullVolume()){
            currentVolume = 0;
        }
        //If a bottle never transferred before, then if the volume being transferred and the current volume in the other
        //bottle exceeds the full volume of the other bottle, and keep the exceed part in the original bottle
        else if (theOtherBottle.getExceedVolume() >= 0){
            theOtherBottle.getTransferredVolume(currentVolume);
            currentVolume = theOtherBottle.getExceedVolume();
            theOtherBottle.setExceedVolume(0);
        }
        justFilled = false;
        justEmptied = false;
        justTransferred = true;
        justGotTransferred = false;
    }

    public boolean isJustFilled() {
        return justFilled;
    }

    public boolean isJustEmptied() {
        return justEmptied;
    }

    public boolean isJustTransferred() {
        return justTransferred;
    }

    public void resetBottle(){
        currentVolume = 0;
        justTransferred = false;
        justEmptied = false;
        justFilled = false;
        justGotTransferred = false;
    }

    public boolean isJustGotTransferred() {
        return justGotTransferred;
    }

    public int getExceedVolume() {
        return exceedVolume;
    }

    public void setExceedVolume(int exceedVolume) {
        this.exceedVolume = exceedVolume;
    }

    public int getFullVolume() {
        return fullVolume;
    }
}
