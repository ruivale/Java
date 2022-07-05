package jdk1_6examples.nio2;

import java.io.*;
import java.net.*;
import java.nio.channels.*;


/**
 * <p>Classname: </p>
 *
 * <p>Description:
 *
 *
 Multicasting with NIO

 A long standing issue for many developers is that the java.nio.channels package
 has lacked support for Internet Protocol (IP) multicasting. In the NIO.2 early
 review draft specification you will see that we have addressed this issue by
 adding multicast support to DatagramChannel. Here's a small example that opens
 a DatagramChannel, binds the channel's socket to a local port, sets the network
 interface for multicast datagrams sent via the channel, and then joins a
 multicast group on the same interface:

 NetworkInterface interf = NetworkInterface.getByName("eth0");
 InetAddress group = InetAddress.getByName("225.0.0.100");

 DatagramChannel dc = DatagramChannel.open(ProtocolFamily.INET)
     .setOption(SocketOption.SO_REUSEADDR, true)
     .bind(new InetSocketAddress(5000))
     .setOption(SocketOption.IP_MULTICAST_IF, interf);

 MembershipKey key = dc.join(group, interf);

 Once the channel has joined the group then it reads or receives multicast
 datagrams in the same manner that it reads or receives unicast datagrams.

 The most significant thing in this example is the static factory method to
 open the channel specifies a protocol family. In this example, the channel
 is to an IPv4 socket. In the existing APIs, the protocol family is transparent
 and all sockets created by the java.net or java.nio.channels packages are
 either all IPv4 or all IPv6. For IP multicasting it is important that the
 protocol family corresponds to the address type of the multicast groups that
 the socket joins; otherwise it is highly operating system specific if the
 socket can join the group, configure options, or receive multicast datagrams.
 Legacy java.net.MulticastSocket has suffered greatly from problems in this area.
 Another interesting thing to point out is that the channel's socket is bound
 and socket options are configured directly. The awkward and counterintuitive
 socket adaptor isn't required so it isn't necessary to mix java.net socket
 APIs when configuring the channel's socket.

 In the example, the MembershipKey that is returned by the join method is a
 token to represent membership of the group. It defines methods to query
 information about the membership and defines the "drop" method to drop
 membership of the group.

 Developers tracking multicast standards will know that the RFCs in this area
 have been updated in recent years to add source filtering and this is now
 supported by almost all modern operating systems. In the NIO.2 draft
 specification we have included basic support for source filtering. The
 following code fragment shows a channel joining a multicast group to only
 receive multicast datagrams sent by a specific IP source address (otherwise
 known as "include-mode" filtering):

 MembershipKey key = dc.join(group, interf, source);

 "Exclusive-mode" filtering is where a group is joined to receive all
 multicast datagrams except those from specific IP source address:

 MembershipKey key = dc.join(group, interf).block(source1).block(source2);

 So that's a brief introduction to the multicast support that we propose to
 add to the java.nio.channels package. There's a lot more detail in the draft
 specification for those interested in this topic.
 ( Apr 28 2007, 01:46:02 PM PDT ) Permalink Comments [4]
 Trackback URL: http://blogs.sun.com/alanb/entry/multicasting_with_nio
 Comments:
 *
 *
 *
 *
 * </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
public class Multicasting {
  public Multicasting() {
    /***
    NetworkInterface interf = NetworkInterface.getByName("eth0");
    InetAddress group = InetAddress.getByName("225.0.0.100");

    DatagramChannel dc = DatagramChannel.open(ProtocolFamily.INET).setOption(
        SocketOption.SO_REUSEADDR, true).bind(new InetSocketAddress(5000)).
        setOption(SocketOptions.IP_MULTICAST_IF, interf);

    MembershipKey key = dc.join(group, interf);
    /**/
  }
}
