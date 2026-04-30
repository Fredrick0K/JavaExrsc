using System.Data.SqlClient;

namespace ConsoleAppStringReverter.DBShit
{

    public class DatabaseConn
    {
        public static string ConnectionString
        {
            get
            {
                string connString = @"Data Source = PF2RN6ZQ\SQLEXPRESS; Initial Catalog = Dealership; Integrated Security = True; Connect Timeout = 30;";
                return connString;
            }
        }

        public static SqlConnection GetConnection()
        {
            var conn = new SqlConnection(ConnectionString);
            conn.Open();
            return conn;
        }
    }
}