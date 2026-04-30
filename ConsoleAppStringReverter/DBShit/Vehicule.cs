namespace ConsoleAppStringReverter.DBShit;

public class Vehicule
{
    public int Id { get; set; }
    public string Make { get; set; }
    public string Model { get; set; }
    public string Type { get; set; }
    public int DealerId { get; set; }

    public Vehicule(int Vehicules_id, string make, string model, string type, int seller)
    {
        this.Id = Vehicules_id;
        this.Make = make;
        this.Model = model;
        this.Type = type;
        this.DealerId = seller;
    }
    public Vehicule(string make, string model, string type, int seller)
    {
        this.Make = make;
        this.Model = model;
        this.Type = type;
        this.DealerId = seller;
    }
    public Vehicule() { }
}
