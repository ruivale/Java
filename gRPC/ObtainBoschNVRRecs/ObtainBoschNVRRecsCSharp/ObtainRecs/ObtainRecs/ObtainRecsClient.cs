using Grpc.Net.Client;
using System;
using System.Collections.Generic;
using System.Text;

namespace ObtainRecs
{
    class ObtainRecsClient
    {
        static void Main(string[] args)
        {
            ObtainRecs();
        }

        static /*async*/ void ObtainRecs()
        {
            var channel = GrpcChannel.ForAddress("https://localhost:8080");
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

            Console.WriteLine(response);

        }
    }
}
