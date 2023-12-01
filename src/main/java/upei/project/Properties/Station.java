package upei.project.Properties;

import upei.project.Player;

public class Station extends Property {

    private final int BASE_RENT = 25;
    /**
     * Constructor that creates a BoardSquare instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    public Station(int iLoc, String name, int buyPrice) {
        super(iLoc, name, buyPrice);
    }

    @Override
    public void playerOnLocation(Player player) {
        if (this.getOwner() == player){
            return;
        }
        else if (this.getOwner() != player && this.getOwner() == null){ // not owned
            if (player.makeChoice(this)){
                this.setOwner(player);
                player.subtractMoney(this.getBuyPrice());
            }
        }
        else{ // a player has landed on owners land
            player.subtractMoney(this.getRent());
            this.getOwner().addMoney(this.getRent());
        }
    }

    public int getRent() {return calcRent();}

    private int calcRent() {
        return (int) (Math.pow(2, this.getOwner().getLandsOwnedOfType(Station.class).size()-1)) * this.BASE_RENT;
    }

    @Override
    public String toString() {
        return "Station{" +
                "BASE_RENT=" + BASE_RENT +
                "} " + super.toString();
    }
}
