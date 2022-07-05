
using System;
using Grpc.Core;
using Simplecctvrecs;

/// <summary>
/// 
/// </summary>
namespace SimpleCCTVRecordingsClient
{
    /// <summary>
    /// 
    /// </summary>
    class Program
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="args"></param>
        public static void Main(string[] args)
        {
            Channel channel = new Channel("127.0.0.1:30051", ChannelCredentials.Insecure);

            var client = new Simplecctvrecs.ObtainRecsService.ObtainRecsServiceClient(channel);

            var reply = client.ObtainRec(new RecsRequest {
                TrackId = 111,
                SourceId = 1,
                SourceUrl = "SourceUrlFromClient",
                StartTimeT = 1111111111,
                EndTimeT = 9999999999
            });
            
            Console.WriteLine("\nRec[" +
                "TrackId:" + reply.TrackId +
                " SrcId:" + reply.SourceId +
                " SrcUrl:" + reply.SourceUrl +
                " [" + reply.StartTimeT +
                "->" + reply.EndTimeT +
                "] Nvr:" + reply.NvrId +
                " Track[Name:" + reply.TrackName +
                " SrcId:" + reply.TrackSourceId +
                " SrcUrl:" + reply.TrackSourceUrl +
                "] SrcName:" + reply.SourceName +
                "]\n");

            channel.ShutdownAsync().Wait();
            Console.WriteLine("Press any key to exit...");
            Console.ReadKey();
        }
    }
}