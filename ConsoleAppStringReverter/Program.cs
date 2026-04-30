using System.Diagnostics;
using System.Runtime;

namespace ConsoleAppStringReverter;

class Program
{
    private static string StringReverter(string sentence)
    {
        string RevertedSentence = "";
        int SentenceLen = sentence.Length;

        for (int i = SentenceLen - 1; i >= 0; i--)
        {
            char c = sentence[i];
            RevertedSentence += c;
        }
        return RevertedSentence;
    }
    static void WriteMenu()
    {
        Console.WriteLine("0 - Salir.");
        Console.WriteLine("1 - String upside down.");
        Console.WriteLine("2 - FizzBuzz.");
        Console.WriteLine("3 - Tax Evader.");
        Console.WriteLine("4 - RPC.");
        Console.WriteLine("5 - Flip coin.");
        Console.WriteLine("6 - Progress Bar.");
        Console.WriteLine("7 - DB-Ops.");
    }

    static void Main(String[] args)
    {
        int opcion = 0;

        do
        {
            WriteMenu();
            Console.WriteLine("Opcion: ");
            opcion = Convert.ToInt16(Console.ReadLine());

            switch (opcion)
            {
                case 0:
                    Console.WriteLine("luv.");
                    break;
                case 1:
                    Console.WriteLine("Get yo's in: ");
                    string? wrd = Console.ReadLine();
                    string drw = StringReverter(wrd);
                    Console.WriteLine(drw);

                    break;
                case 2:
                    int limit = 101;

                    for (int i = 1; i < limit; i++)
                    {
                        if (i % 3 == 0 && i % 5 == 0)
                        {
                            Console.WriteLine("FizZBuzz");
                        }

                        else if (i % 5 == 0)
                        {
                            Console.WriteLine("Buzz");
                        }
                        else if (i % 3 == 0)
                        {
                            System.Console.WriteLine("Fizz");
                        }
                        else
                        {
                            Console.WriteLine(i);
                        }
                    }
                    break;
                case 3:
                    Console.WriteLine("Tax Percentage (Without %): ");
                    double tax = Convert.ToDouble(Console.ReadLine()) / 100;

                    Console.WriteLine("Total revnew: ");
                    double bucks = Convert.ToDouble(Console.ReadLine());

                    double realBucks = bucks - tax * bucks;

                    Console.WriteLine(realBucks);
                    break;
                case 4:

                    string[] values = ["rock", "paper", "cissors"];
                    string? userIn;

                    Random randomIdx = new Random();
                    int userPoint = 0, pcPoint = 0, idx;

                    do
                    {
                        Console.WriteLine("Whatcha givin? (<0> to exit) ");
                        userIn = Console.ReadLine();
                        idx = randomIdx.Next(values.Length);

                        if (userIn != null && userIn.Equals(values[idx]))
                        {
                            userPoint += 1;
                            Console.WriteLine("Got me...");
                        }
                        else if (userIn != null && userIn.Equals("0"))
                        {
                            Console.WriteLine("aight.");
                        }
                        else
                        {
                            pcPoint += 1;
                            Console.WriteLine("Try next time bud");
                        }
                    } while (userIn != null && !userIn.Equals("0"));

                    Console.WriteLine($"Quick summary: User got -> {userPoint} and Computar got -> {pcPoint}");
                    break;
                case 5:
                    Console.WriteLine("How many times: ");
                    int times = Convert.ToInt16(Console.ReadLine());
                    string[] coin = ["Tail", "Nail"];
                    Random random = new Random();

                    for (int i = 0; i < times; i++)
                    {
                        Console.WriteLine(coin[random.Next(coin.Length)]);
                    }
                    break;
                case 6:
                    Random random1 = new();
                    string[] progress = new string[20];
                    for (int i = 0; i < 20; i++)
                    {
                        progress[i] = "-";
                    }
                    Console.WriteLine($"Progress so far: ");
                    for (int j = 0; j < 20; j++)
                    {
                        progress[j] = "#";
                        Console.Write("\r" + string.Join("", progress));
                        Thread.Sleep(random1.Next(100, 1500));
                    }
                    Console.WriteLine();
                    Console.WriteLine("Di end.");
                    Console.Clear();
                    break;
                case 7:
                    ProgramDB.Princ();
                    break;
                default:
                    Console.WriteLine("Yo sure lad?");
                    break;
            }
        } while (opcion != 0);
    }
}

