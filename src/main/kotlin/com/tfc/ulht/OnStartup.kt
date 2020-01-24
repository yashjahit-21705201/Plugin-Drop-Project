/*-
 * Plugin Drop Project
 * 
 * Copyright (C) 2019 Yash Jahit
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tfc.ulht

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.tfc.ulht.loginComponents.Authentication
import com.tfc.ulht.loginComponents.CredentialsController
import java.io.File
import java.io.FileInputStream
import java.security.KeyStore
import java.util.*


class OnStartup: StartupActivity {

    companion object {
        val pass = "password".toCharArray()
    }

    override fun runActivity(project: Project) {

        val userPassFile = File("${project.basePath}\\up.txt")

        if (userPassFile.exists()) {
            val bufferedReader = userPassFile.bufferedReader()
            val text = bufferedReader.readLine()

            val data = text.split(";")

            val username = data[0]
            val decryptedPassword = String(CredentialsController().decrypt(data[1].toByteArray()))

            Authentication().checkCredentials(username, decryptedPassword, true)
            Authentication.alreadyLoggedIn = true
        }

    }


}