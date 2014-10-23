/**
 * Copyright (C) 2013 Guestful (info@guestful.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.guestful.jersey.container;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public class Port {

    public static boolean isFree(int port) {
        try {
            new ServerSocket(port).close();
            return true;
        } catch (IOException ignored) {
        }
        return false;
    }

    public static int findFree(int min, int max) {
        Random r = new Random();
        max = max - min + 1;
        while (true) {
            int p = min + r.nextInt(max);
            if (isFree(p)) {
                return p;
            }
        }
    }

    public static int findFree() {
        return findFree(1025, 65500);
    }
}
