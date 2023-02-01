# Regex

### Character classes
1. A simple character class consists of literal characters
   placed side by side and matches only these characters.
   For example, [abc] consists of characters a, b, and c.
   Also, java RegExDemo t[aiou]ck tack reports a match
   because a is a member of [aiou]. It also reports a
   match when the input is tick, tock, or tuck because i,
   o, and u are members.
2. A negation character class consists of a circumflex
   metacharacter (^), followed by literal characters placed
   side by side, and matches all characters except for
   the characters in the class. For example, [^abc]
   consists of all characters except for a, b, and c. Also,
   java RegExDemo "[^b]ox" box doesn’t report a match
   because b isn’t a member of [^b], whereas java
   RegExDemo "[^b]ox" fox reports a match because f is
   a member. (The double quotes surrounding [^b]ox are
   necessary on my Windows 7 operating system because
   ^ is treated specially at the command line.)
3. A range character class consists of successive literal
   characters expressed as a starting literal character,
   followed by the hyphen metacharacter (-), followed by
   an ending literal character, and matches all characters
   in this range. For example, [a-z] consists of all  
   characters from a through z. Also, java RegExDemo
   [h-l]ouse house reports a match because h is a
   member of the class, whereas java RegExDemo [h-l]
   ouse mouse doesn’t report a match because m lies
   outside of the range and is therefore not part of the
   class. You can combine multiple ranges within the same
   range character class by placing them side by side;
   for example, [A-Za-z] consists of all uppercase and
   lowercase Latin letters.
4. A union character class consists of multiple nested
   character classes and matches all characters that
   belong to the resulting union. For example, [abc[u-z]]
   consists of characters a, b, c, u, v, w, x, y, and z.
   Also, java RegExDemo [[0-9][A-F][a-f]] e reports a
   match because e is a hexadecimal character. (I could
   have alternatively expressed this character class as
   [0-9A-Fa-f] by combining multiple ranges.)
5. An intersection character class consists of multiple
   &&–separated nested character classes and matches all
   characters that are common to these nested character
   classes. For example, [a-c&&[c-f]] consists of
   character c, which is the only character common to
   [a-c] and [c-f]. Also, java RegExDemo "[aeiouy&&[y]]"
   y reports a match because y is common to classes
   [aeiouy] and [y].
6. A subtraction character class consists of multiple
   &&-separated nested character classes, where at least
   one nested character class is a negation character
   class, and matches all characters except for those
   indicated by the negation character class/classes.
   For example, [a-z&&[^x-z]] consists of characters a
   through w. (The square brackets surrounding ^x-z are
   necessary; otherwise, ^ is ignored and the resulting
   class consists of only x, y, and z.) Also, java RegExDemo
   "[a-z&&[^aeiou]]" g reports a match because g is a
   consonant and only consonants belong to this class.
   (I’m ignoring y, which is sometimes regarded as a
   consonant and sometimes regarded as a vowel.)

### Predefined character classes
1. \d -> any digit character == [0-9]
2. \D -> any non-digit character == [^\d]
3. \s -> any whitespace character == [\t\n\x0B\f\r ]
4. \S -> any nonwhitespace character == [^\s]
5. \w -> any word character == [a-zA-Z0-9]
6. \W -> any non word character == [^\w]


### Capturing Groups

A capturing group saves a match’s characters for later recall during pattern
matching and is expressed as a character sequence surrounded by
parentheses metacharacters ( and ). All characters within a capturing group
are treated as a unit. For example, the (Java) capturing group combines J,
a, v, and a into a unit. It matches the Java pattern against all occurrences
of Java in the input. Each match replaces the previous match’s saved Java
characters with the next match’s Java characters.

Capturing groups can appear inside other capturing groups. For example,
capturing groups (A) and (B(C)) appear inside capturing group ((A)
(B(C))), and capturing group (C) appears inside capturing group (B(C)).
Each nested or non-nested capturing group receives its own number,
numbering starts at 1, and capturing groups are numbered from left to
right. For example, ((A)(B(C))) is assigned 1, (A) is assigned 2, (B(C)) is
assigned 3, and (C) is assigned 4.

### Boundary Matches and Zero-Length Matches

1. ^    -> beginning of line
2. $    -> end of line
3. \b   -> word boundary
4. \
5. \B   -> nonword boundary
6. \A   -> beginning of text
7. \G   -> end of previous match
8. \Z   -> end of text except for line terminator (when present)
9. \z   -> end of text

### Quantifiers
1. A greedy quantifier (?, *, or +) attempts to find the
   longest match. Specify X? to find one or no occurrences
   of X, X* to find zero or more occurrences of X, X+ to
   find one or more occurrences of X, X{n} to find n
   occurrences of X, X{n,} to find at least n (and possibly
   more) occurrences of X, and X{n,m} to find at least n but
   no more than m occurrences of X.
2. A reluctant quantifier (??, *?, or +?) attempts to find
   the shortest match. Specify X?? to find one or no
   occurrences of X, X*? to find zero or more occurrences
   of X, X+? to find one or more occurrences of X, X{n}? to
   find n occurrences of X, X{n,}? to find at least n (and
   possibly more) occurrences of X, and X{n,m}? to find at
   least n but no more than m occurrences of X.
3. A possessive quantifier (?+, *+, or ++) is similar to a
   greedy quantifier except that a possessive quantifier
   makes only one attempt to find the longest match,
   whereas a greedy quantifier can make multiple
   attempts. Specify X?+ to find one or no occurrences
   of X, X*+ to find zero or more occurrences of X, X++
   to find one or more occurrences of X, X{n}+ to find n
   occurrences of X, X{n,}+ to find at least n (and possibly
   more) occurrences of X, and X{n,m}+ to find at least n
   but no more than m occurrences of X.

