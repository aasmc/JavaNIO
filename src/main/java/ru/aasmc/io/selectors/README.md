# Selectors

A selector is an object created from a subclass of the abstract java.nio.
channels.Selector class. The selector maintains a set of channels that it
examines to determine which channels are ready for reading, writing,
completing a connection sequence, accepting another connection, or some
combination of these tasks. The actual work is delegated to the operating
system via a POSIX select() or similar system call.

Selectors are used with selectable channels, which are objects whose classes
ultimately inherit from the abstract java.nio.channels.SelectableChannel
class, which describes a channel that can be multiplexed by a selector.
Socket channels, server socket channels, datagram channels, and pipe
source/sink channels are selectable channels because java.nio.channels.
SocketChannel, java.nio.channels.ServerSocketChannel, java.nio.
channels.DatagramChannel, java.nio.channels.Pipe.SinkChannel, and
java.nio.channels.Pipe.SourceChannel are derived from SelectableChannel.
In contrast, file channels are not selectable channels because java.nio.
channels.FileChannel doesn’t include SelectableChannel in its ancestry.

One or more previously created selectable channels are registered with a
selector. Each registration returns an instance of a subclass of the abstract
SelectionKey class, which is a token signifying the relationship between one
channel and the selector. This key keeps track of two sets of operations:
interest set and ready set. The interest set identifies the operation categories
that will be tested for readiness the next time one of the selector’s selection
methods is invoked. The ready set identifies the operation categories for
which the key’s channel has been found to be ready by the key’s selector.

You can create your selectable channels before or after creating your
selector. However, you must ensure that each channel is in nonblocking
mode before registering the channel with the selector.

