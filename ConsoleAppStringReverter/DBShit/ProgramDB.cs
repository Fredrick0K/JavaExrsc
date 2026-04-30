using ConsoleAppStringReverter.DBShit;

namespace ConsoleAppStringReverter;

class ProgramDB
{
    private static void WriteMenu()
    {
        Console.WriteLine("0 - salir.");
        Console.WriteLine("1 - Vehicules.");
        Console.WriteLine("2 - Vehicule By ID.");
        Console.WriteLine("3 - Insert Vehicule.");
        Console.WriteLine("4 - Update Vehicule.");
        Console.WriteLine("5 - Delete vehicule.");

    }

    private static void WriteObject(Vehicule v, int i)
    {
        Console.WriteLine("-----------Vehiculo {0}-----------", i);
        Console.WriteLine("ID: " + v.Id);
        Console.WriteLine("Marca: " + v.Make);
        Console.WriteLine("Modelo: " + v.Model);
        Console.WriteLine("Tipo: " + v.Type);
        Console.WriteLine("Id Concesionario: " + v.DealerId);
        Console.WriteLine("----------------------------------");
    }

    public static Vehicule RequestObject(bool needId)
    {
        Vehicule v = null;
        if (needId)
        {
            Console.WriteLine("ID: ");
            int id = Convert.ToInt16(Console.ReadLine());
            Console.WriteLine("Make: ");
            string make = Console.ReadLine();
            Console.WriteLine("Model: ");
            string model = Console.ReadLine();
            Console.WriteLine("Type: ");
            string type = Console.ReadLine();
            Console.WriteLine("Dealer ID: ");
            int dealerId = Convert.ToInt16(Console.ReadLine());

            v = new Vehicule(id, make, model, type, dealerId);
        }
        else
        {
            Console.WriteLine("Make: ");
            string make = Console.ReadLine();
            Console.WriteLine("Model: ");
            string model = Console.ReadLine();
            Console.WriteLine("Type: ");
            string type = Console.ReadLine();
            Console.WriteLine("Dealer ID: ");
            int dealerId = Convert.ToInt16(Console.ReadLine());

            v = new Vehicule(make, model, type, dealerId);
        }
        return v;
    }

    public static void Princ()
    {
        int opt;

        do
        {
            WriteMenu();
            opt = Convert.ToInt16(Console.ReadLine());
            switch (opt)
            {
                case 0:
                    Console.WriteLine("cya");
                    break;
                case 1:
                    var list = DatabaseAccess.GetAllVehicules();
                    int i = 1;
                    foreach (var v in list)
                    {
                        i++;
                        WriteObject(v, i);
                    }
                    break;
                case 2:
                    Console.WriteLine("ID: ");
                    int id = Convert.ToInt16(Console.ReadLine());
                    var vehicule = DatabaseAccess.GetVehiculeById(id);
                    if (vehicule != null)
                    {
                        WriteObject(vehicule, id);
                    }
                    else
                    {
                        Console.WriteLine("No se ha encontrado el vehiculo con ID: " + id);
                    }
                    break;
                case 3:
                    Vehicule v = RequestObject(false);
                    if(DatabaseAccess.InsertVehicule(v) > 0)
                    {
                        Console.WriteLine("Inserted!");
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        } while (opt != 0);
    }
}