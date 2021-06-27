/*
 * MIT License
 *
 * Copyright (c) 2018 Nanne Baars
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.paseto4j.version2;

import okio.Buffer;
import org.apache.tuweni.bytes.Bytes;

public class Util {

    private Util() {
    }

    /**
     * Authentication Padding
     * <p>
     * https://github.com/paragonie/paseto/blob/master/docs/01-Protocol-Versions/Common.md#pae-definition
     *
     * @param pieces string[] of the pieces
     */
    public static String pae(String... pieces) {
        try (Buffer accumulator = new Buffer()) {
            accumulator.writeLongLe(pieces.length);

            for (String piece : pieces) {
                accumulator.writeLongLe(piece.length());
                accumulator.writeUtf8(piece);
            }
            return accumulator.snapshot().hex();
        }
    }

    public static byte[] pae(byte[]... pieces) {
        try (Buffer accumulator = new Buffer()) {
            accumulator.writeLongLe(pieces.length);

            for (byte[] piece : pieces) {
                accumulator.writeLongLe(piece.length);
                accumulator.write(piece);
            }
            return accumulator.snapshot().toByteArray();
        }
    }

    public static byte[] hexToBytes(String hex) {
        return Bytes.fromHexString(hex).toArray();
    }
}
