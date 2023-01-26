# NIO Buffers

Buffers possess four properties:
- Capacity: the total number data items that can be
  stored in the buffer. The capacity is specified when the
  buffer is created and cannot be changed later.
- Limit: he zero-based index of the first element that
  should not be read or written. In other words, it identifies
  the number of “live” data items in the buffer.
- Position: The zero-based index of the next data item
  that can be read or the location where the data item can
  be written.
- Mark: A zero-based index to which the buffer’s
  position will be reset when the buffer’s reset() method
  (presented shortly) is called. The mark is initially
  undefined.

These four properties are related as follows: **0 <= mark <= position <= limit <= capacity**.

### Hierarchy:
- Buffer
  - ByteBuffer
    - MappedByteBuffer
  - CharBuffer
  - DoubleBuffer
  - FloatBuffer
  - IntBuffer
  - LongBuffer
  - ShortBuffer


### Flipping Buffers
After filling a buffer, you must prepare it for draining by a channel. When
you pass the buffer as is, the channel accesses undefined data beyond the
current position.

To solve this problem, you could reset the position to 0, but how would the
channel know when the end of the inserted data had been reached? The
solution is to work with the limit property, which indicates the end of the
active portion of the buffer. Basically, you set the limit to the current position
and then reset the current position to 0.

```java
buffer.limit(buffer.position()).position(0)
// OR JUST
buffer.flip()
```

#### Direct Byte Buffers
A direct byte buffer is a byte buffer that interacts with channels and native
code to perform I/O. The direct byte buffer attempts to store byte elements in
a memory area that a channel uses to perform direct (raw) access via native
code that tells the operating system to drain or fill the memory area directly.

Direct byte buffers are the most efficient means for performing I/O on the
JVM. Although you can also pass nondirect byte buffers to channels, a
performance problem might arise because nondirect byte buffers are not
always able to serve as the target of native I/O operations.

When passed a nondirect byte buffer, a channel might have to create a
temporary direct byte buffer, copy the nondirect byte buffer’s content to the
direct byte buffer, perform the I/O operation on the temporary direct byte
buffer, and copy the temporary direct byte buffer’s content to the nondirect
byte buffer. The temporary direct byte buffer will then be subject to garbage
collection.