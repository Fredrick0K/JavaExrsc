using Dapper;

namespace ConsoleAppStringReverter.DBShit
{

    public class DatabaseAccess
    {

        public static List<Vehicule> GetAllVehicules()
        {
            using (var conn = DatabaseConn.GetConnection())
            {
                string sql = "SELECT * FROM Vehicules";
                var list = conn.Query<Vehicule>(sql).ToList();
                return list;
            }
        }

        public static Vehicule GetVehiculeById(int id)
        {
            using (var conn = DatabaseConn.GetConnection())
            {
                string sql = "SELECT * FROM Vehicules WHERE Id = @Id";
                var vehicule = conn.QueryFirstOrDefault<Vehicule>(sql, new { Id = id });
                return vehicule;
            }
        }

        public static int InsertVehicule(Vehicule v)
        {
            using (var conn = DatabaseConn.GetConnection())
            {
                string sql = "INSERT INTO Vehicules(Make, Model, Type, DealerId) VALUES (@Make, @Model, @Type, @DealerId)";
                var execSql = conn.Execute(sql, new
                {
                    Make = v.Make,
                    Model = v.Model,
                    Type = v.Type,
                    DealerId = v.DealerId
                });
                return execSql;
            }
        }

        public static int UpdateVehicule(Vehicule v)
        {
            using (var conn = DatabaseConn.GetConnection())
            {
                string sql = "UPDATE Vehicules SET " +
                "Make = @Make, " +
                "Model = @Model, " +
                "Type = @Type, " +
                "DealerId = @DealerId " +
                "WHERE Id = @Id";

                var execSql = conn.Execute(sql, new
                {
                    Make = v.Make,
                    Model = v.Model,
                    Type = v.Type,
                    DealerId = v.DealerId,
                    Id = v.Id
                });
                return execSql;
            }
        }

        public static int DeleteVehicule(int id)
        {
            using (var conn = DatabaseConn.GetConnection())
            {
                string sql = "DELETE FROM Vehicules WHERE Id = @Id";
                var execSql = conn.Execute(sql, new { Id = id });
                return execSql;
            }
        }
    }
}