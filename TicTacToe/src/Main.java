import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


    public static void main(String[] args) {

        char [][] gameBoard = {{' ', '|', ' ', '|', ' '},
                               {'-', '+', '-', '+', '-'},
                               {' ', '|', ' ', '|', ' '},
                               {'-', '+', '-', '+', '-'},
                               {' ', '|', ' ', '|', ' '}
                            };

        

        printGameBoard(gameBoard);

        playTicTacToe(gameBoard);


    } //END MAIN


    //PRIMARY GAME METHOD
    public static void playTicTacToe(char[][] gameBoard) {
        while (true) {
            Scanner scan = new Scanner(System.in);

        //Player Move
            System.out.println("Enter your placement (1-9): ");
            int playerPos = scan.nextInt();

            //Check for overlapping moves
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Space taken! Enter another position.");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");
            printGameBoard(gameBoard);

            if (winConditionMet()) break;


        //CPU Move
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            //Check for overlapping moves
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);

            if (winConditionMet()) break;

        }

        newGame();

    }


    private static void newGame() {
        System.out.println("Play Again? yes/no");

        Scanner scan = new Scanner(System.in);
        String again = scan.next();

        if (again.equals("yes")) {
            char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '}
            };

            playerPositions.clear();
            cpuPositions.clear();

            printGameBoard(gameBoard);
            playTicTacToe(gameBoard);
        }
    }

    public static boolean winConditionMet() {
        String result = checkWinner();
        if (result.length() > 0) {
            System.out.println(result);
            return true;
        }
        return false;
    }

    public static void printGameBoard(char [][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void placePiece(char [][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }


    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);

        List leftRightCross = Arrays.asList(1, 5, 9);
        List rightLeftCross = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
            winning.add(topRow);
            winning.add(middleRow);
            winning.add(bottomRow);
            winning.add(leftColumn);
            winning.add(middleColumn);
            winning.add(rightColumn);
            winning.add(leftRightCross);
            winning.add(rightLeftCross);

        for (List list : winning) {
            if (playerPositions.containsAll(list)) {
                return "Player Wins";
            } else if (cpuPositions.containsAll(list)) {
                return "CPU Wins";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Tie";
            }
        }

        return "";
    }


}
