package com.kyljmeeski.plainhttps.body;

import com.kyljmeeski.plainhttps.Body;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A class representing a dechunked version of an HTTP response body.
 * This class takes a chunked HTTP response body and converts it into a dechunked form.
 */
public class DechunkedBody implements Body {

    private final Body source;

    /**
     * Constructs a DechunkedBody with the specified source body.
     *
     * @param source the source body that is chunked
     */
    public DechunkedBody(Body source) {
        this.source = source;
    }

    /**
     * Returns the dechunked content of the response body as bytes.
     * This method reads the chunked content from the source body,
     * dechunks it, and returns the dechunked content.
     *
     * @return the dechunked content of the response body as a byte array
     */
    @Override
    public byte[] content() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int index = 0;

        while (index < source.content().length) {
            // Read chunk size
            int chunkSizeEndIndex = index;
            while (source.content()[chunkSizeEndIndex] != '\r') {
                chunkSizeEndIndex++;
            }
            String chunkSizeHex = new String(source.content(), index, chunkSizeEndIndex - index);
            int chunkSize = Integer.parseInt(chunkSizeHex.trim(), 16);

            if (chunkSize == 0) {
                break; // End of chunks
            }

            index = chunkSizeEndIndex + 2; // Skip '\r\n'

            // Read the chunk data
            byte[] chunk = new byte[chunkSize];
            System.arraycopy(source.content(), index, chunk, 0, chunkSize);
            try {
                outputStream.write(chunk);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            index += chunkSize + 2; // Move to the next chunk (skip chunk data and '\r\n')
        }

        return outputStream.toByteArray();
    }

    /**
     * Returns the MIME type of the content in the response body.
     * This method simply delegates to the source body's type method.
     *
     * @return the MIME type of the content in the response body as a String
     */
    @Override
    public String type() {
        return source.type();
    }

}
