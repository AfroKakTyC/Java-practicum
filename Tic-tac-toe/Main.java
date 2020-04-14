package tictactoe;
import java.util.Scanner;
public class Main {

    public static int checkWin(char[][] field, char ch)
    {
        int counter;
        int winNumber = 0;

        for (int i = 0; i < 3; i++)
        {
            counter = 0;
            for (int j = 0; j < 3; j++)
            {
                if (field[i][j] == ch)
                	counter++;
            }
            if (counter == 3)
            	winNumber++;
        }
		for (int i = 0; i < 3; i++)
		{
			counter = 0;
			for (int j = 0; j < 3; j++)
			{
				if (field[j][i] == ch)
					counter++;
			}
			if (counter == 3)
				winNumber++;
		}
        counter = 0;
        for (int i = 0; i < 3; i++)
		{
			if (field[i][i] == ch)
				counter++;
		}
		if (counter == 3)
			winNumber++;
		counter = 0;
		for (int i = 0; i < 3; i++)
		{
			if (field[i][2-i] == ch)
				counter++;
		}
		if (counter == 3)
			winNumber++;
        return (winNumber);
    }

    public static boolean checkEmpty(char[][] field)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (field[i][j] == '_')
					return (true);
			}
		}
		return (false);
	}

	public static int countChars(char[][] field, char ch)
	{
		int counter = 0;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (field[i][j] == ch)
					counter++;
			}
		}
		return (counter);
	}

    public static String checkCondition(char[][] field)
    {
        int winO = checkWin(field,'O');
        int winX = checkWin(field,'X');
		int oCount = countChars(field, 'O');
        int xCount = countChars(field, 'X');
        boolean isEmpty = checkEmpty(field);
		if (winX > 1 || winO > 1 || Math.abs(xCount - oCount) > 1 || (winO == 1 && winX == 1))
			return ("Impossible");
        if (winO == 0 && winX == 0)
        {
        	if (isEmpty)
				return ("Game not finished");
        	else
				return ("Draw");
		}
        if (winO == 1 && winX == 0)
			return ("O wins");
		if (winX == 1 && winO == 0)
			return ("X wins");

		return ("Error");
    }

    public static void drawField(char[][] field)
    {
        System.out.println("---------");
        for (int i = 0; i < 3; i++)
        {
            System.out.print("| ");
            for (int j = 0; j < 3; j++)
            {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] field = new char[3][3];

        //System.out.print("Input cells:");
        //String input = scanner.next();
		String input = "_________";
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                field[i][j] = input.charAt(i * 3 + j);
            }
        }
        drawField(field);
        //System.out.println(checkCondition(field));
		System.out.print("Enter the coordinates:");
		input = scanner.nextLine();
		boolean itsOk, firstPlayer, endGame;
		firstPlayer = true;
		endGame = false;
		do
		{
			itsOk = true;
			input = scanner.nextLine();
			String[] parseInput = input.split(" ");
			if (input.length() != 3)
			{
				System.out.println("Incorrect input!1");
				itsOk = false;
			}
			else if (parseInput[0].length() != 1 && parseInput[1].length() != 1)
			{
				System.out.println("Incorrect input!2");
				itsOk = false;
			}
			else if (!(parseInput[0].charAt(0) >= 48 && parseInput[0].charAt(0) <= 57)
				&& !(parseInput[1].charAt(0) >= 48 && parseInput[0].charAt(1) <= 57))
			{
				System.out.println("Incorrect input!3");
				itsOk = false;
			}
			else
			{
				int a = Integer.parseInt(parseInput[0]);
				int b = Integer.parseInt(parseInput[1]);
				if (!(a >= 0 && a <= 3 && b >= 0 && b <= 3))
				{
					System.out.println("Incorrect input!4");
					itsOk = false;
				}
				else if (field[3 - b][a-1] != '_')
				{
					System.out.println("This cell is occupied! Choose another one!");
					itsOk = false;
				}
				else
				{
					if (firstPlayer)
					{
						field[3 - b][a - 1] = 'X';
						firstPlayer = false;
					}
					else
					{
						field[3 - b][a - 1] = 'O';
						firstPlayer = true;
					}
					drawField(field);
					if (checkCondition(field).equals("Draw") ||
							checkCondition(field).equals("X wins") ||
							checkCondition(field).equals("O wins"))
					{
						System.out.println(checkCondition(field));
						endGame = true;
					}
				}
			}
		} while (!itsOk || !endGame);
    }
}
