package Maps;

import java.util.Scanner;

import estructuras.AVL;
import Maps.PathMatrix;

public class PruebaAVL {
    public static void main(String[] args) {

        Scanner scanner;
        scanner = new Scanner(System.in);
        int number;
        //BST tree = new BST();
        AVL<Partida> tree = new AVL();
        System.out.println();
        System.out.print("Enter a positive integer to insert ");
        System.out.print("or a negative integer to stop: ");
        number = scanner.nextInt();
        while(number >= 0) {
            Partida part = new Partida(number);
            tree.insert(part);
            System.out.println("root: " + tree.getRoot().getKey());
            tree.bfs(tree.getRoot());
            System.out.println();
            System.out.print("Enter a positive integer to insert ");
            System.out.print("or a negative integer to stop: ");
            number = scanner.nextInt();
        }
        System.out.println();
        System.out.print("Enter a positive integer to next ");
        System.out.print("or a negative integer to quit: ");
        number = scanner.nextInt();
        while(number >= 0) {
            Partida part = new Partida(number);
            tree.next(part);
            System.out.print("Enter a positive integer to next ");
            System.out.print("or a negative integer to quit: ");
            number = scanner.nextInt();
        }
        System.out.println();
        System.out.print("Enter two positive integer to rangeSearch (numer 1 > number 2)");
        System.out.println("or a negative integer to quit: ");
        System.out.print("number 1: ");
        int number1 = scanner.nextInt();
        System.out.print("number 2: ");
        int number2 = scanner.nextInt();
        while(number1 >= 0 && number2 >= 0) {
            Partida part1 = new Partida(number1);
            Partida part2 = new Partida(number2);
            tree.rangePrint(part1, part2);
            System.out.print("Enter two positive integer to rangeSearch (number 1 > number 2)");
            System.out.println("or a negative integer to quit: ");
            System.out.print("number 1: ");
            number1 = scanner.nextInt();
            System.out.print("number 2: ");
            number2 = scanner.nextInt();
        }

        System.out.println();
        System.out.print("Enter a positive integer to remove ");
        System.out.print("or a negative integer to quit: ");
        number = scanner.nextInt();
        while(number >= 0) {
            Partida part = new Partida(number);
            tree.remove(part);
            tree.bfs(tree.getRoot());
            System.out.println();
            System.out.print("Enter a positive integer to remove ");
            System.out.print("or a negative integer to quit: ");
            number = scanner.nextInt();
        }
        System.out.println();
        System.out.print("Enter a positive integer to searchNode ");
        System.out.print("or a negative integer to quit: ");
        number = scanner.nextInt();
        while(number >= 0) {
            Partida part = new Partida(number);
            System.out.println(tree.isNode(part));
            //tree.inOrder(tree.getRoot());
            System.out.println();
            System.out.print("Enter a positive integer to searchNode ");
            System.out.print("or a negative integer to quit: ");
            number = scanner.nextInt();
        }
        System.out.println();
        System.out.println("End of Program");
        System.out.println();
    }
}
