using System;
using Grpc.Core;
using ObtainRecs;
using Grpc.Net.Client;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace ObtainRecsClient
{
    class Program
    {


        static async Task Main(string[] args)
        {

            using var channel = GrpcChannel.ForAddress("https://localhost:30051");
            var client = new Obtainrecs.ObtainRecsService.ObtainRecsServiceClient(channel);

            var reply = /*await*/ client.obtainRecsServerStreaming(
                    new global::Obtainrecs.ObtainRecsRequest
                    {
                        TrackId = 9,
                        SourceId = 99,
                        SourceUrl = "SourceUrl 9+99",
                        StartTimeT = 11111111,
                        EndTimeT = 99999999
                    });

            
            Console.WriteLine("Rec: " + reply);

            Console.WriteLine("Press any key to exit...");
            Console.ReadKey();
        }


        public static void _Main(string[] args)
        {
            Program.ObtainRecs();

            Console.WriteLine("Main: press any key to exit...");
            Console.ReadKey();
        }


        public static async Task<IAsyncResult> _ObtainRecs() 
        { 
            var channel = GrpcChannel.ForAddress("https://localhost:30051");
            var client = new Obtainrecs.ObtainRecsService.ObtainRecsServiceClient(channel);

            var response = /*await*/ client.obtainRecsServerStreaming(
                new global::Obtainrecs.ObtainRecsRequest
                {
                    TrackId = 9,
                    SourceId = 99,
                    SourceUrl = "SourceUrl 9+99",
                    StartTimeT = 11111111,
                    EndTimeT = 99999999
                });

            Console.WriteLine("ObtainRec: awainting for responses...");


            // When using C# 8 or later...
            await foreach (var _response in response.ResponseStream.ReadAllAsync())
            {
                Console.WriteLine("ObtainRec TrackSourceUrl: " + _response.TrackSourceUrl);
            }

            //
            // A server streaming call...
            //
            //while (await response.ResponseStream.MoveNext())
            //{
            //    Console.WriteLine("ObtainRec: " + response.ResponseStream.Current.TrackSourceUrl);
            //    // "Greeting: Hello World" is written multiple times
            //}


            //int i = 0;

            //Console.WriteLine("Response isComplete? " + response.ResponseHeadersAsync.IsCompleted);

            //while (!response.ResponseHeadersAsync.IsCompleted && i++ < 10)
            //{
            //    Console.Write(".");
            //    System.Threading.Thread.Sleep(500);
            //}

            //Console.WriteLine("Response: " + response.ToString());

            channel.ShutdownAsync().Wait();
            Console.WriteLine("Press any key to exit...");
            Console.ReadKey();









            /********************************************************************************************/
            //Channel channel = new Channel("127.0.0.1:30051", ChannelCredentials.Insecure);

            //var client = new Greeter.GreeterClient(channel);
            //String user = "you";

            //var reply = client.SayHello(new HelloRequest { Name = user });
            //Console.WriteLine("Greeting: " + reply.Message);

            //channel.ShutdownAsync().Wait();
            //Console.WriteLine("Press any key to exit...");
            //Console.ReadKey();
            /********************************************************************************************/


            return null;
        }
    }
}
