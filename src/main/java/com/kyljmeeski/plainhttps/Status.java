/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2024 Amir Syrgabaev
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
package com.kyljmeeski.plainhttps;

import java.util.Arrays;

public class Status {

    private final byte[] status;

    public Status(byte[] status) {
        this.status = status;
    }

    public int code() {
        int start = -1;
        for (int i = 0; i < status.length; i++) {
            if (status[i] == 32) {
                if (start != -1) {
                    byte[] bytes = Arrays.copyOfRange(status, start + 1, i);
                    return ((bytes[0] - '0') * 100) + ((bytes[1] - '0') * 10) + (bytes[2] - '0');
                } else {
                    start = i;
                }
            }
        }
        return 0;
    }

    public String phrase() {
        int start = -1;
        for (int i = 0; i < status.length; i++) {
            if (status[i] == 32) {
                if (start != -1) {
                    byte[] bytes = Arrays.copyOfRange(status, i + 1, status.length);
                    return new String(bytes);
                } else {
                    start = i;
                }
            }
        }
        return null;
    }

}
