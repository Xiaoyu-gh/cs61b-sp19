public class HelloNumbers {
	public static void main(String[] args) {
		int x = 0;
		int count = 0;
		while (x < 45) {
			x = x + count;
			System.out.print(x + " ");
			count = count + 1; 
		}
	}
}