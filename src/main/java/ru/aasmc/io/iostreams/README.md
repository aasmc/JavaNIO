# IO Streams Hierarchy
### OutputStream
- ByteArrayOutputStream
- FileOutputStream
- FilterOutputStream
  - BufferedOutputStream
  - DataOutputStream
  - PrintStream (should use PrintWriter instead)
- ObjectOutputStream
- PipedOutputStream

### InputStream
- ByteArrayInputStream
- FileInputStream
- FilterInputStream
  - BufferedInputStream
  - DataInputStream
  - LineNumberInputStream (Deprecated) use LineNumberReader
  - PushbackInputStream
- ObjectInputStream
- PipedInputStream
- SequenceInputStream
- StringBufferInputStream (Deprecated) use StringReader


### java.util.zip package streams:
- CheckedOutputStream
- CheckedInputStream
- DeflaterOutputStream
- GZIPOutputStream
- GZIPInputStream
- InflaterInputStream
- ZipOutputStream
- ZipInputStream

### java.util.jar package streams:
- JarOutputStream
- JarInputStream

