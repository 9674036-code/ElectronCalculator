// Mo Spiegel | 4B | Partner: Ethan Tang

/** Project description:

 *      Physical transistor simulator

 *      Randomly runs electrons through transistors in a bitwise addition circuit

 *      Each electron has a probability to quantum tunnel through the transistor -> create an error in the result of addition

 *      This class holds logic to determine random quantum tunneling, calculate tunneling probabiltiy from physical constants

 */
import java.util.Random;


//Simulates an electron with some probability of quantum tunneling through a transistor


public
class Electron {
    //random object
    Random random = new Random();

    // Calculate quantum tunneling probabiltiy: Final probabiltiy should be around 20-30%

    // The physical constants that define the final probabiltiy are explicitly defined here for user educational value

    // These constants are fiducially chosen to give a probability of 20-30%

    static double width = 1e-9;
// Width of transistor meters (1 nanometer)
    static double mass_e = 9.109 * Math.pow(10,-31);
// Eelectron mass
    static double rPlanck_const = 1.055 * Math.pow(10,-34);
// reduced planck constant
    static double energyDiff = 2.2 * Math.pow(10,-20);
//Difference between the potential energy height of the barrier and the electron energy (J)
    static double kappa = Math.sqrt(2*mass_e*energyDiff/Math.pow(rPlanck_const,2));
//Power law index scaling factor
    static double tunProb = Math.exp(-2* kappa * width); // tunneling probability modeled as a power law of width
    String gate; // Which gates tunneled through (append "AND", "NOT" depending on gate tunneled throgh )
    int tunNum; // Number of gates tunneled through


    // constructor


    public Electron() {

        this.tunNum= 0;

        this.gate= "";

    }

    // Member methods

    //Reset/clear electron class

    public void resetClass() {

        tunNum = 0;

        gate = "";

    }

    // logic for not gate: Randomly see if the electron tunnels through one transistor barrier

    public Boolean notGateTunnel() {

        if (tunProb>= random.nextDouble()) {
//generate random number 0-1, compare to tunneling probability

            tunNum += 1;
            gate += "NOT ";
            return true;


        } else {


            return false;


        }
    }


    // logic for and gate: Randomly see if the electron tunnels through two transistor barriers


    public Boolean andGateTunnel(Boolean bit1, Boolean bit0) {
//Arguments are false if the bit being operated upon is 0


        double randProb;
        if (bit1== false && bit0 == false) { // If both bits are set to 0 (False), the electron must tunnel through two transistor barriers to cause an error


            randProb = random.nextDouble()* random.nextDouble();
// Random probability for first barrier * random probability for 2nd barrier


        } else {
// If at least 1 bit is not 0, the electron only needs to tunnel through one barrier


            randProb = random.nextDouble();


        }


        if (Math.pow(tunProb,2)>= randProb) {


            tunNum += 1;
            gate += "AND ";
            return true;


        } else {


            return false;
        }
    }
}




/*


PAIR INTEGRATION NOTES


MY ROLE:


Support Class






ONE DECISION WE HAD TO AGREE ON:


Me and Ethan agreed that the support class would handle the logic for determining whether the electron tunneled or not, and store the number of tunnelings/gates tunneled through (quantities unique to each electron instantiation).


We also agreed that Ethan would call the the notGateTunnel and andGateTunnel depending on whether the electron was encountering the relevant gate, so there would be no choice logic between the two functions in the electron
 class.






ONE PROBLEM THAT APPEARED WHEN THE FILES MET:


Currently, Ethan has not finished the main file, and as such the files are not yet compatible. I am currenlty submitting my class file to avoid the deadline closing, but this will likely be modified and resubmitted in
 the future.






HOW WE SOLVED IT:


Currently, Ethan has not finished the main file, and as such the files are not yet compatible, so we haven't solved the issue of file integration yet.


Once again, I'm submitting my finished file alone to avoid the deadline closing, this will likely be resubmitted once me and Ethan get back in contact.






ONE THING I NOW UNDERSTAND BETTER ABOUT CLASSES OR OBJECTS:


*/





