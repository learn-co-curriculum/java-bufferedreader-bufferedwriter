# BufferedReader and BufferedWriter

## Learning Goals

- Learn how to write to a file using the `BufferedWriter` class.
- Discuss the `FileReader` class.
- Explain how to read from a file using the `BufferedReader` class.

## Introduction

It is important to know that there are **many** ways to read and write to a file
in Java. In the File Input Output (IO) lesson, we learned about the two
simplest ways to read and write to a file. In this lesson, we will learn about
two more ways to read and write to a file - in perhaps a more efficient manner
too.

## BufferedWriter

The `BufferedWriter` class provides us another solution to writing files in Java!
It is similar to the `FileWriter` class, and we'll actually construct our
`BufferedWriter` instance using a `FileWriter` object!

```java
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileIOMain {
    public static void main(String[] args) {
        File file = new File("simple.txt");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
    }
}
```

So if we have to pass in a `FileWriter` object in order to even use the
`BufferedWriter`, why even bother? What's the difference?

The `BufferedWriter` uses an internal buffer to write data to a file. A
**buffer** is a place in memory that stores data temporarily. We may have seen
a show on a streaming service "buffer", meaning it continuously downloads part
of the show into a buffer, so it doesn't have to download while we're watching.
We can also think of a buffer as a bowl of candy on Halloween. Say we're handing
out candy to children for trick-or-treat. We continuously fill the bowl with
candy from the candy bag. The bowl acts as the buffer in this example between
the children grabbing the candy and the candy bag. Getting back to the
`BufferedWriter` class, it is more efficient to use a `BufferedWriter` object
when writing to a file if dealing with a large amount of data that needs to be
written.

Now that we know how to construct a `BufferedWriter`, let's see how to write to
a file using it!

```java
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOMain {
    public static void main(String[] args) {
        File file = new File("simple.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("example of writing to a file.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
```

Let's break this code down a little:

- As we saw before, the `FileWriter` constructors could throw an `IOException`
  so we will need to wrap it in a try-catch statement. We can utilize the
  try-with-resources again too!
- Notice that the `BufferedWriter` also has a `write()` method! We can call
  that method again to write the text "example of writing to a file" to the
  `File` object we specified.

Here is the file we wrote to with the `BufferedWriter` object!

![simple-text-file-content](https://curriculum-content.s3.amazonaws.com/java-mod-3/file-input-output/write-to-file-content.png)

## BufferedReader

The `BufferedReader` class is another solution to reading in text from a file!
Like the `BufferedWriter` class, it also uses an internal buffer to read data.
This is more efficient when reading large amounts of data from a file.

In order to construct a `BufferedReader` instance, we'll need to make use of
another class: `FileReader`. `FileReader` is very similar to `FileWriter`,
except it reads text from files instead of writing to a file. We can construct
a `FileReader` by passing in either a `String` object with the filename, or the
`File` object itself - very similar to how we would construct a `FileWriter`!

```java
import java.io.File;
import java.io.FileReader;

public class FileIOMain {
    public static void main(String[] args) {
        String fileName = "simple.txt";
        File file = new File(filename);

        // Pass in the String with the file name
        FileReader fileReader1 = new FileReader(fileName);
        
        // Pass in the File object itself
        FileReader fileReader2 = new FileReader(file);
    }
}
```

Similarly, the `FileReader` constructors may throw an `IOException`. We won't
be spending any more time discussing the `FileReader` class, but to learn more
about this class, please see the Java documentation here:
[Java 11 FileReader](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/FileReader.html)

Now that we know about the `FileReader`, let's see how we can construct a
`BufferedReader` instance using it!

```java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileIOMain {
  public static void main(String[] args) {
    File file = new File("simple.txt");

    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
  }
}
```

Now this could throw an `IOException`, so we will, once again, wrap this
assignment in a try-with-resources statement. We'll then use the method
`readLine()` from the `BufferedReader` class! The `readLine()` method returns a
`String` object holding the value of the line the `BufferedReader` instance read
in.

```java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileIOMain {
    public static void main(String[] args) {
        String content = "";
        File file = new File("simple.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((content = bufferedReader.readLine()) != null) {
                System.out.println(content);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
```

Notice in the above code, `content` is assigned to `bufferedReader.readLine()`.
If the `readLine()` method returns a `null` value, then there is nothing left
in the file to read. Therefore, we will iterate through the file until the
`bufferedReader` has reached the end-of-file (EOF).

If we run the code above, we'll see that the output is exactly what we
originally wrote to the file:

```text
example of writing to a file.
```

## Resources

- [Java 11 BufferedWriter](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedWriter.html)
- [Java 11 FileReader](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/FileReader.html)
- [Java 11 BufferedReader](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedReader.html)