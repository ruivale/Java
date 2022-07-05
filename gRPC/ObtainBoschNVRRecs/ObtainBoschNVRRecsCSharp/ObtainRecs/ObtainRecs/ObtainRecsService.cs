using Obtainrecs;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using grpc = global::Grpc.Core;

namespace ObtainRecs
{
    public class ObtainRecsService : Obtainrecs.ObtainRecsService.ObtainRecsServiceBase
    {
        public ObtainRecsService()
        {
            
        }

        public override global::System.Threading.Tasks.Task obtainRecsServerStreaming(
                global::Obtainrecs.ObtainRecsRequest request, 
                grpc::IServerStreamWriter<global::Obtainrecs.ObtainRecsResponse> responseStream, 
                grpc::ServerCallContext context)
        {

            Console.WriteLine("ObtainRecsService.obtainRecsServerStreaming(trackId: "+request.TrackId+", ..): returning stuff...");

            return Task.FromResult(new ObtainRecsResponse
            {
                TrackId = request.TrackId,
                TrackName = "TrackName",
                TrackSourceId = 1,
                TrackSourceUrl = "TrackSourceUrl",
                NvrId = 1,
                SourceId = request.SourceId,
                SourceName = "SourceName",
                SourceUrl = request.SourceUrl,
                StartTimeT = request.StartTimeT,
                EndTimeT   = request.EndTimeT
            });

        }
    }
}
