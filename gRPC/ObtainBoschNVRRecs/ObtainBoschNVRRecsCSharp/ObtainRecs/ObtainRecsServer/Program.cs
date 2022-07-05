using System;
using System.Threading.Tasks;
using Grpc.Core;
using ObtainRecs;
//using Obtainrecs;


namespace ObtainRecsServer
{
    class Program
    {
        const int Port = 30051;

        public static void Main(string[] args)
        {
            Server server = new Server
            {
                Services = { Obtainrecs.ObtainRecsService.BindService(new ObtainRecsService()) },
                Ports = { new ServerPort("localhost", Port, ServerCredentials.Insecure) }
            };
            server.Start();

            Console.WriteLine("ObtainRecs server listening on port " + Port);
            Console.WriteLine("Press any key to stop the server...");
            Console.ReadKey();

            server.ShutdownAsync().Wait();
        }
    }
}
