
public class Hexadecimal
{
    private int _decNum;
    private String _binNum;
    private final static String HEXDIGITS = "0123456789ABCDEF";

    public Hexadecimal()
    {
        _decNum = 0;
        _binNum = "0";
    }

    public Hexadecimal(int n)
    {
        _decNum = n;
        _binNum = decToBin(n);
    }

    public Hexadecimal(String s)
    {
        _decNum = binToDec(s);
        _binNum = s;
    }

    public String toString()
    {
        return _binNum;
    }

    public static String decToBin(int n)
    {
        String ret = "";
        while (n > 0)
	    {
		ret = HEXDIGITS.substring(n%16, (n%16)+1) + ret;
		//ret is the hexdigit whose index corresponds with the remainder of n%16
		n /= 16;
		//n divided by 16 each time until it reaches 0
	    }
        return ret;
    }
 

    public static String decToBinR(int n)
    {
        if (n == 0) return "";
        return decToBinR(n/16) + HEXDIGITS.substring(n%16, (n%16)+1);
	//n divided by 16 goes into each recursion until it reaches 0
	//adds on the hexdigit that corresponds with the remainder of n%16
    }


    public static int binToDec( String s ) {
	int dec = 0;
	int power = 0;
	for (int ctr = 0; ctr < s.length(); ctr ++ ) {
	    power = s.length() - ctr - 1;
	    //ctr and power increment at opposite ends
	    dec += (HEXDIGITS.indexOf(s.substring(ctr,ctr+1))
		    //goes through each char in string s
		    //returns corresponding index of char in string HEXDIGITS
		    * Math.pow(16, power));//number multiplied by 16 raised to th correct power
	}
	return dec;
    }

    public static int binToDecR( String s ) { 
	int dec = 0;
	int power = 0;
	if (s.length() == 0) {
	    return -1; //in case String s is empty
	}    
	else if (s.length() == 1) {//base case
	    dec += (HEXDIGITS.indexOf(s.substring(0))//Steps through String
		    //returns corresponding index in HEXDIGITS
		    * Math.pow(16, power));//multiplied by 16 to correct power
	    return dec;
	}
	else {
	    power = s.length() -1; //power is one less than length because...
	    return binToDecR(s.substring(1))
		//first element of s sliced off with each recursion
		+(HEXDIGITS.indexOf(s.substring(0,1))
		  //multiplies the first int in s by
		  *(int)(Math.pow(16, power)));//16 raised to correct power
	}
    }

    public boolean equals(Object other)
    {
        if (!(other instanceof Hexadecimal))
            return false;
        return compareTo((Hexadecimal)other) == 0;
    }

 
    public int compareTo(Hexadecimal other)
    {
        if (!(other instanceof Hexadecimal))
            throw new ClassCastException("compareTo() input not a Binary");
        if (_decNum == other._decNum) return 0;
        if (_decNum <  other._decNum) return -1;
        return 1;
    }

 
    public static void main(String[] args)

    {
	System.out.println(decToBin(6780)); //should be 1A7C
	System.out.println(decToBin(113717409)); //should be 6C730A1
	System.out.println(decToBinR(6780)); //should be 1A7C
	System.out.println(decToBinR(113717409));// should be 6C730A1

	System.out.println(binToDec("1A7C")); //should be 6780
	System.out.println(binToDec("6C730A1")); //should be 113717409

	System.out.println(binToDecR("1A7C")); //should be 6780
	System.out.println(binToDecR("6C730A1")); //should be 113717409

	Hexadecimal b1 = new Hexadecimal(5);
        Hexadecimal b2 = new Hexadecimal(5);
        Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(7);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos


    }
}
