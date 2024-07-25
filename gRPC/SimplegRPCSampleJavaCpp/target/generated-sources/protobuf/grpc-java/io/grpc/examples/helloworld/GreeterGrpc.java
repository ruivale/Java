package io.grpc.examples.helloworld;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.65.0)",
    comments = "Source: helloworld.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final java.lang.String SERVICE_NAME = "helloworld.Greeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.helloworld.HelloRequest,
      io.grpc.examples.helloworld.HelloReply> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = io.grpc.examples.helloworld.HelloRequest.class,
      responseType = io.grpc.examples.helloworld.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.helloworld.HelloRequest,
      io.grpc.examples.helloworld.HelloReply> getSayHelloMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.helloworld.HelloRequest, io.grpc.examples.helloworld.HelloReply> getSayHelloMethod;
    if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
          GreeterGrpc.getSayHelloMethod = getSayHelloMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.helloworld.HelloRequest, io.grpc.examples.helloworld.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.helloworld.HelloReply.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayHello"))
              .build();
        }
      }
    }
    return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.helloworld.EmptyRequest,
      io.grpc.examples.helloworld.GoodbyeReply> getSayGoodbyeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayGoodbye",
      requestType = io.grpc.examples.helloworld.EmptyRequest.class,
      responseType = io.grpc.examples.helloworld.GoodbyeReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.helloworld.EmptyRequest,
      io.grpc.examples.helloworld.GoodbyeReply> getSayGoodbyeMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.helloworld.EmptyRequest, io.grpc.examples.helloworld.GoodbyeReply> getSayGoodbyeMethod;
    if ((getSayGoodbyeMethod = GreeterGrpc.getSayGoodbyeMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSayGoodbyeMethod = GreeterGrpc.getSayGoodbyeMethod) == null) {
          GreeterGrpc.getSayGoodbyeMethod = getSayGoodbyeMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.helloworld.EmptyRequest, io.grpc.examples.helloworld.GoodbyeReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayGoodbye"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.helloworld.EmptyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.helloworld.GoodbyeReply.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayGoodbye"))
              .build();
        }
      }
    }
    return getSayGoodbyeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterStub>() {
        @java.lang.Override
        public GreeterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterStub(channel, callOptions);
        }
      };
    return GreeterStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterBlockingStub>() {
        @java.lang.Override
        public GreeterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterBlockingStub(channel, callOptions);
        }
      };
    return GreeterBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterFutureStub>() {
        @java.lang.Override
        public GreeterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterFutureStub(channel, callOptions);
        }
      };
    return GreeterFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    default void sayHello(io.grpc.examples.helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     * <pre>
     * 
     * </pre>
     */
    default void sayGoodbye(io.grpc.examples.helloworld.EmptyRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.GoodbyeReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSayGoodbyeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Greeter.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class GreeterImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GreeterGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Greeter.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class GreeterStub
      extends io.grpc.stub.AbstractAsyncStub<GreeterStub> {
    private GreeterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void sayHello(io.grpc.examples.helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 
     * </pre>
     */
    public void sayGoodbye(io.grpc.examples.helloworld.EmptyRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.GoodbyeReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSayGoodbyeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Greeter.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class GreeterBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GreeterBlockingStub> {
    private GreeterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public io.grpc.examples.helloworld.HelloReply sayHello(io.grpc.examples.helloworld.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 
     * </pre>
     */
    public io.grpc.examples.helloworld.GoodbyeReply sayGoodbye(io.grpc.examples.helloworld.EmptyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSayGoodbyeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Greeter.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class GreeterFutureStub
      extends io.grpc.stub.AbstractFutureStub<GreeterFutureStub> {
    private GreeterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.helloworld.HelloReply> sayHello(
        io.grpc.examples.helloworld.HelloRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.helloworld.GoodbyeReply> sayGoodbye(
        io.grpc.examples.helloworld.EmptyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSayGoodbyeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SAY_GOODBYE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((io.grpc.examples.helloworld.HelloRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloReply>) responseObserver);
          break;
        case METHODID_SAY_GOODBYE:
          serviceImpl.sayGoodbye((io.grpc.examples.helloworld.EmptyRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.GoodbyeReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSayHelloMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.grpc.examples.helloworld.HelloRequest,
              io.grpc.examples.helloworld.HelloReply>(
                service, METHODID_SAY_HELLO)))
        .addMethod(
          getSayGoodbyeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.grpc.examples.helloworld.EmptyRequest,
              io.grpc.examples.helloworld.GoodbyeReply>(
                service, METHODID_SAY_GOODBYE)))
        .build();
  }

  private static abstract class GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.examples.helloworld.HelloWorldProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeter");
    }
  }

  private static final class GreeterFileDescriptorSupplier
      extends GreeterBaseDescriptorSupplier {
    GreeterFileDescriptorSupplier() {}
  }

  private static final class GreeterMethodDescriptorSupplier
      extends GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GreeterMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getSayGoodbyeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
