# Formatters

### Format specifiers:
1. %[argument_index$][flags][width][.precision]conversion
2. %[argument_index$][flags][width]conversion
3. %[flags][width]conversion

### Examples format specifiers and their conversions:
1. %d: Formats the argument as a decimal integer.
2. %x: Formats the argument as a hexadecimal integer.
3. %c: Formats the argument as a character.
4. %f: Formats the argument as a decimal number.
5. %s: Formats the argument as a string.
6. %n: Outputs an operating system-specific line separator.
7. %10.2f: Formats the argument as a decimal number with
   10 as the minimum number of characters to be written
   (leading spaces are written when the number is smaller
   than the width) and 2 as the number of characters to be
   written after the decimal point.
8. %05d: Formats the argument as a decimal integer with 5
   as the minimum number of characters to be written
   (leading 0s are written when the number is smaller than
   the width).

When you’re finished with the formatter, you might want to invoke the void
flush() method to ensure that any buffered output in the destination is
written to the underlying stream. You would typically invoke flush() when
the destination is a file.

