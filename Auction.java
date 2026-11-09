import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> listOfLots;
    // The number that will be given to the next lot entered into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        listOfLots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        listOfLots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot aLot : listOfLots) {
            System.out.println(aLot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            
            boolean successful = selectedLot.bidFor(new Bid(bidder,value));//question2
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null if a lot with this 
     * number does not exist.
     * @param lotNumber The number of the lot to return.
     * @return The lot with the given number, or null.
     */
    public Lot getLot(int lotNumber)//q6
    {
        {
            for (Lot lot : listOfLots) {
                if (lot.getNumber() == lotNumber) {
                    return lot;
                }
            }

            return null;
        }
       
    }
    public void close()//q3
    {
        for (Lot lot : listOfLots) {
            Bid highestBid = lot.getHighestBid();

            if (highestBid != null) {
                System.out.println(lot.toString());
                System.out.println("Successful bidder: " +
                    highestBid.getBidder().getName());
                System.out.println("Winning bid: " +
                    highestBid.getValue());
            }
            else {
                System.out.println(lot.toString());
                System.out.println("This lot has no bidder.");
            }
        }
    }

    public ArrayList<Lot> getUnsold()//q4
    {
        ArrayList<Lot> unsoldLots = new ArrayList<>();

        for (Lot lot : listOfLots) {
            Bid highestBid = lot.getHighestBid();

            if (highestBid == null) {
                unsoldLots.add(lot);
            }
        }

        return unsoldLots;
    }
    //q5 : the original lot number values dont change which causes their positions to not match their positios in the ArrayList
    //if the get method is called it would return a wrong lot or an error

public Lot removeLot(int number)//q7
    {
        Lot lot = getLot(number);

        if (lot != null) {
            listOfLots.remove(lot);
        }

        return lot;
    }
}
//q8 : they both have the add method, clear method,the contains method,and other methods that are similar
//methods that only linkedlist has are addfirst, addlast, getfirst, getlast, offer, poll, removefirst and removelast



