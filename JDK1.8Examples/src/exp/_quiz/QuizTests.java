
package exp._quiz;


import java.math.BigInteger;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**

What is the problem with the following code?
public static BigInteger parsePriceTotalOfProducts(final String input) {
	//example input: 'bought 9 products for 120 per piece'
	//this method assumes that prices are input as integers, so '1265' instead of '1.265,00'
	Pattern pattern = Pattern.compile("^.*?(\d+).+?(\d+).*$");
	Matcher matcher = pattern.matcher(input);
	int number = Integer.parseInt(matcher.group(1));
	int costPerProduct = Integer.parseInt(matcher.group(2));
	return BigInteger.valueOf((long)number * costPerProduct);
}
The matcher.group calls use 1 and 2, instead of 0 and 1
Nothing
matcher.find() is not called
The regular expression is incorrect



----------------------------------------------------------------------------------------------------------------
What does the function [ callMe(...) ] do?
public int callMe( int limit ) {
	return ( limit <= 0 ) ? 0 : callMeBaby( 0, 1, limit );
}
private int callMeBaby( int n1, int n2, int limit ) {
	return ( limit != 0 ) ? callMeBaby( n2, n1 + n2, limit - 1 ) : n1;
}

Get the nth(limit) number in fibonacci
Infinite loop
Doesn't compile
Multiplication without using [*] operator



----------------------------------------------------------------------------------------------------------------
What will this program print?
public class MyClass {
	private String name;
	public static void main(String args[]) {
		MyClass m1 = new MyClass();
		MyClass m2 = new MyClass();

		m1.name = m2.name = "m1";

		callMe( m1, m2 );
		System.out.println( m1 + " & " + m2 );
	}
	private static void callMe( MyClass... m ) {
		m[0] = m[1];
		m[1].name = "new name";
	}
}

None of the above
m1 & m1
new name & new name
m1 & new name



----------------------------------------------------------------------------------------------------------------
What is the problem with the following code?
public static getOldestPerson(Collection<Person> people) {
	if (people.isEmpty()) {
		return null;
	}

	Person oldest;

	for (Person p : people) {
		if ((oldest == null) || (p.olderThan(oldest)) {
			oldest = p;
		}
	}

	return oldest;
}

Nothing
Doesn't do what it's supposed to do
Doesn't compile
Potential null access



----------------------------------------------------------------------------------------------------------------
What is the problem with the following code?
public static int getIPv4BinaryRepresentation(final String ipv4) {
	Matcher matcher = Pattern.compile("(d+).(d+).(d+).(d+)").matcher(ipv4);
	if (!matcher.find()) {
		throw new IllegalArgumentException("Not an IPv4 address: " + ipv4);
	}
	int p1 = Integer.parseInt(matcher.group(1));
	int p2 = Integer.parseInt(matcher.group(2));
	int p3 = Integer.parseInt(matcher.group(3));
	int p4 = Integer.parseInt(matcher.group(4));
	if (p1 < 0 || p1 > 255 || p2 < 0 || p2 > 255 || p3 < 0 || p3 > 255 || p4 < 0 || p4 > 255) {
		throw new IllegalArgumentException("Not an IPv4 address: " + ipv4);
	}
	return ((p1 << 24) | (p2 << 16) | (p3 << 8) | p4);
}

The bitshifting calculation is incorrect
In matcher.group() groups 1 through 4 are read instead of 0 through 3
Nothing
The regular expression is wrong



----------------------------------------------------------------------------------------------------------------
What would be the output of the following code snippet?
Map<String, Object> collection = new TreeMap<>();
System.out.println( collection.compute( "foo", (k, v) -> (v == null) ? new ArrayList<Object>() : ((List)v).add( "bar" ) ) );
System.out.println( collection.compute( "foo", (k, v) -> (v == null) ? new ArrayList<Object>() : ((List)v).add( "ber" ) ) );

["bar", "ber"]
[] true
ClassCastException
Doesn't compile



----------------------------------------------------------------------------------------------------------------



 */
public class QuizTests {

  /**
   * What is the problem with the following code?
   *    The matcher.group calls use 1 and 2, instead of 0 and 1
   *    Nothing
   *    matcher.find() is not called
   *    The regular expression is incorrect

   * @param input
   * @return
   */
  public static BigInteger parsePriceTotalOfProducts() {
	final String input = "bought 9 products for 120 per piece";
	//this method assumes that prices are input as integers, so '1265' instead of '1.265,00'
	Pattern pattern = Pattern.compile("^.*?(\\d+).+?(\\d+).*$");
	Matcher matcher = pattern.matcher(input);
	int number = Integer.parseInt(matcher.group(1));
	int costPerProduct = Integer.parseInt(matcher.group(2));
	return BigInteger.valueOf((long)number * costPerProduct);
  }




  public static void main(final String[] args){
    QuizTests.parsePriceTotalOfProducts();

  }
}
