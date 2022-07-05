
using System;
using System.Threading.Tasks;
using Grpc.Core;
using Simplecctvrecs;


/// <summary>
/// 
/// </summary>
namespace SimpleCCTVRecordingsServer
{
    /// <summary>
    /// 
    /// </summary>
    class ObtainRecsServiceImpl : Simplecctvrecs.ObtainRecsService.ObtainRecsServiceBase
    {
        /// <summary>
        /// Server side handler 
        /// </summary>
        /// <param name="request"></param>
        /// <param name="context"></param>
        /// <returns></returns>

        public override Task<RecsReply> ObtainRec(RecsRequest request, ServerCallContext context)
        {
            return Task.FromResult(new RecsReply
            {
                TrackId = request.TrackId,
                SourceId = request.SourceId,
                SourceUrl = request.SourceUrl,
                StartTimeT = request.StartTimeT,
                EndTimeT = request.EndTimeT,

                NvrId = 666,
                TrackName = "TrackNameFromServer",
                TrackSourceId = 66,
                TrackSourceUrl = "TrackSourceUrlFromServer",
                SourceName = "SourceNameFromServer"

            });
        }
    }

    /// <summary>
    /// 
    /// </summary>
    class Program
    {
        const int Port = 30051;

        /// <summary>
        /// 
        /// </summary>
        /// <param name="args"></param>
        public static void Main(string[] args)
        {
            Server server = new Server
            {
                Services = { Simplecctvrecs.ObtainRecsService.BindService(new ObtainRecsServiceImpl()) },
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