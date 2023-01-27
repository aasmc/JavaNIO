## Java NIO Channels

Main interface of channels is:
```java
java.nio.channels.Channel
```

It has two methods: 
- void close() - this is an idempotent operation. 
- boolean isOpen()

To support I/O Channel interface is extended by the following interfaces:
```java
java.nio.channels.WritableByteChannel

java.nio.channels.ReadableByteChannel
```

A channel whose class implements only WritableByteChannel or
ReadableByteChannel is unidirectional. Attempting to read from a writable
byte channel or write to a readable byte channel results in a thrown exception.
You can use the instanceof operator to determine if a channel instance
implements either interface. Because it’s somewhat awkward to test for both
interfaces, Java supplies the ***java.nio.channels.ByteChannel*** interface,
which is an empty marker interface that subtypes ***WritableByteChannel*** and
***ReadableByteChannel***. When you need to learn whether or not a channel is
bidirectional, it’s more convenient to specify an expression such as channel
instanceof ByteChannel.

Channel is also extended by the ***java.nio.channels.InterruptibleChannel***
interface. InterruptibleChannel describes a channel that can be
asynchronously closed and interrupted.

### Scatter/Gather I/O

Channels provide the ability to perform a single I/O operation across multiple
buffers. This capability is known as scatter/gather I/O (and is also known as
vectored I/O).

In the context of a write operation, the contents of several buffers are
gathered (drained) in sequence and then sent through the channel to a
destination. These buffers are not required to have identical capacities. In
the context of a read operation, the contents of a channel are scattered
(filled) to multiple buffers in sequence; each buffer is filled to its limit until the
channel is empty or until the total buffer space is used.

Java provides the ***java.nio.channels.ScatteringByteChannel*** interface
to support scattering and the ***java.nio.channels.GatheringByteChannel***
interface to support gathering.

#### File Channels
FileInputStream
and FileOutputStream also provide the same method. In contrast,
java.io.FileReader and java.io.FileWriter don’t offer a way to obtain
a file channel.

***Caution***. The file channel returned from FileInputStream’s getChannel()
method is read-only, and the file channel returned from FileOutputStream’s
getChannel() method is write-only. Attempting to write to a read-only file
channel or read from a write-only file channel results in an exception.

The abstract java.nio.channels.FileChannel class describes a file channel.
Because this class implements the InterruptibleChannel interface, file
channels are interruptible. Because this class implements the ByteChannel,
GatheringByteChannel, and ScatteringByteChannel interfaces, you can write
to, read from, and perform scatter/gather I/O on underlying files.

### Note
Unlike buffers, which are not thread-safe, file channels are thread-safe.

## Mapping Files into Memory

FileChannel declares a map() method that lets you create a virtual memory
mapping between a region of an open file and a java.nio.MappedByteBuffer
instance that wraps itself around this region. This mapping mechanism
offers an efficient way to access a file because no time-consuming system
calls are needed to perform I/O.

## Socket Channels

Socket channels are described by the ***java.nio.channels*** package’s
abstract ServerSocketChannel, SocketChannel, and DatagramChannel
classes. Each class ultimately extends ***java.nio.channels.
SelectableChannel*** and implements InterruptibleChannel, making
ServerSocketChannel, SocketChannel, and DatagramChannel instances
selectable and interruptible. Because SocketChannel and DatagramChannel
implement the ByteChannel, GatheringByteChannel, and
ScatteringByteChannel interfaces, you can write to, read from, and perform
scatter/gather I/O on their underlying sockets.